package com.example.usuario.rekindlefrontend.data.entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Refugiado extends Usuario {

    @SerializedName("phoneNumber")
    @Expose
    private String phoneNumber;
    @SerializedName("birthDate")
    @Expose
    private String birthDate;
    @SerializedName("sex")
    @Expose
    private String sex;
    @SerializedName("country")
    @Expose
    private String country;
    @SerializedName("town")
    @Expose
    private String town;
    @SerializedName("ethnic")
    @Expose
    private String ethnic;
    @SerializedName("bloodType")
    @Expose
    private String bloodType;
    @SerializedName("eyeColor")
    @Expose
    private String eyeColor;


    public Refugiado(){
        super();
    }

    public Refugiado(String mail, String password, String name, String surname1,
            String surname2, String phoneNumber, String birthDate, String sex, String country,
            String town, String ethnic, String bloodType, String eyeColor) {
        super(mail, password, name, surname1, surname2);
        this.phoneNumber = phoneNumber;
        this.birthDate = birthDate;
        this.sex = sex;
        this.country = country;
        this.town = town;
        this.ethnic = ethnic;
        this.bloodType = bloodType;
        this.eyeColor = eyeColor;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getTown() {
        return town;
    }

    public void setTown(String town) {
        this.town = town;
    }

    public String getEthnic() {
        return ethnic;
    }

    public void setEthnic(String ethnic) {
        this.ethnic = ethnic;
    }

    public String getBloodType() {
        return bloodType;
    }

    public void setBloodType(String bloodType) {
        this.bloodType = bloodType;
    }

    public String getEyeColor() {
        return eyeColor;
    }

    public void setEyeColor(String eyeColor) {
        this.eyeColor = eyeColor;
    }

    @Override
    public String toString() {
        return "Refugiado{" +
                "phoneNumber='" + phoneNumber + '\'' +
                ", birthDate='" + birthDate + '\'' +
                ", sex='" + sex + '\'' +
                ", country='" + country + '\'' +
                ", town='" + town + '\'' +
                ", ethnic='" + ethnic + '\'' +
                ", bloodType='" + bloodType + '\'' +
                ", eyeColor='" + eyeColor + '\'' +
                '}';
    }
}
