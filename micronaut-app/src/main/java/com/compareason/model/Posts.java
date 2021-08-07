package com.compareason.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.bson.codecs.pojo.annotations.BsonId;
import org.bson.codecs.pojo.annotations.BsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Posts {

    @BsonId
    @BsonProperty(value = "_id")
    private String id;
    @BsonProperty(value = "id")
    private int postId;
    @BsonProperty(value = "userId")
    private int userId;
    @BsonProperty(value = "title")
    private String title;
    @BsonProperty(value = "body")
    private String body;

    public int getPostId() {
        return postId;
    }

    public String getId() {
        return id.toString();
    }

    public int getUserId() {
        return userId;
    }

    public String getTitle() {
        return title;
    }

    public String getBody() {
        return body;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setPostId(int postId) {
        this.postId = postId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setBody(String body) {
        this.body = body;
    }

    @Override
    public String toString() {
        return "Posts{" +
                "_id='" + id + '\'' +
                ", id=" + postId +
                ", userId=" + userId +
                ", title='" + title + '\'' +
                ", body='" + body + '\'' +
                '}';
    }
}
