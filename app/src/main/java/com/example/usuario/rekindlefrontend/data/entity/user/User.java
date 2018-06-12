package com.example.usuario.rekindlefrontend.data.entity.user;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.Base64;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class User implements Parcelable {

    @SerializedName("userType")
    @Expose
    private String userType;
    @SerializedName("mail")
    @Expose
    private String mail;
    @SerializedName("password")
    @Expose
    private String password;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("surname1")
    @Expose
    private String surname1;
    @SerializedName("surname2")
    @Expose
    private String surname2;
    @SerializedName("photo")
    @Expose
    private String photo;

    public User() {
    }


    public User(String userType, String mail, String password, String name, String surname1, String
            surname2, String photo) {
        this.userType = userType;
        this.mail = mail;
        this.password = password;
        this.name = name;
        this.surname1 = surname1;
        this.surname2 = surname2;
        this.photo = photo;
    }

    protected User(Parcel in) {
        userType = in.readString();
        mail = in.readString();
        password = in.readString();
        name = in.readString();
        surname1 = in.readString();
        surname2 = in.readString();
        photo = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(userType);
        dest.writeString(mail);
        dest.writeString(password);
        dest.writeString(name);
        dest.writeString(surname1);
        dest.writeString(surname2);
        dest.writeString(photo);
    }

    public static final Creator<User> CREATOR = new Creator<User>() {
        @Override
        public User createFromParcel(Parcel in) {
            return new User(in);
        }

        @Override
        public User[] newArray(int size) {
            return new User[size];
        }
    };

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public String getMail() {
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
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public Bitmap getDecodedPhoto() {
        byte[] decodedString = Base64.decode(getPhoto(), Base64.DEFAULT);
        return BitmapFactory.decodeByteArray(decodedString, 0,
                decodedString.length);
    }

    @Override
    public String toString() {
        return "User{" +
                "userType='" + userType + '\'' +
                ", mail='" + mail + '\'' +
                ", password='" + password + '\'' +
                ", name='" + name + '\'' +
                ", surname1='" + surname1 + '\'' +
                ", surname2='" + surname2 + '\'' +
                ", photo='" + photo + '\'' +
                '}';
    }
}
