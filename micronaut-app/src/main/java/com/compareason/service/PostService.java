package com.compareason.service;

import com.compareason.model.Posts;
import com.mongodb.client.FindIterable;

import java.util.ArrayList;
import java.util.List;

public interface PostService {
    List<Posts> findAllPostsByMongo();

    List<Posts> findAllPostsByWebclient();
}
