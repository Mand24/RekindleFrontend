package com.example.usuario.rekindlefrontend.data.entity.user;

import android.os.Parcel;
import android.os.Parcelable;

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

    public Volunteer() {
        super();
    }

    public Volunteer(String mail, String password, String name, String surname1,
            String surname2, String photo) {
        super("Volunteer", mail, password, name, surname1, surname2, photo);
    }

    protected Volunteer(Parcel in) {
        super(in);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        super.writeToParcel(dest, flags);
    }
}
