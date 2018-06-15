package com.example.usuario.rekindlefrontend.data.entity.service;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Donation extends Service {
    @SerializedName("places")
    @Expose
    private String placesLimit;
    @SerializedName("startTime")
    @Expose
    private String startTime;
    @SerializedName("endTime")
    @Expose
    private String endTime;


    public Donation(int id, String email, String name, String description, String adress, Double
            positionLat, Double positionLng,
            String placesLimit, String startTime, String endTime, String phoneNumber, Boolean
            ended, String expiresOn) {
        super(id, "Donation", email, name, description, adress, positionLat, positionLng, phoneNumber,
                ended, expiresOn);

        this.placesLimit = placesLimit;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public String getPlacesLimit() {
        return placesLimit;
    }

    public void setPlacesLimit(String placesLimit) {
        this.placesLimit = placesLimit;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    @Override
    public String toString() {
        return "Donation{" +
                "id=" + getId() + '\'' +
                ", serviceType=" + getServiceType() + '\'' +
                ", volunteer='" + getEmail() + '\'' +
                ", name='" + getName() + '\'' +
                ", description='" + getDescription() + '\'' +
                ", adress='" + getAdress() + '\'' +
                ", phoneNumber='" + getPhoneNumber() + '\'' +
                ", placesLimit='" + placesLimit + '\'' +
                ", startTime='" + startTime + '\'' +
                ", endTime='" + endTime + '\'' +
                '}';
    }
}
