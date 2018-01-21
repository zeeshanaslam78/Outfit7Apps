package com.xeeshi.outfit7apps.data;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by ZEESHAN on 05/01/2018.
 */

public class AppList implements Parcelable {

    private String name;
    private Drawable icon;
    private String versionCode;
    private String versionName;
    private String packageName;
    private List<String> requestedPermissions;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Drawable getIcon() {
        return icon;
    }

    public void setIcon(Drawable icon) {
        this.icon = icon;
    }

    public String getVersionCode() {
        return versionCode;
    }

    public void setVersionCode(String versionCode) {
        this.versionCode = versionCode;
    }

    public String getVersionName() {
        return versionName;
    }

    public void setVersionName(String versionName) {
        this.versionName = versionName;
    }

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public List<String> getRequestedPermissions() {
        return requestedPermissions;
    }

    public void setRequestedPermissions(String[] requestedPermissions) {
        this.requestedPermissions = Arrays.asList(requestedPermissions);
    }

    public void setRequestedPermissions(List<String> requestedPermissions) {
        this.requestedPermissions = requestedPermissions;
    }

    public AppList() {
    }

    protected AppList(Parcel in) {
        name = in.readString();

        // Deserialize Parcelable and cast to Bitmap first:
        Bitmap bitmap = (Bitmap)in.readParcelable(getClass().getClassLoader());
        // Convert Bitmap to Drawable:
        icon = new BitmapDrawable(bitmap);

        versionCode = in.readString();
        versionName = in.readString();
        packageName = in.readString();
        if (in.readByte() == 0x01) {
            requestedPermissions = new ArrayList<String>();
            in.readList(requestedPermissions, String.class.getClassLoader());
        } else {
            requestedPermissions = null;
        }
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);

        // Convert Drawable to Bitmap first:
        Bitmap bitmap = (Bitmap)((BitmapDrawable) icon).getBitmap();
        // Serialize bitmap as Parcelable:
        dest.writeParcelable(bitmap, flags);

        dest.writeString(versionCode);
        dest.writeString(versionName);
        dest.writeString(packageName);
        if (requestedPermissions == null) {
            dest.writeByte((byte) (0x00));
        } else {
            dest.writeByte((byte) (0x01));
            dest.writeList(requestedPermissions);
        }
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<AppList> CREATOR = new Parcelable.Creator<AppList>() {
        @Override
        public AppList createFromParcel(Parcel in) {
            return new AppList(in);
        }

        @Override
        public AppList[] newArray(int size) {
            return new AppList[size];
        }
    };


    @Override
    public String toString() {
        return "AppList{" +
                "name='" + name + '\'' +
                ", \nicon=" + icon +
                ", \nversionCode='" + versionCode + '\'' +
                ", \nversionName='" + versionName + '\'' +
                ", \npackageName='" + packageName + '\'' +
                ", \nrequestedPermissions=" + requestedPermissions.toString() +
                '}';
    }
}
