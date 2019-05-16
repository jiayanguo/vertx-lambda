package com.charter.metadata.vertx.test.verticle.handler;

import com.charter.metadata.vertx.test.verticle.util.Product;
import io.vertx.core.Handler;
import io.vertx.core.http.HttpServerResponse;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.RoutingContext;

/**
 * Created by jguo on 2019-04-21.
 */
public class AddProductHandler implements Handler<RoutingContext> {
  @Override
  public void handle(RoutingContext routingContext) {
    String productID = routingContext.request().getParam("productID");
    HttpServerResponse response = routingContext.response();
    if (productID == null) {
      response.setStatusCode(400).end();
    } else {
      JsonObject product = routingContext.getBodyAsJson();
      if (product == null) {
        response.setStatusCode(404).end();
      } else {
        Product.products.put(productID, product);
        response.end();
      }
    }
  }
}
