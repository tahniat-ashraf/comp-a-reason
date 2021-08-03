package com.compareason.service;

import com.compareason.model.Posts;
import com.compareason.repository.PostCrudRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;

@Service
public class PostServiceImpl implements PostService {

    private final PostCrudRepository postCrudRepository;
    private final WebClient webClient;

    public PostServiceImpl(PostCrudRepository postCrudRepository) {
        this.postCrudRepository = postCrudRepository;
        this.webClient = WebClient.create("https://jsonplaceholder.typicode.com");
    }


    public Flux<Posts> findAllPostsByMongo() {
                    return postCrudRepository
                            .findAll();
    }

    @Override
    public Flux<Posts> findAllPostsByWebclient() {
        return webClient
                .get()
                .uri("/posts")
                .exchangeToFlux(clientResponse ->
                        clientResponse.bodyToFlux(Posts.class));
    }

}
