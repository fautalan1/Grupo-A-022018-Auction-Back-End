package model;

import entity.DAuction;

public class Auction {
        private long id;
        private String author;
        private String title;
        private String description;
        private String address;

    public Auction(DAuction aDAction){
        this.id = aDAction.getId();
        this.author = aDAction.getAuthor();
        this.title = aDAction.getTitle();
        this.description = aDAction.getDescription();
        this.address = aDAction.getAddress();
    }

    public Auction(){}

    public Auction(long id, String author, String title, String description, String address) {
        this.id = id;
        this.author = author;
        this.title = title;
        this.description = description;
        this.address = address;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
