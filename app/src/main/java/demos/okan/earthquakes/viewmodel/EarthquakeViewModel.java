package demos.okan.earthquakes.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import demos.okan.earthquakes.repository.api.EarthquakeApiRepository;
import demos.okan.earthquakes.repository.model.Earthquake;
import demos.okan.earthquakes.repository.model.NetworkState;

@Singleton
public class EarthquakeViewModel extends ViewModel {

    private static final String TAG = "EarthquakeViewModel";

    @Inject
    EarthquakeApiRepository repository;

    @Inject
    public EarthquakeViewModel() {}

    public void retrieveEarthquakes() {
        repository.retrieveEarthquakes();
    }

    public LiveData<List<Earthquake>> getEarthquakes() {
        return repository.getEarthquakes();
    }

    public LiveData<NetworkState> getNetworkState() {
        return repository.getNetworkState();
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        repository.clear();
    }
}
