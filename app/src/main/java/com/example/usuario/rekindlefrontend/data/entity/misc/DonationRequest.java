package com.example.usuario.rekindlefrontend.data.entity.misc;

import android.os.Parcel;
import android.os.Parcelable;

import com.example.usuario.rekindlefrontend.data.entity.service.Donation;
import com.example.usuario.rekindlefrontend.data.entity.user.User;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class DonationRequest implements Serializable {

    @SerializedName("refugeeMail")
    @Expose
    private String refugeeMail;
    @SerializedName("donation")
    @Expose
    private Donation donation;
    @SerializedName("motive")
    @Expose
    private String motive;

    public DonationRequest() {

    }

    public DonationRequest(User user, Donation donation, String motive) {
        this.refugeeMail = user.getMail();
        this.donation = donation;
        this.motive = motive;
    }

    public String getRefugeeMail() {
        return this.refugeeMail;
    }

    public Donation getDonation() {
        return this.donation;
    }

    public String getMotive() {
        return this.motive;
    }

    @Override
    public String toString() {
        return "DonationRequest{" +
                "refugeeMail='" + refugeeMail + '\'' +
                ", donation=" + donation +
                ", motive='" + motive + '\'' +
                '}';
    }
}

