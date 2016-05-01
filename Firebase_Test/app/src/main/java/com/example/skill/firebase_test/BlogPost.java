package com.example.skill.firebase_test;

public class BlogPost {
    private String author;
    private String title;

    public BlogPost() {
        // empty default constructor, necessary for Firebase to be able to deserialize blog posts
    }

    public String getAuthor() {
        return author;
    }

    public String getTitle() {
        return title;
    }

}