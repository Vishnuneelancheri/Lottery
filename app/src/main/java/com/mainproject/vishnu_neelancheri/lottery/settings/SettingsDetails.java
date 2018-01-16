package com.mainproject.vishnu_neelancheri.lottery.settings;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Vishnu Neelancheri, email: vishnuvishnuneelan@gmail.com on 12/30/2017.
 */

public class SettingsDetails implements Parcelable {
    private int settingsId, first_price, second_price, third_price, isActivated;
    private String date;

    public int getSettingsId() {
        return settingsId;
    }

    public void setSettingsId(int settingsId) {
        this.settingsId = settingsId;
    }

    public int getFirst_price() {
        return first_price;
    }

    public void setFirst_price(int first_price) {
        this.first_price = first_price;
    }

    public int getSecond_price() {
        return second_price;
    }

    public void setSecond_price(int second_price) {
        this.second_price = second_price;
    }

    public int getThird_price() {
        return third_price;
    }

    public void setThird_price(int third_price) {
        this.third_price = third_price;
    }

    public int isActivated() {
        return isActivated;
    }

    public void setActivated(int activated) {
        isActivated = activated;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.settingsId);
        dest.writeInt(this.first_price);
        dest.writeInt(this.second_price);
        dest.writeInt(this.third_price);
        dest.writeInt(this.isActivated);
        dest.writeString(this.date);
    }

    public SettingsDetails() {
    }

    protected SettingsDetails(Parcel in) {
        this.settingsId = in.readInt();
        this.first_price = in.readInt();
        this.second_price = in.readInt();
        this.third_price = in.readInt();
        this.isActivated = in.readInt();
        this.date = in.readString();
    }

    public static final Parcelable.Creator<SettingsDetails> CREATOR = new Parcelable.Creator<SettingsDetails>() {
        @Override
        public SettingsDetails createFromParcel(Parcel source) {
            return new SettingsDetails(source);
        }

        @Override
        public SettingsDetails[] newArray(int size) {
            return new SettingsDetails[size];
        }
    };
}
