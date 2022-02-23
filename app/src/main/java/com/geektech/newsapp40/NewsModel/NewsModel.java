package com.geektech.newsapp40.NewsModel;

import java.io.Serializable;

public class NewsModel implements Serializable {
    private String title;
    private String createdAt;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public NewsModel(String title, String createdAt) {
        this.title = title;
        this.createdAt = createdAt;
    }
}
