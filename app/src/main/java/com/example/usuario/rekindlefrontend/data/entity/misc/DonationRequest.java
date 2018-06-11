package com.example.usuario.rekindlefrontend.data.entity.misc;

import android.os.Parcel;
import android.os.Parcelable;

import com.example.usuario.rekindlefrontend.data.entity.service.Donation;
import com.example.usuario.rekindlefrontend.data.entity.user.User;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DonationRequest implements Parcelable {
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
    @SerializedName("idRequest")
    @Expose
    private int idRequest;
    @SerializedName("user")
    @Expose
    private User user;
    @SerializedName("donation")
    @Expose
    private Donation donation;
    @SerializedName("motive")
    @Expose
    private String motive;

    public DonationRequest() {

    }

    public DonationRequest(User user, Donation donation, String motive) {
        this.user = user;
        this.donation = donation;
        this.motive = motive;
    }

    protected DonationRequest(Parcel in) {
        idRequest = in.readInt();
        user = in.readParcelable(User.class.getClassLoader());
        donation = in.readParcelable(Donation.class.getClassLoader());
        motive = in.readString();
    }

    public User getUser() { return this.user; }

    public Donation getDonation() { return this.donation; }

    public String getMotive() { return this.motive; }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(idRequest);
        dest.writeParcelable(user, flags);
        dest.writeSerializable(donation);
        dest.writeString(motive);
    }

    @Override
    public String toString() {
        return "Report{" +
                "idRequest=" + idRequest +
                ", user=" + user +
                ", donation=" + donation +
                ", motive='" + motive + '\'' +
                '}';
    }
}

