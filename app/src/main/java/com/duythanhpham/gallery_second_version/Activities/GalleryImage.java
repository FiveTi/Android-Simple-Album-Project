package com.duythanhpham.gallery_second_version.Activities;

import java.io.Serializable;

public class GalleryImage implements Serializable {
    private String image_title;
    private Integer image_id;

    public GalleryImage()
    {
        image_title = "";
        image_id = 0;
    }

    public GalleryImage(String image_title, Integer image_id)
    {
        this.image_title = image_title;
        this.image_id = image_id;
    }

    public String getImage_title() {
        return image_title;
    }

    public void setImage_title(String android_version_name) {
        this.image_title = android_version_name;
    }

    public Integer getImage_ID() {
        return image_id;
    }

    public void setImage_ID(Integer android_image_url) {
        this.image_id = android_image_url;
    }
}
