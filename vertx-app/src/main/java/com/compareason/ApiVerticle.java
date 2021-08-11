package com.compareason;

import com.compareason.service.MongoClientService;
import io.vertx.core.Promise;
import io.vertx.core.impl.logging.Logger;
import io.vertx.core.impl.logging.LoggerFactory;
import io.vertx.core.json.Json;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.client.WebClientOptions;
import io.vertx.reactivex.core.AbstractVerticle;
import io.vertx.reactivex.ext.web.Router;
import io.vertx.reactivex.ext.web.RoutingContext;
import io.vertx.reactivex.ext.web.client.WebClient;
import io.vertx.reactivex.ext.web.handler.BodyHandler;


public class ApiVerticle extends AbstractVerticle {

  private static final Logger LOG = LoggerFactory.getLogger("ApiVerticle");
  private static final JsonObject DEFAULT_ERROR = new JsonObject().put("status", "fail");


  @Override
  public void start(Promise<Void> promise) throws Exception {

    var mongoClient = new MongoClientService();
    var webclient = WebClient.create(vertx, new WebClientOptions().setMaxPoolSize(100));
    Router router = Router.router(vertx);
    router.route().handler(BodyHandler.create());
    router.get("/posts-mongo").handler(routingContext -> handleMongoRequest(routingContext, mongoClient)).failureHandler(this::failureHandler);
    router.get("/hello").handler(this::greet).failureHandler(this::failureHandler);
    router.get("/posts-client").handler(routingContext -> handleWebClientRequest(routingContext, webclient));

    vertx
      .createHttpServer()
      .requestHandler(router)
      .rxListen(9090)
      .subscribe(httpServer -> {
        LOG.info("ApiVerticle is up and running bro");
        promise.complete();
      }, throwable -> {
        LOG.error("Failed to deploy ApiVerticle", throwable);
        promise.fail(throwable);
      });

  }

  private void handleWebClientRequest(RoutingContext routingContext, WebClient webclient) {
    routingContext.response().putHeader("content-type", "application/json");

    webclient
      .get("jsonplaceholder.typicode.com","/posts")
      .rxSend()
      .subscribe(bufferHttpResponse -> {
          routingContext.response().end(bufferHttpResponse.bodyAsJsonArray().encode());
        },
        throwable -> {
          LOG.error("Error while fetching posts from jsonplaceholder", throwable);
          routingContext.response().end(DEFAULT_ERROR.encode());
        });

  }

  private void greet(RoutingContext routingContext) {
    routingContext.response().putHeader("content-type", "application/json");
    routingContext.response().end(new JsonObject().put("message", "Hello World!").encode());
  }

  private void failureHandler(RoutingContext routingContext) {
    routingContext.response().putHeader("content-type", "application/json");
    routingContext.response().end(DEFAULT_ERROR.encode());
  }

  private void handleMongoRequest(RoutingContext routingContext, MongoClientService mongoClient) {

    routingContext.response().putHeader("content-type", "application/json");

    mongoClient
      .findAllPosts()
      .subscribe(message ->
        {
          routingContext.response().end(Json.encode(message));
        },
        throwable -> {
          LOG.error("Error while fetching posts from mongo", throwable);
          routingContext.response().end(DEFAULT_ERROR.encode());
        });
  }

}
