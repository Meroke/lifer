package com.example.lifer.Data;

public class NewsItem {
    private String imageUrl;
    private String title;
    private String description;
    private String publishedAt;

    public NewsItem(String imageUrl, String title, String description, String publishedAt) {
        this.imageUrl = imageUrl;
        this.title = title;
        this.description = description;
        this.publishedAt = publishedAt;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getPublishedAt() {
        return publishedAt;
    }
}
