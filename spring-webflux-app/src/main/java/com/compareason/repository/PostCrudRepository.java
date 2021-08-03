package com.compareason.repository;

import com.compareason.model.Posts;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostCrudRepository extends ReactiveMongoRepository<Posts, String> {

}
