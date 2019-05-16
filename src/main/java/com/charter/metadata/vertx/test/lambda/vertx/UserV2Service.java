package com.charter.metadata.vertx.test.lambda.vertx;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Handler;
import io.vertx.core.eventbus.EventBus;
import io.vertx.core.eventbus.Message;
import io.vertx.core.json.JsonObject;

public class UserV2Service extends AbstractVerticle {
    @Override
    public void start() throws Exception {

      EventBus eventBus = vertx.eventBus();

      eventBus.consumer("GET:/users/v2", new Handler<Message<String>>() {
        @Override
        public void handle(Message<String> tMessage) {
          tMessage.reply(new JsonObject("{\n" +
            "    \"statusCode\": 200\n" +
            "}"));
        }
      });
  }
}
