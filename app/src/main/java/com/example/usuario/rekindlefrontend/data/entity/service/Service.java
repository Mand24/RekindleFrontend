package com.example.usuario.rekindlefrontend.data.entity.service;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Service implements Serializable {
    @SerializedName("id")
    @Expose
    private int id;
    @SerializedName("serviceType")
    @Expose
    private String serviceType;
    @SerializedName("volunteer")
    @Expose
    private String volunteer;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("adress")
    @Expose
    private String adress;
    @SerializedName("phoneNumber")
    @Expose
    private String phoneNumber;
    @SerializedName("ended")
    @Expose
    private Boolean ended;
    @SerializedName("positionLat")
    @Expose
    private Double positionLat;
    @SerializedName("positionLng")
    @Expose
    private Double positionLng;

    private int image;


    public Service(int id, String serviceType, String email, String name, String description, String
            adress, Double positionLat, Double positionLng, String phoneNumber, Boolean ended) {
        this.id = id;
        this.serviceType = serviceType;
        this.volunteer = email;
        this.name = name;
        this.description = description;
        this.adress = adress;
        this.positionLat = positionLat;
        this.positionLng = positionLng;
        this.phoneNumber = phoneNumber;
        this.ended = ended;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getServiceType() {
        return serviceType;
    }

    public void setServiceType(String serviceType) {
        this.serviceType = serviceType;
    }

    public String getEmail() {
        return volunteer;
    }

    public void setEmail(String email) {
        this.volunteer = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

    public Double getPositionLat(){
        return positionLat;
    }

    public void setPositionLat(Double positionLat){
        this.positionLat = positionLat;
    }

    public Double getPositionLng(){
        return positionLng;
    }

    public void setPositionLng(Double positionLng){
        this.positionLng = positionLng;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public Boolean getEnded() {
        return ended;
    }

    public void setEnded(Boolean ended) {
        this.ended = ended;
    }

    @Override
    public String toString() {
        return "Service{" +
                "id=" + id +
                ", serviceType='" + serviceType + '\'' +
                ", volunteer='" + volunteer + '\'' +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", adress='" + adress + '\'' +
                ", positionLat'"+ positionLat + '\'' +
                ", positionLng'"+ positionLng + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", ended=" + ended +
                ", image=" + image +
                '}';
    }
}
