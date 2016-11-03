package com.actiknow.callsikandar.model;

/**
 * Created by l on 27/10/2016.
 */

public class Service {
    int id, type_id;
    String name, description, icon, type_name;

    public Service (int id, int type_id, String name, String description, String icon, String type_name) {
        this.id = id;
        this.type_id = type_id;
        this.name = name;
        this.description = description;
        this.icon = icon;
        this.type_name = type_name;
    }

    public int getId () {
        return id;
    }

    public void setId (int id) {
        this.id = id;
    }

    public int getType_id () {
        return type_id;
    }

    public void setType_id (int type_id) {
        this.type_id = type_id;
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

    public String getIcon () {
        return icon;
    }

    public void setIcon (String icon) {
        this.icon = icon;
    }

    public String getType_name () {
        return type_name;
    }

    public void setType_name (String type_name) {
        this.type_name = type_name;
    }
}
