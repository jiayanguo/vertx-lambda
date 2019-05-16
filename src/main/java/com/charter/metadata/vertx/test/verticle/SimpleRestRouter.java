package com.charter.metadata.vertx.test.verticle;

import com.charter.metadata.vertx.test.verticle.handler.AddProductHandler;
import com.charter.metadata.vertx.test.verticle.handler.GetProductHandler;
import com.charter.metadata.vertx.test.verticle.handler.ListProductHandler;
import io.vertx.core.AbstractVerticle;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.handler.BodyHandler;

public class SimpleRestRouter extends AbstractVerticle {

  @Override
  public void start() {

    Router router = Router.router(vertx);

    router.route().handler(BodyHandler.create());
    router.get("/products/:productID").handler(new GetProductHandler());
    router.put("/products/:productID").handler(new AddProductHandler());
    router.get("/products").handler(new ListProductHandler());

    vertx.createHttpServer().requestHandler(router).listen(8080);
  }
}
