package demos.okan.earthquakes.repository.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Earthquakes {

    @SerializedName("earthquakes")
    @Expose() private List<Earthquake> earthquakes;

    public Earthquakes() {}

    public Earthquakes(List<Earthquake> earthquakes) {
        this.earthquakes = earthquakes;
    }

    public List<Earthquake> getEarthquakes() {
        return earthquakes;
    }

    public void setEarthquakes(List<Earthquake> earthquakes) {
        this.earthquakes = earthquakes;
    }
}
