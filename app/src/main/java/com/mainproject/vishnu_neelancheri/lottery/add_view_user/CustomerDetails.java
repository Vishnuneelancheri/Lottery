package com.mainproject.vishnu_neelancheri.lottery.add_view_user;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Vishnu Neelancheri 9633647027 on 12/27/2017.
 */

public class CustomerDetails implements Parcelable {
    private int id;
    private String name, email, mobile, code;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.email);
        dest.writeInt(this.id);
        dest.writeString(this.name);
        dest.writeString(this.mobile);
        dest.writeString(this.code);
    }

    public CustomerDetails() {
    }

    protected CustomerDetails(Parcel in) {
        this.email = in.readString();
        this.id = in.readInt();
        this.name = in.readString();
        this.mobile = in.readString();
        this.code = in.readString();
    }

    public static final Parcelable.Creator<CustomerDetails> CREATOR = new Parcelable.Creator<CustomerDetails>() {
        @Override
        public CustomerDetails createFromParcel(Parcel source) {
            return new CustomerDetails(source);
        }

        @Override
        public CustomerDetails[] newArray(int size) {
            return new CustomerDetails[size];
        }
    };
}
