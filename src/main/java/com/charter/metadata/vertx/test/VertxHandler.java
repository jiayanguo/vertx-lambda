package com.charter.metadata.vertx.test;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestStreamHandler;
import com.charter.metadata.vertx.test.util.LambdaServer;
import com.charter.metadata.vertx.test.verticle.handler.AddProductHandler;
import com.charter.metadata.vertx.test.verticle.handler.GetProductHandler;
import com.charter.metadata.vertx.test.verticle.handler.ListProductHandler;
import io.vertx.core.Vertx;
import io.vertx.core.http.HttpServerRequest;
import io.vertx.ext.web.Router;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.concurrent.Semaphore;

/**
 * Created by jguo on 2019-04-23.
 */
public class VertxHandler implements RequestStreamHandler {

  static {
    // Lambda function is allowed write access to /tmp only
    System.setProperty("vertx.cacheDirBase", "/tmp/.vertx");
  }

  private final Semaphore responseEnd = new Semaphore(0, true);
  Vertx vertx = Vertx.vertx();
  private Router router;

  public VertxHandler() {
    router =Router.router(vertx);
    router.get("/products/:productID").handler(new GetProductHandler());
    router.put("/products/:productID").handler(new AddProductHandler());
    router.get("/products").handler(new ListProductHandler());

  }

  @Override
  public void handleRequest(InputStream input, OutputStream output, Context context) throws IOException {

    // create a LambdaServer which will process a single HTTP request
    LambdaServer server = new LambdaServer(vertx, context, input, output);

    // trigger the HTTP request processing
    server.requestHandler(this::handleRequest).listen();

    // block the main thread until the request has been fully processed
    waitForResponseEnd();
  }

  public void waitForResponseEnd() {
    while (true) {
      try {
        responseEnd.acquire();
        return;
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }
  }

  private void handleRequest(HttpServerRequest request) {
    request.response().endHandler(this::handleResponseEnd);
    router.accept(request);
  }

  private void handleResponseEnd(Void v) {
    responseEnd.release();
  }
}
