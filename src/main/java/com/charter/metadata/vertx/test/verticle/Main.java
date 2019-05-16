package com.charter.metadata.vertx.test.verticle;

import io.vertx.core.Vertx;

/**
 * Created by jguo on 2019-04-21.
 */
public class Main {
  public static void main(String[] args) {
    Vertx vertx = Vertx.vertx();

    vertx.deployVerticle(new SimpleRestRouter());
  }
}
