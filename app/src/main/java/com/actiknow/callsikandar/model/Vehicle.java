package com.actiknow.callsikandar.model;

/**
 * Created by actiknow on 10/25/16.
 */

public class Vehicle {
    int id;
    private String make_and_model, registration_number, year_of_manufacture, km_reading, last_service_date, fuel_type;

    public Vehicle (int id, String make_and_model, String registration_number, String year_of_manufacture, String km_reading, String last_service_date, String fuel_type) {
        this.id = id;
        this.make_and_model = make_and_model;
        this.registration_number = registration_number;
        this.year_of_manufacture = year_of_manufacture;
        this.km_reading = km_reading;
        this.last_service_date = last_service_date;
        this.fuel_type = fuel_type;
    }

    public int getId () {
        return id;
    }

    public void setId (int id) {
        this.id = id;
    }

    public String getMake_and_model () {
        return make_and_model;
    }

    public void setMake_and_model (String make_and_model) {
        this.make_and_model = make_and_model;
    }

    public String getRegistration_number () {
        return registration_number;
    }

    public void setRegistration_number (String registration_number) {
        this.registration_number = registration_number;
    }

    public String getYear_of_manufacture () {
        return year_of_manufacture;
    }

    public void setYear_of_manufacture (String year_of_manufacture) {
        this.year_of_manufacture = year_of_manufacture;
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
