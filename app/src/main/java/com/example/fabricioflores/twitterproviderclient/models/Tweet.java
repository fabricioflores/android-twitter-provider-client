package com.example.fabricioflores.twitterproviderclient.models;

/**
 * Created by fabricioflores on 8/11/16.
 */

public class Tweet {
    private String text;
    private String userImage;
    private String userName;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getUserImage() {
        return userImage;
    }

    public void setUserImage(String userImage) {
        this.userImage = userImage;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
