package com.charter.metadata.vertx.test.lambda.vertx

import io.vertx.core.AbstractVerticle
import io.vertx.core.json.JsonObject

/**
 * Created by jguo on 2019-04-22.
 */
class UserServices extends AbstractVerticle {
  @Override
  void start() throws Exception {

    final eventBus = vertx.eventBus()

    eventBus.consumer('GET:/users') { message ->
      // Do something with Vert.x async, reactive APIs
      message.reply(new JsonObject([statusCode: 200, body: 'Received GET:/users/{id}']))
    }

    eventBus.consumer('POST:/users') { message ->
      // Do something with Vert.x async, reactive APIs
      message.reply([statusCode: 201, body: 'Received POST:/users'])
    }

    eventBus.consumer('GET:/users/{id}') { message ->
      // Do something with Vert.x async, reactive APIs
      message.reply([statusCode: 200, body: 'Received GET:/users/{id}'])
    }

    eventBus.consumer('PUT:/users/{id}') { message ->
      // Do something with Vert.x async, reactive APIs
      message.reply([statusCode: 200, body: 'Received PUT:/users/{id}'])
    }

    eventBus.consumer('DELETE:/users/{id}') { message ->
      // Do something with Vert.x async, reactive APIs
      message.reply([statusCode: 200, body: 'Received DELETE:/users/{id}'])
    }
  }
}
