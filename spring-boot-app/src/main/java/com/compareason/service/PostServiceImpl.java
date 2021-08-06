package com.compareason.service;

import com.compareason.model.Posts;
import com.compareason.repository.PostCrudRepository;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class PostServiceImpl implements PostService {

    private final PostCrudRepository postCrudRepository;
    private final RestTemplate restTemplate;

    public PostServiceImpl(PostCrudRepository postCrudRepository) {
        this.postCrudRepository = postCrudRepository;
        restTemplate = new RestTemplate();
    }

    @Override
    public List<Posts> findAllPostsByMongo() {
        return postCrudRepository.findAll();
    }

    @Override
    public List<Posts> findAllPostsByWebclient() {
        return restTemplate
                .exchange("https://jsonplaceholder.typicode.com/posts", HttpMethod.GET, null, new ParameterizedTypeReference<List<Posts>>() {
                }).getBody();
    }
}
