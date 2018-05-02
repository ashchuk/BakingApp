package com.ashchuk.bakingapp.mvp.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

// Generated using Android Parcelable code generator
// https://github.com/mcharmas/android-parcelable-intellij-plugin/


public class Step implements Parcelable {
    @Expose
    @SerializedName("id")
    private byte stepId;
    @Expose
    @SerializedName("shortDescription")
    private String shortDescription;
    @Expose
    @SerializedName("description")
    private String description;
    @Expose
    @SerializedName("videoURL")
    private String videoURL;
    @Expose
    @SerializedName("thumbnailURL")
    private String thumbnailURL;

    public Step() {
    }

    protected Step(Parcel in) {
        stepId = in.readByte();
        shortDescription = in.readString();
        description = in.readString();
        videoURL = in.readString();
        thumbnailURL = in.readString();
    }

    public static final Creator<Step> CREATOR = new Creator<Step>() {
        @Override
        public Step createFromParcel(Parcel in) {
            return new Step(in);
        }

        @Override
        public Step[] newArray(int size) {
            return new Step[size];
        }
    };

    public byte getStepId() {
        return stepId;
    }

    public void setStepId(byte stepId) {
        this.stepId = stepId;
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getVideoURL() {
        return videoURL;
    }

    public void setVideoURL(String videoURL) {
        this.videoURL = videoURL;
    }

    public String getThumbnailURL() {
        return thumbnailURL;
    }

    public void setThumbnailURL(String thumbnailURL) {
        this.thumbnailURL = thumbnailURL;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeByte(stepId);
        dest.writeString(shortDescription);
        dest.writeString(description);
        dest.writeString(videoURL);
        dest.writeString(thumbnailURL);
    }
}