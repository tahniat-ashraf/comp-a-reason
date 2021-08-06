package com.compareason.service;

import com.compareason.model.Posts;

import java.util.List;

public interface PostService {
    List<Posts> findAllPostsByMongo();
    List<Posts> findAllPostsByWebclient();

}
