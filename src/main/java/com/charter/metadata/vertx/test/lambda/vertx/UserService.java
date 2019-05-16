package com.charter.metadata.vertx.test.lambda.vertx;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Handler;
import io.vertx.core.eventbus.EventBus;
import io.vertx.core.eventbus.Message;
import io.vertx.core.json.JsonObject;

public class UserService extends AbstractVerticle {
    @Override
    public void start() throws Exception {

      EventBus eventBus = vertx.eventBus();

      eventBus.consumer("GET:/users", new Handler<Message<String>>() {
        @Override
        public void handle(Message<String> tMessage) {

          JsonObject jsonObject = new JsonObject("{\n" +
            "    \"statusCode\": 200\n" +
            "}");

          tMessage.reply(jsonObject);
        }
      });
  }
}
