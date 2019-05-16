package com.charter.metadata.vertx.test;

import io.vertx.junit5.VertxExtension;
import org.junit.jupiter.api.extension.ExtendWith;

@ExtendWith(VertxExtension.class)
public class TestMainVerticle {

//  @BeforeEach
//  void deploy_verticle(Vertx vertx, VertxTestContext testContext) {
//    vertx.deployVerticle(new MainVerticle(), testContext.succeeding(id -> testContext.completeNow()));
//  }
//
//  @Test
//  @DisplayName("Should start a Web Server on port 8888")
//  @Timeout(value = 10, timeUnit = TimeUnit.SECONDS)
//  void start_http_server(Vertx vertx, VertxTestContext testContext) throws Throwable {
//    vertx.createHttpClient().getNow(8888, "localhost", "/", response -> testContext.verify(() -> {
//      assertTrue(response.statusCode() == 200);
//      response.handler(body -> {
//        assertTrue(body.toString().contains("Hello from Vert.x!"));
//        testContext.completeNow();
//      });
//    }));
//  }

}
