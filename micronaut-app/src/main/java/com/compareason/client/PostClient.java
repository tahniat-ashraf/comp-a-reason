package com.compareason.client;

import com.compareason.model.Posts;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.client.annotation.Client;
import io.micronaut.retry.annotation.CircuitBreaker;
import io.micronaut.retry.annotation.Retryable;

import java.util.List;

@Client("https://jsonplaceholder.typicode.com")
@Retryable
@CircuitBreaker
public interface PostClient {

    @Get("/posts")
    List<Posts> getPosts();

}
