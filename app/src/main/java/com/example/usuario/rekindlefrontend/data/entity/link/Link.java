package com.example.usuario.rekindlefrontend.data.entity.link;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Link implements Parcelable{

    @SerializedName("id")
    @Expose
    private int idLink;
    @SerializedName("type")
    @Expose
    private String type;
    @SerializedName("url")
    @Expose
    private String url;
    @SerializedName("description")
    @Expose
    private String description;

    public Link() {
    }

    public Link(String type, String url, String description) {
        this.type = type;
        this.url = url;
        this.description = description;
    }

    protected Link(Parcel in) {
        idLink = in.readInt();
        type = in.readString();
        url = in.readString();
        description = in.readString();
    }

    public static final Creator<Link> CREATOR = new Creator<Link>() {
        @Override
        public Link createFromParcel(Parcel in) {
            return new Link(in);
        }

        @Override
        public Link[] newArray(int size) {
            return new Link[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(idLink);
        dest.writeString(type);
        dest.writeString(url);
        dest.writeString(description);
    }

    public int getIdLink() {
        return idLink;
    }

    public void setIdLink(int idLink) {
        this.idLink = idLink;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Link{" +
                "idLink=" + idLink +
                ", type='" + type + '\'' +
                ", url='" + url + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
