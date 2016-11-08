package com.example.fabricioflores.twitterproviderclient.models;

/**
 * Created by fabricioflores on 8/11/16.
 */

public class Query {
    private long id;
    private String text;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String toString(){
        return this.getText();
    }
}
