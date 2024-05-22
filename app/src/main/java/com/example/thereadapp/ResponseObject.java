package com.example.thereadapp;

public class ResponseObject {
    private String id;
    private String text;
    private String source;
    private String source_url;
    private String language;
    private String permalink;

    public ResponseObject(String id, String text, String title, String body, String lang, String permalink) {
        this.id = id;
        this.text = text;
        this.source = title;
        this.source_url = body;
        this.language = lang;
        this.permalink = permalink;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String userId) {
        this.text = userId;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String title) {
        this.source = title;
    }

    public String getSource_url() {
        return source_url;
    }

    public void setSource_url(String source_url) {
        this.source_url = source_url;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String source_url) {
        this.language = source_url;
    }

    public String getPermalink() {
        return permalink;
    }

    public void setPermalink(String source_url) {
        this.permalink = source_url;
    }

}

