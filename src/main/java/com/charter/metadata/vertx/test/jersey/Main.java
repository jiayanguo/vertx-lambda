package com.charter.metadata.vertx.test.jersey;

import com.charter.metadata.vertx.test.verticle.handler.AddProductHandler;
import com.charter.metadata.vertx.test.verticle.handler.GetProductHandler;
import com.charter.metadata.vertx.test.verticle.handler.ListProductHandler;
import com.englishtown.vertx.hk2.HK2JerseyBinder;
import com.englishtown.vertx.hk2.HK2VertxBinder;
import com.englishtown.vertx.jersey.JerseyServer;
import io.vertx.core.Vertx;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.handler.BodyHandler;
import org.glassfish.hk2.api.ServiceLocator;
import org.glassfish.hk2.utilities.ServiceLocatorUtilities;


public class Main {

  public static void main(String[] args) {

    Vertx vertx = Vertx.vertx();

    Router router = Router.router(vertx);

    router.route().handler(BodyHandler.create());
    router.get("/products/:productID").handler(new GetProductHandler());
    router.put("/products/:productID").handler(new AddProductHandler());
    router.get("/products").handler(new ListProductHandler());

    // Can't make it work on the same port.
    // use websocketHandler to serve static content
    vertx.createHttpServer().requestHandler(router).listen(8081);

    vertx.runOnContext(aVoid -> {

      // Set up the jersey configuration
      // The minimum config required is a package to inspect for JAX-RS endpoints
      vertx.getOrCreateContext().config()
        .put("jersey", new JsonObject()
          .put("port", 8080)
          .put("base_path", "/helloworld")
          .put("resources", new JsonArray()
            .add("com.charter.metadata.vertx.test.jersey.rest")

          ));

      // Use a service locator (HK2 or Guice are supported by default) to create the jersey server
      ServiceLocator locator = ServiceLocatorUtilities.bind(new HK2JerseyBinder(), new HK2VertxBinder(vertx));
      JerseyServer server = locator.getService(JerseyServer.class);

      // Start the server which simply returns "Hello World!" to each GET request.
      server.start();

    });
  }
}
