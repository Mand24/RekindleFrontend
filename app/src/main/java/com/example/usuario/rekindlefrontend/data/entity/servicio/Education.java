package com.example.usuario.rekindlefrontend.data.entity.servicio;

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
    private String places;
    @SerializedName("price")
    @Expose
    private String price;

    public Education(int id, String email, String name, String description, String adress,
            String ambit, String requirements, String schedule, String places, String price,
            String phoneNumber) {
        super(id, "Education", email, name, description, adress, phoneNumber);
        this.ambit = ambit;
        this.requirements = requirements;
        this.schedule = schedule;
        this.places = places;
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

    public String getPlaces() {
        return places;
    }

    public void setPlaces(String places) {
        this.places = places;
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
                "ambit='" + ambit + '\'' +
                ", requirements='" + requirements + '\'' +
                ", schedule='" + schedule + '\'' +
                ", places='" + places + '\'' +
                ", price='" + price + '\'' +
                '}';
    }
}
