package com.example.usuario.rekindlefrontend.data.entity.service;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Valoration implements Serializable {
    @SerializedName("idService")
    @Expose
    private int idService;
    @SerializedName("serviceType")
    @Expose
    private String serviceType;
    @SerializedName("mailRefugee")
    @Expose
    private String mailRefugee;
    @SerializedName("points")
    @Expose
    private Float points;

    public Valoration(int idService, String serviceType, String mailRefugee, Float points) {
        this.idService = idService;
        this.serviceType = serviceType;
        this.mailRefugee = mailRefugee;
        this.points = points;
    }

    public int getIdService() {
        return idService;
    }

    public void setIdService(int idService) {
        this.idService = idService;
    }

    public String getServiceType() {
        return serviceType;
    }

    public void setServiceType(String serviceType) {
        this.serviceType = serviceType;
    }

    public String getMailRefugee() {
        return mailRefugee;
    }

    public void setMailRefugee(String mailRefugee) {
        this.mailRefugee = mailRefugee;
    }

    public Float getPoints() {
        return points;
    }

    public void setPoints(Float points) {
        this.points = points;
    }

    @Override
    public String toString() {
        return "Valoration{" +
                "idService=" + idService +
                ", serviceType='" + serviceType + '\'' +
                ", mailRefugee='" + mailRefugee + '\'' +
                ", points=" + points +
                '}';
    }
}
