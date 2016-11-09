package com.actiknow.callsikandar.model;

/**
 * Created by l on 07/11/2016.
 */

public class ServiceProviderRating {

    int id;
    String name, rating, date, time, description;

    public ServiceProviderRating () {

    }

    public ServiceProviderRating (int id, String name, String rating, String date, String time, String description) {
        this.id = id;
        this.name = name;
        this.date = date;
        this.time = time;
        this.rating = rating;
        this.description = description;
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

    public String getRating () {
        return rating;
    }

    public void setRating (String rating) {
        this.rating = rating;
    }

    public String getDate () {
        return date;
    }

    public void setDate (String date) {
        this.date = date;
    }

    public String getTime () {
        return time;
    }

    public void setTime (String time) {
        this.time = time;
    }

    public String getDescription () {
        return description;
    }

    public void setDescription (String description) {
        this.description = description;
    }
}
