package com.example.usuario.rekindlefrontend.data.entity.user;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Volunteer extends User {


    public static final Parcelable.Creator<Volunteer> CREATOR =
            new Parcelable.Creator<Volunteer>() {
                @Override
                public Volunteer createFromParcel(Parcel source) {
                    return new Volunteer(source);
                }

                @Override
                public Volunteer[] newArray(int size) {
                    return new Volunteer[size];
                }
            };
    @SerializedName("averageValoration")
    @Expose
    private double averageValoration;

    public Volunteer() {
        super();
    }

    public Volunteer(String mail, String password, String name, String surname1,
            String surname2, String photo) {
        super("Volunteer", mail, password, name, surname1, surname2, photo);
    }

    protected Volunteer(Parcel in) {
        super(in);
        this.averageValoration = in.readDouble();
    }

    public double getAverageValoration() {
        return averageValoration;
    }

    public void setAverageValoration(double averageValoration) {
        this.averageValoration = averageValoration;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        super.writeToParcel(dest, flags);
        dest.writeDouble(this.averageValoration);
    }

    @Override
    public String toString() {
        return "Volunteer{" +
                "tipo='" + getUserType() + '\'' +
                ", enabled=" + getEnabled() + '\'' +
                ", mail='" + getMail() + '\'' +
                ", password='" + getPassword() + '\'' +
                ", name='" + getName() + '\'' +
                ", surname1='" + getSurname1() + '\'' +
                ", surname2='" + getSurname2() + '\'' +
                ", photo='" + getPhoto() + '\'' +
                ", averageValoration=" + averageValoration +
                '}';
    }
}
