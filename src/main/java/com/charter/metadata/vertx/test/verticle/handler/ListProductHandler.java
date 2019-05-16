package com.charter.metadata.vertx.test.verticle.handler;

import com.charter.metadata.vertx.test.verticle.util.Product;
import io.vertx.core.Handler;
import io.vertx.core.json.JsonArray;
import io.vertx.ext.web.RoutingContext;

/**
 * Created by jguo on 2019-04-21.
 */
public class ListProductHandler implements Handler<RoutingContext> {
  @Override
  public void handle(RoutingContext routingContext) {
    JsonArray arr = new JsonArray();
    Product.products.forEach((k, v) -> arr.add(v));
    routingContext.response().putHeader("content-type", "application/json").end(arr.encodePrettily());
  }
}
