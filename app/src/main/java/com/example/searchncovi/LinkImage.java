package com.example.searchncovi;

public class LinkImage {
    private String image_url;

    public LinkImage() {
    }

    public LinkImage(String image_url) {
        this.image_url = image_url;
    }

    public String getImage_url() {
        return image_url;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }

    @Override
    public String toString() {
        return "LinkImage{" +
                "image_url='" + image_url + '\'' +
                '}';
    }
}
