package com.example.usuario.rekindlefrontend.data.entity.user;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Refugee extends User {

    public static final Parcelable.Creator<Refugee> CREATOR =
            new Parcelable.Creator<Refugee>() {
                @Override
                public Refugee createFromParcel(Parcel source) {
                    return new Refugee(source);
                }

                @Override
                public Refugee[] newArray(int size) {
                    return new Refugee[size];
                }
            };
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
    @SerializedName("biography")
    @Expose
    private String biography;

    public Refugee() {
        super();
    }

    public Refugee(String mail, String password, String name, String surname1,
            String surname2, String photo, String phoneNumber, String birthDate, String sex, String
            country,
            String town, String ethnic, String bloodType, String eyeColor, String biography) {
        super("Refugee", mail, password, name, surname1, surname2, photo);
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

    protected Refugee(Parcel in) {
        super(in);
        this.phoneNumber = in.readString();
        this.birthDate = in.readString();
        this.sex = in.readString();
        this.country = in.readString();
        this.town = in.readString();
        this.ethnic = in.readString();
        this.bloodType = in.readString();
        this.eyeColor = in.readString();
        this.biography = in.readString();
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

    public String getBiography() {
        return biography;
    }

    public void setBiography(String biography) {
        this.biography = biography;
    }

    @Override
    public String toString() {
        return "Refugee{" +
                "tipo='" + getUserType() + '\'' +
                ", enabled=" + getEnabled() + '\'' +
                ", mail='" + getMail() + '\'' +
                ", password='" + getPassword() + '\'' +
                ", name='" + getName() + '\'' +
                ", surname1='" + getSurname1() + '\'' +
                ", surname2='" + getSurname2() + '\'' +
                ", photo='" + getPhoto() + '\'' +
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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        super.writeToParcel(dest, flags);
        dest.writeString(this.phoneNumber);
        dest.writeString(this.birthDate);
        dest.writeString(this.sex);
        dest.writeString(this.country);
        dest.writeString(this.town);
        dest.writeString(this.ethnic);
        dest.writeString(this.bloodType);
        dest.writeString(this.eyeColor);
        dest.writeString(this.biography);
    }
}
