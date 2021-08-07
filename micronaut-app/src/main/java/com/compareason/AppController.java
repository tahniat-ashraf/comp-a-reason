package com.compareason;

import com.compareason.model.Posts;
import com.compareason.service.PostService;
import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.Produces;

import javax.inject.Inject;
import java.util.List;

@Controller
public class AppController {

    @Inject
    PostService postService;

    @Get("/posts-mongo")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Posts> handleMongoRequests() {
        return postService.findAllPostsByMongo();
    }

    @Get("/hello")
    @Produces(MediaType.TEXT_PLAIN)
    public String greet() {
        return "Hello World";
    }

    @Get("/posts-client")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Posts> handleWebClientRequests() {
        return postService.findAllPostsByWebclient();
    }
}
