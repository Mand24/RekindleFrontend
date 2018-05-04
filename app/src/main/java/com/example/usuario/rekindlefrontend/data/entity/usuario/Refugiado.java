package com.example.usuario.rekindlefrontend.data.entity.usuario;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Refugiado extends Usuario {

    /*@SerializedName("mail")
    @Expose
    protected String mail;
    @SerializedName("password")
    @Expose
    protected String password;
    @SerializedName("name")
    @Expose
    protected String name;
    @SerializedName("surname1")
    @Expose
    protected String surname1;
    @SerializedName("surname2")
    @Expose
    protected String surname2;*/

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
    @SerializedName("eyeColor")
    @Expose
    private String biography;


    public Refugiado(){
        super();
    }

//    public Refugiado(){}

    /*public Refugiado(String mail, String password, String name, String surname1,
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
    }*/

    public Refugiado(String mail, String password, String name, String surname1,
            String surname2, String phoneNumber, String birthDate, String sex, String country,
            String town, String ethnic, String bloodType, String eyeColor, String biography) {
        super(0, mail, password, name, surname1, surname2);
        /*this.mail = mail;
        this.password = password;
        this.name = name;
        this.surname1 = surname1;
        this.surname2 = surname2;*/
        this.phoneNumber = phoneNumber;
        this.birthDate = birthDate;
        this.sex = sex;
        this.country = country;
        this.town = town;
        this.ethnic = ethnic;
        this.bloodType = bloodType;
        this.eyeColor = eyeColor;
        this.biography = biography;
    }

    /*public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname1() {
        return surname1;
    }

    public void setSurname1(String surname1) {
        this.surname1 = surname1;
    }

    public String getSurname2() {
        return surname2;
    }

    public void setSurname2(String surname2) {
        this.surname2 = surname2;
    }*/

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

    public String getBiography() {
        return biography;
    }

    public void setBiography(String eyeColor) {
        this.biography = biography;
    }

    /*@Override
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
    }*/

    @Override
    public String toString() {
        return "Refugiado{" +
                "tipo='" + getTipo() + '\'' +
                "mail='" + getMail() + '\'' +
                ", password='" + getPassword() + '\'' +
                ", name='" + getName() + '\'' +
                ", surname1='" + getSurname1() + '\'' +
                ", surname2='" + getSurname2() + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", birthDate='" + birthDate + '\'' +
                ", sex='" + sex + '\'' +
                ", country='" + country + '\'' +
                ", town='" + town + '\'' +
                ", ethnic='" + ethnic + '\'' +
                ", bloodType='" + bloodType + '\'' +
                ", eyeColor='" + eyeColor + '\'' +
                ", biography='" + biography + '\'' +
                '}';
    }
}
