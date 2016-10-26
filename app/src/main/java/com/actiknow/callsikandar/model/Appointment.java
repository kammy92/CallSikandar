package com.actiknow.callsikandar.model;

/**
 * Created by Admin on 26-10-2016.
 */

public class Appointment {
    int id;
    String name, car_registration_number, service_type, status;

    public Appointment (int id, String name, String car_registration_number, String service_type, String status) {
        this.id = id;
        this.name = name;
        this.car_registration_number = car_registration_number;
        this.service_type = service_type;
        this.status = status;
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

    public String getCar_registration_number () {
        return car_registration_number;
    }

    public void setCar_registration_number (String car_registration_number) {
        this.car_registration_number = car_registration_number;
    }

    public String getService_type () {
        return service_type;
    }

    public void setService_type (String service_type) {
        this.service_type = service_type;
    }

    public String getStatus () {
        return status;
    }

    public void setStatus (String status) {
        this.status = status;
    }
}
