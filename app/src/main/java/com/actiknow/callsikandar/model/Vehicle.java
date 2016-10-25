package com.actiknow.callsikandar.model;

/**
 * Created by actiknow on 10/25/16.
 */

public class Vehicle {
    private String model, registration_number, manufacturer, km_reading, last_service_date, fuel_type;

    public Vehicle (String model, String registration_number, String manufacturer, String km_reading, String last_service_date, String fuel_type) {
        this.model = model;
        this.registration_number = registration_number;
        this.manufacturer = manufacturer;
        this.km_reading = km_reading;
        this.last_service_date = last_service_date;
        this.fuel_type = fuel_type;
    }

    public String getModel () {
        return model;
    }

    public void setModel (String model) {
        this.model = model;
    }

    public String getRegistration_number () {
        return registration_number;
    }

    public void setRegistration_number (String registration_number) {
        this.registration_number = registration_number;
    }

    public String getManufacturer () {
        return manufacturer;
    }

    public void setManufacturer (String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String getKm_reading () {
        return km_reading;
    }

    public void setKm_reading (String km_reading) {
        this.km_reading = km_reading;
    }

    public String getLast_service_date () {
        return last_service_date;
    }

    public void setLast_service_date (String last_service_date) {
        this.last_service_date = last_service_date;
    }

    public String getFuel_type () {
        return fuel_type;
    }

    public void setFuel_type (String fuel_type) {
        this.fuel_type = fuel_type;
    }
}
