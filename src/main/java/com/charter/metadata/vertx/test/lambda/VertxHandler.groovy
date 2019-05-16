package com.charter.metadata.vertx.test.lambda

import com.amazonaws.services.lambda.runtime.Context
import com.amazonaws.services.lambda.runtime.RequestHandler
import com.charter.metadata.vertx.test.lambda.vertx.UserService
import com.charter.metadata.vertx.test.lambda.vertx.UserV2Service
import io.vertx.core.Vertx
import io.vertx.core.json.JsonObject
import org.apache.log4j.LogManager
import org.apache.log4j.Logger

import java.util.concurrent.CompletableFuture
import java.util.concurrent.TimeUnit

import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyResponseEvent;
/**
 * Created by jguo on 2019-04-21.
 */

class VertxHandler implements RequestHandler<Map, String> {

  static final Logger logger = LogManager.getLogger(VertxHandler.class);
  APIGatewayProxyResponseEvent response = new APIGatewayProxyResponseEvent();


  // Initialize Vertx instance and deploy UserService Verticle
  final Vertx vertxInstance = {
    System.setProperty('vertx.disableFileCPResolving', 'true')
    final vertx = Vertx.vertx()
    vertx.deployVerticle(new UserService ())
    vertx.deployVerticle(new UserV2Service())
    return vertx
  }()

  @Override
  String handleRequest(Map input, Context context) {
    final future = new CompletableFuture<String>()

    // Send message to event bus using httpmethod:resource as dynamic channel
    final eventBusAddress = "${input.httpMethod}:${input.path}"

    vertxInstance.eventBus().send(
      eventBusAddress,
      new JsonObject(input),
      { asyncResult ->
      if (asyncResult.succeeded()) {
        System.out.println(asyncResult.result().body())
//        response.setHeaders(Collections.singletonMap("x-custom-header", "my custom header value"));
//        response.setStatusCode(200);
//        response.setBody(asyncResult.result().body())
        future.complete(asyncResult.result().body())
      } else {
        future.completeExceptionally(asyncResult.cause())
      }
    })

    future.get(5, TimeUnit.SECONDS)
  }
}
