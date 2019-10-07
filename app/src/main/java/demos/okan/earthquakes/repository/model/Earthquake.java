package demos.okan.earthquakes.repository.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Earthquake implements Serializable {

    @SerializedName("datetime")
    @Expose() private String dateTime;

    @SerializedName("depth")
    @Expose() private double depth;

    @SerializedName("eqid")
    @Expose() private String id;

    @SerializedName("lat")
    @Expose() private double latitude;

    @SerializedName("lng")
    @Expose() private double longitude;

    @SerializedName("magnitude")
    @Expose() private double magnitude;

    @SerializedName("src")
    @Expose() private String source;

    public Earthquake() {}

    public Earthquake(String dateTime, double depth, String id, double latitude, double longitude, double magnitude, String source) {
        this.dateTime = dateTime;
        this.depth = depth;
        this.id = id;
        this.latitude = latitude;
        this.longitude = longitude;
        this.magnitude = magnitude;
        this.source = source;
    }

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    public double getDepth() {
        return depth;
    }

    public void setDepth(double depth) {
        this.depth = depth;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getMagnitude() {
        return magnitude;
    }

    public void setMagnitude(double magnitude) {
        this.magnitude = magnitude;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }
}
