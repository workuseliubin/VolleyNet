package com.mrliu.volleynet.login;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;


public class LoginBean implements Parcelable, Serializable {
    private static final long serialVersionUID = -3861900080725089863L;
    public String token;
    public String expiresIn;

    public LoginBean() {
    }

    protected LoginBean(Parcel in) {
        token = in.readString();
        expiresIn = in.readString();
    }

    public static final Creator<LoginBean> CREATOR = new Creator<LoginBean>() {
        @Override
        public LoginBean createFromParcel(Parcel in) {
            return new LoginBean(in);
        }

        @Override
        public LoginBean[] newArray(int size) {
            return new LoginBean[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(token);
        dest.writeString(expiresIn);
    }
}
