package com.example.yummychina.model;

/**
 * This class is a model, encapsulating all the infor needed by app of posts.
 */
public class Post {
    private String postId;
    private String fromWhom;
    private String imageLink;
    private String story;
    private String description;

    public Post() {
    }

    public Post(String postId, String fromWhom, String imageLink, String story, String description) {
        this.postId = postId;
        this.fromWhom = fromWhom;
        this.imageLink = imageLink;
        this.story = story;
        this.description = description;
    }

    public String getPostId() {
        return postId;
    }

    public void setPostId(String postId) {
        this.postId = postId;
    }

    public String getFromWhom() {
        return fromWhom;
    }

    public void setFromWhom(String fromWhom) {
        this.fromWhom = fromWhom;
    }

    public String getImageLink() {
        return imageLink;
    }

    public void setImageLink(String imageLink) {
        this.imageLink = imageLink;
    }

    public String getStory() {
        return story;
    }

    public void setStory(String story) {
        this.story = story;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
