package demos.okan.earthquakes.repository.api;

import demos.okan.earthquakes.repository.model.Earthquakes;
import io.reactivex.Observable;
import retrofit2.http.GET;

public interface EarthquakeApiService {

    String BASE_API_URL = "http://api.geonames.org/";

    @GET("earthquakesJSON?formatted=true&north=44.1&south=-9.9&east=-22.4&west=55.2&username=mkoppelman")
    Observable<Earthquakes> getEarthquakes();
}
