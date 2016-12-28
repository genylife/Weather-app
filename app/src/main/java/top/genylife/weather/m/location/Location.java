package top.genylife.weather.m.location;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by wanqi on 2016/12/27.
 *
 * @since 1.0.0
 */
public class Location implements Parcelable {


    private double lng;//经度
    private double lat;//纬度

    public Location() {

    }

    public Location(double lng, double lat) {
        this.lng = lng;
        this.lat = lat;
    }

    public String urlFormat() {
        return lat + "," + lng;
    }

    public double getLng() {
        return lng;
    }

    public void setLng(double lng) {
        this.lng = lng;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeDouble(this.lng);
        dest.writeDouble(this.lat);
    }

    protected Location(Parcel in) {
        this.lng = in.readDouble();
        this.lat = in.readDouble();
    }

    public static final Creator<Location> CREATOR = new Creator<Location>() {
        @Override
        public Location createFromParcel(Parcel source) {
            return new Location(source);
        }

        @Override
        public Location[] newArray(int size) {
            return new Location[size];
        }
    };
}
