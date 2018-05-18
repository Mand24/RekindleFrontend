package com.example.usuario.rekindlefrontend.data.entity.reports;


import com.example.usuario.rekindlefrontend.data.entity.usuario.Usuario;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Report{
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
}
