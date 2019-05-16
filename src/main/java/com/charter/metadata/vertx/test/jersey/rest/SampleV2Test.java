package com.charter.metadata.vertx.test.jersey.rest;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

/**
 * Created by jguo on 2019-04-21.
 */


@Path("/services/v2")
public class SampleV2Test {

  @GET
  @Path("message")
  public Response getMessage() {
    return Response.ok().entity("This is sample message of vertx").build();
  }

  @GET
  @Path("welcome")
  public Response getWelcome() {
    return Response.ok().entity("Welcome to vertx").build();
  }
}
