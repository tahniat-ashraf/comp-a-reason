package com.compareason.service;

import com.compareason.client.PostClient;
import com.compareason.model.Posts;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.ArrayList;
import java.util.List;

@Singleton
public class PostServiceImpl implements PostService {

    @Inject
    PostClient postClient;

    @Inject
    MongoClient mongoClient;

    @Override
    public List<Posts> findAllPostsByMongo() {
        return getPostsCollection()
                .find()
                .into(new ArrayList<>());
    }

    private MongoCollection<Posts> getPostsCollection() {
        return mongoClient
                .getDatabase("vertx-async-to-sync")
                .getCollection("posts", Posts.class);
    }

    @Override
    public List<Posts> findAllPostsByWebclient() {
        return postClient.getPosts();
    }
}
