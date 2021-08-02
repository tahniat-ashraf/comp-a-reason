package com.compareason.service;


import io.reactivex.Single;
import io.vertx.core.impl.logging.Logger;
import io.vertx.core.impl.logging.LoggerFactory;
import io.vertx.core.json.JsonObject;
import io.vertx.reactivex.core.Vertx;
import io.vertx.reactivex.ext.mongo.MongoClient;


import java.util.List;

public class MongoClientService {

  private final MongoClient mongoClient;

  public MongoClientService() {


    mongoClient = MongoClient.createShared(Vertx.currentContext().owner(),
      new JsonObject().put("db_name", "vertx-async-to-sync")
    );

  }

  public Single<List<JsonObject>> findAllPosts() {
    return mongoClient
      .rxFind("posts", new JsonObject());
  }

}
