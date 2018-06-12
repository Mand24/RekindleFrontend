package com.example.usuario.rekindlefrontend.data.entity.service;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Education extends Service {
    @SerializedName("ambit")
    @Expose
    private String ambit;
    @SerializedName("requirements")
    @Expose
    private String requirements;
    @SerializedName("schedule")
    @Expose
    private String schedule;
    @SerializedName("places")
    @Expose
    private String placesLimit;
    @SerializedName("price")
    @Expose
    private String price;

    public Education(int id, String email, String name, String description, String adress,
            String ambit, String requirements, String schedule, String placesLimit, String price,
            String phoneNumber, Boolean
            ended) {
        super(id, "Education", email, name, description, adress, phoneNumber, ended);
        this.ambit = ambit;
        this.requirements = requirements;
        this.schedule = schedule;
        this.placesLimit = placesLimit;
        this.price = price;
    }

    public String getAmbit() {
        return ambit;
    }

    public void setAmbit(String ambit) {
        this.ambit = ambit;
    }

    public String getRequirements() {
        return requirements;
    }

    public void setRequirements(String requirements) {
        this.requirements = requirements;
    }

    public String getSchedule() {
        return schedule;
    }

    public void setSchedule(String schedule) {
        this.schedule = schedule;
    }

    public String getPlacesLimit() {
        return placesLimit;
    }

    public void setPlacesLimit(String placesLimit) {
        this.placesLimit = placesLimit;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Education{" +
                "id=" + getId() + '\'' +
                ", serviceType=" + getServiceType() + '\'' +
                ", volunteer='" + getEmail() + '\'' +
                ", name='" + getName() + '\'' +
                ", description='" + getDescription() + '\'' +
                ", adress='" + getAdress() + '\'' +
                ", phoneNumber='" + getPhoneNumber() + '\'' +
                ", ambit='" + ambit + '\'' +
                ", requirements='" + requirements + '\'' +
                ", schedule='" + schedule + '\'' +
                ", placesLimit='" + placesLimit + '\'' +
                ", price='" + price + '\'' +
                '}';
    }
}
