package com.compareason;

import com.compareason.model.Posts;
import com.compareason.service.PostService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
public class AppController {

    private final PostService postService;

    public AppController(PostService postService) {
        this.postService = postService;
    }

    @GetMapping(value = "/posts-mongo", produces = MediaType.APPLICATION_JSON_VALUE)
    public Flux<Posts> handleMongoRequests() {
        return postService.findAllPostsByMongo();
    }

    @GetMapping(value = "/hello", produces = MediaType.TEXT_PLAIN_VALUE)
    public Mono<String> greet() {
        return Mono.just("Hello World");
    }

    @GetMapping(value = "/posts-client", produces = MediaType.APPLICATION_JSON_VALUE)
    public Flux<Posts> handleWebClientRequests() {
        return postService.findAllPostsByWebclient();
    }


}
