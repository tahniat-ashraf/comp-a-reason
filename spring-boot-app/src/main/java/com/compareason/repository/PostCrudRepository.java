package com.compareason.repository;

import com.compareason.model.Posts;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostCrudRepository extends MongoRepository<Posts, String> {
}
