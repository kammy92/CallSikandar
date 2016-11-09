package com.actiknow.callsikandar.model;

/**
 * Created by l on 08/11/2016.
 */

public class ServiceQuotations {
    String name;

    public ServiceQuotations (String name) {
        this.name = name;
    }

    public ServiceQuotations () {

    }

    public String getName () {
        return name;
    }

    public void setName (String name) {
        this.name = name;
    }
}
