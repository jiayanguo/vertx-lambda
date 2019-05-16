package com.charter.metadata.vertx.test.verticle.handler;

import com.charter.metadata.vertx.test.verticle.util.Product;
import io.vertx.core.Handler;
import io.vertx.core.http.HttpServerResponse;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.RoutingContext;

import java.util.List;
import java.util.Map;

/**
 * Created by jguo on 2019-04-21.
 */
public class GetProductHandler implements Handler<RoutingContext> {
  @Override
  public void handle(RoutingContext routingContext) {
    String productID = routingContext.request().getParam("productID");

    List<Map.Entry<String, String>> list = routingContext.request().params().entries();
    System.out.println(routingContext.request().params().entries().toString());
    HttpServerResponse response = routingContext.response();
    if (productID == null) {
      response.setStatusCode(400).end();
    } else {
      JsonObject product = Product.products.get(productID);

      list.stream().forEach((name) -> {
        product.put(name.getKey(), name.getValue());
      });

      if (product == null) {
        response.setStatusCode(404).end();
      } else {
        response.putHeader("content-type", "application/json").end(product.encodePrettily());
      }
    }
  }
}
