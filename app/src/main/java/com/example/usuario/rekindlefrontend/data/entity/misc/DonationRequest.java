package com.example.usuario.rekindlefrontend.data.entity.misc;

import android.os.Parcel;
import android.os.Parcelable;

import com.example.usuario.rekindlefrontend.data.entity.service.Donation;
import com.example.usuario.rekindlefrontend.data.entity.user.User;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DonationRequest implements Parcelable {

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

    protected DonationRequest(Parcel in) {
        refugeeMail = in.readString();
        donation = in.readParcelable(Donation.class.getClassLoader());
        motive = in.readString();
    }

    public String getRefugeeMail() { return this.refugeeMail; }

    public Donation getDonation() { return this.donation; }

    public String getMotive() { return this.motive; }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(refugeeMail);
        dest.writeSerializable(donation);
        dest.writeString(motive);
    }

    public static final Parcelable.Creator<DonationRequest> CREATOR = new Parcelable.Creator<DonationRequest>
            () {
        @Override
        public DonationRequest createFromParcel(Parcel in) {
            return new DonationRequest(in);
        }

        @Override
        public DonationRequest[] newArray(int size) {
            return new DonationRequest[size];
        }
    };

    @Override
    public String toString() {
        return "Report{" +
                ", refugeeMail=" + refugeeMail +
                ", donation=" + donation +
                ", motive='" + motive + '\'' +
                '}';
    }
}

