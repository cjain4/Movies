package com.example.myapplication;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;
import java.util.ArrayList;

public class AddMovie implements Parcelable {
    String name1;
    String desc;
    String yearr;
    String imdb;
    String movie_rating;
    String movie_category;
    protected AddMovie(Parcel in) {
        this.name1 =  in.readString();
        this.desc = in.readString();
        this.yearr = in.readString();
        this.imdb = in.readString();
        this.movie_rating = in.readString();
        this.movie_category = in.readString();
    }

    @Override
    public String toString() {
        return "AddMovie{" +
                "name1='" + name1 + '\'' +
                ", desc='" + desc + '\'' +
                ", yearr='" + yearr + '\'' +
                ", imdb='" + imdb + '\'' +
                ", movie_rating='" + movie_rating + '\'' +
                ", movie_category='" + movie_category + '\'' +
                '}';
    }

    public AddMovie(String name1, String desc, String yearr, String imdb, String movie_rating, String movie_category) {
        this.name1 = name1;
        this.desc = desc;
        this.yearr = yearr;
        this.imdb = imdb;
        this.movie_rating = movie_rating;
        this.movie_category = movie_category;
    }

    public static final Creator<AddMovie> CREATOR = new Creator<AddMovie>() {
        @Override
        public AddMovie createFromParcel(Parcel in) {
            return new AddMovie(in);
        }

        @Override
        public AddMovie[] newArray(int size) {
            return new AddMovie[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.name1);
        dest.writeString(this.desc);
        dest.writeString(this.yearr);
        dest.writeString(this.imdb);
        dest.writeString(this.movie_rating);
        dest.writeString(this.movie_category);
    }
}
