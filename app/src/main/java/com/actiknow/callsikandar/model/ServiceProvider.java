package com.actiknow.callsikandar.model;

/**
 * Created by Admin on 26-10-2016.
 */

public class ServiceProvider {
    int id;
    String name, distance, address, rating;

    public ServiceProvider (int id, String name, String distance, String address, String rating) {
        this.id = id;
        this.name = name;
        this.distance = distance;
        this.address = address;
        this.rating = rating;
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

    public String getDistance () {
        return distance;
    }

    public void setDistance (String distance) {
        this.distance = distance;
    }

    public String getAddress () {
        return address;
    }

    public void setAddress (String address) {
        this.address = address;
    }

    public String getRating () {
        return rating;
    }

    public void setRating (String rating) {
        this.rating = rating;
    }
}
