package com.actiknow.callsikandar.model;

/**
 * Created by l on 07/11/2016.
 */

public class ServiceRequest {

    int requestId;
    String repairName, repairDescription, repairIcon;

    public ServiceRequest (int requestId, String repairName, String repairDescription, String repairIcon) {
        this.repairName = repairName;
        this.repairDescription = repairDescription;
        this.repairIcon = repairIcon;
        this.requestId = requestId;
    }

    public ServiceRequest () {
    }

    public String getRepairName () {
        return repairName;
    }

    public void setRepairName (String repairName) {
        this.repairName = repairName;
    }

    public int getRequestId () {
        return requestId;
    }

    public void setRequestId (int requestId) {
        this.requestId = requestId;
    }

    public String getRepairDescription () {
        return repairDescription;
    }

    public void setRepairDescription (String repairDescription) {
        this.repairDescription = repairDescription;
    }

    public String getRepairIcon () {
        return repairIcon;
    }

    public void setRepairIcon (String repairIcon) {
        this.repairIcon = repairIcon;
    }
}
