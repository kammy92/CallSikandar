package com.actiknow.callsikandar.model;

/**
 * Created by l on 07/11/2016.
 */

public class ServiceProviderPackage {

    int id;
    String name, price, description, image;

    public ServiceProviderPackage () {

    }

    public ServiceProviderPackage (int id, String name, String description, String price, String image) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.description = description;
        this.image = image;

    }

    public String getImage () {
        return image;
    }

    public void setImage (String image) {
        this.image = image;
    }

    public String getPrice () {
        return price;
    }

    public void setPrice (String price) {
        this.price = price;
    }

    public int getId () {
        return id;
    }

    public void setId (int id) {
        this.id = id;
    }

    public String getName () {
        return name;
    }

    public void setName (String name) {
        this.name = name;
    }

    public String getDescription () {
        return description;
    }

    public void setDescription (String description) {
        this.description = description;
    }
}
