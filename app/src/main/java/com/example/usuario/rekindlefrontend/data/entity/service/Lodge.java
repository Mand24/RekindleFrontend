package com.example.usuario.rekindlefrontend.data.entity.service;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Lodge extends Service {
    @SerializedName("places")
    @Expose
    private String placesLimit;
    @SerializedName("dateLimit")
    @Expose
    private String dateLimit;

    public Lodge(int id, String email, String name, String description, String adress, Double positionLat, Double positionLng, String placesLimit, String dateLimit,
            String phoneNumber, Boolean ended, String expiresOn) {
        super(id, "Lodge", email, name, description, adress, positionLat, positionLng, phoneNumber,
                ended, expiresOn);
        this.placesLimit = placesLimit;
        this.dateLimit = dateLimit;
    }

    public String getPlacesLimit() {
        return placesLimit;
    }

    public void setPlacesLimit(String placesLimit) {
        this.placesLimit = placesLimit;
    }

    public String getDateLimit() {
        return dateLimit;
    }

    public void setDateLimit(String dateLimit) {
        this.dateLimit = dateLimit;
    }

    @Override
    public String toString() {
        return "Lodge{" +
                "id=" + getId() + '\'' +
                ", serviceType=" + getServiceType() + '\'' +
                ", volunteer='" + getEmail() + '\'' +
                ", name='" + getName() + '\'' +
                ", description='" + getDescription() + '\'' +
                ", adress='" + getAdress() + '\'' +
                ", phoneNumber='" + getPhoneNumber() + '\'' +
                ", placesLimit='" + placesLimit + '\'' +
                ", dateLimit='" + dateLimit + '\'' +
                '}';
    }
}
