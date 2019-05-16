package com.charter.metadata.vertx.test.verticle.util;

import io.vertx.core.json.JsonObject;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by jguo on 2019-04-21.
 */
public class Product {
  public static Map<String, JsonObject> products = new HashMap<>();

  static  {
    setUpInitialData();
  }

  private static void setUpInitialData() {
    addProduct(new JsonObject().put("id", "prod3568").put("name", "Egg Whisk").put("price", 3.99).put("weight", 150));
    addProduct(new JsonObject().put("id", "prod7340").put("name", "Tea Cosy").put("price", 5.99).put("weight", 100));
    addProduct(new JsonObject().put("id", "prod8643").put("name", "Spatula").put("price", 1.00).put("weight", 80));
  }

  private static void addProduct(JsonObject product) {
    products.put(product.getString("id"), product);
  }
}
