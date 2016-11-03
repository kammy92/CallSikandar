package com.actiknow.callsikandar.model;

/**
 * Created by Admin on 14-09-2016.
 */
public class Banner {
    int banner_id, product_id, category_id;
    String text, image_url, type;

    public Banner (int banner_id, int product_id, int category_id, String text, String image_url, String type) {
        this.banner_id = banner_id;
        this.product_id = product_id;
        this.category_id = category_id;
        this.text = text;
        this.image_url = image_url;
        this.type = type;
    }

    public int getBanner_id () {
        return banner_id;
    }

    public void setBanner_id (int banner_id) {
        this.banner_id = banner_id;
    }

    public int getProduct_id () {
        return product_id;
    }

    public void setProduct_id (int product_id) {
        this.product_id = product_id;
    }

    public int getCategory_id () {
        return category_id;
    }

    public void setCategory_id (int category_id) {
        this.category_id = category_id;
    }

    public String getText () {
        return text;
    }

    public void setText (String text) {
        this.text = text;
    }

    public String getImage_url () {
        return image_url;
    }

    public void setImage_url (String image_url) {
        this.image_url = image_url;
    }

    public String getType () {
        return type;
    }

    public void setType (String type) {
        this.type = type;
    }
}
