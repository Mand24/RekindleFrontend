package com.example.usuario.rekindlefrontend.data.entity.reports;


import android.os.Parcel;
import android.os.Parcelable;

import com.example.usuario.rekindlefrontend.data.entity.usuario.Usuario;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Report implements Parcelable{
    @SerializedName("idReport")
    @Expose
    private int idReport;

    @SerializedName("informerUser")
    @Expose
    private Usuario informerUser;

    @SerializedName("reportedUser")
    @Expose
    private Usuario reportedUser;

    @SerializedName("motive")
    @Expose
    private String motive;

    public Report(){}

    public Report(Usuario informerUser, Usuario reportedUser, String motive){
        this.informerUser = informerUser;
        this.reportedUser = reportedUser;
        this.motive = motive;
    }

    protected Report(Parcel in) {
        idReport = in.readInt();
        informerUser = in.readParcelable(Usuario.class.getClassLoader());
        reportedUser = in.readParcelable(Usuario.class.getClassLoader());
        motive = in.readString();
    }

    public static final Creator<Report> CREATOR = new Creator<Report>() {
        @Override
        public Report createFromParcel(Parcel in) {
            return new Report(in);
        }

        @Override
        public Report[] newArray(int size) {
            return new Report[size];
        }
    };

    public String getInformerUserMail(){
        return this.informerUser.getMail();
    }

    public String getReportedUserMail(){
        return this.informerUser.getMail();
    }

    public String getMotive(){
        return this.motive;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(idReport);
        dest.writeParcelable(informerUser, flags);
        dest.writeParcelable(reportedUser, flags);
        dest.writeString(motive);
    }

    @Override
    public String toString() {
        return "Report{" +
                "idReport=" + idReport +
                ", informerUser=" + informerUser +
                ", reportedUser=" + reportedUser +
                ", motive='" + motive + '\'' +
                '}';
    }
}
