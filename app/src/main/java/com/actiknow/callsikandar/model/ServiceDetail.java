package com.actiknow.callsikandar.model;

/**
 * Created by l on 27/10/2016.
 */

public class ServiceDetail {
    int id, charges, service_id;
    String distance, service_name, service_provider_name, address_2, rating, icon;

    public ServiceDetail (int id, String distance, int charges, int service_id, String service_name, String service_provider, String address_2, String rating, String icon) {
        this.id = id;
        this.distance = distance;
        this.charges = charges;
        this.service_id = service_id;
        this.service_name = service_name;
        this.service_provider_name = service_provider;
        this.address_2 = address_2;
        this.rating = rating;
        this.icon = icon;
    }

    public int getService_id () {
        return service_id;
    }

    public void setService_id (int service_id) {
        this.service_id = service_id;
    }

    public String getRating () {
        return rating;
    }

    public void setRating (String rating) {
        this.rating = rating;
    }

    public int getId () {
        return id;
    }

    public void setId (int id) {
        this.id = id;
    }

    public String getService_name () {
        return service_name;
    }

    public void setService_name (String service_name) {
        this.service_name = service_name;
    }

    public String getService_provider_name () {
        return service_provider_name;
    }

    public void setService_provider_name (String service_provider_name) {
        this.service_provider_name = service_provider_name;
    }

    public String getAddress_2 () {
        return address_2;
    }

    public void setAddress_2 (String address_2) {
        this.address_2 = address_2;
    }

    public String getDistance () {
        return distance;
    }

    public void setDistance (String distance) {
        this.distance = distance;
    }

    public int getCharges () {
        return charges;
    }

    public void setCharges (int charges) {
        this.charges = charges;
    }

    public String getIcon () {
        return icon;
    }

    public void setIcon (String icon) {
        this.icon = icon;
    }
}
