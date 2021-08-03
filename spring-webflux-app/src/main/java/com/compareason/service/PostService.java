package com.compareason.service;

import com.compareason.model.Posts;
import reactor.core.publisher.Flux;

public interface PostService {
    Flux<Posts> findAllPostsByMongo();
    Flux<Posts> findAllPostsByWebclient();
}
