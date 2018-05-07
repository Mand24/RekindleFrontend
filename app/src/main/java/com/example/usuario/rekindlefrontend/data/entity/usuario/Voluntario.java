package com.example.usuario.rekindlefrontend.data.entity.usuario;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

public class Voluntario extends Usuario {

    public Voluntario(){
        super();
    }

    public Voluntario(String mail, String password, String name, String surname1,
            String surname2) {
        super(1, mail, password, name, surname1, surname2);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        super.writeToParcel(dest, flags);
    }

    protected Voluntario(Parcel in) {
        super(in);
    }

    public static final Parcelable.Creator<Voluntario> CREATOR =
            new Parcelable.Creator<Voluntario>() {
                @Override
                public Voluntario createFromParcel(Parcel source) {
                    return new Voluntario(source);
                }

                @Override
                public Voluntario[] newArray(int size) {
                    return new Voluntario[size];
                }
            };
}
