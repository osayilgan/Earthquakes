package demos.okan.earthquakes.repository.api;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import demos.okan.earthquakes.repository.model.Earthquake;
import demos.okan.earthquakes.repository.model.Earthquakes;
import demos.okan.earthquakes.repository.model.NetworkState;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

@Singleton
public class EarthquakeApiRepository {

    private static final String TAG = EarthquakeApiRepository.class.getName();

    /* Service Interface */
    private EarthquakeApiService earthquakeApiService;

    /* Live Data for Earthquakes */
    private MutableLiveData<List<Earthquake>> earthquakesLiveData;

    /* Live Data for Network State */
    private MutableLiveData<NetworkState> networkStateLiveData;

    /* Disposable for Sequential Network Calls */
    private final CompositeDisposable disposables = new CompositeDisposable();

    @Inject
    public EarthquakeApiRepository(EarthquakeApiService earthquakeApiService) {

        this.earthquakeApiService = earthquakeApiService;

        earthquakesLiveData = new MutableLiveData<>();
        networkStateLiveData = new MutableLiveData<>();

        /* Start Loading Data when Repository Created */
        retrieveEarthquakes();
    }

    /**
     * Called when we need to clean up the disposables.
     */
    public void clear() {
        disposables.clear();
    }

    public LiveData<List<Earthquake>> getEarthquakes() {
        return earthquakesLiveData;
    }

    public LiveData<NetworkState> getNetworkState() {
        return networkStateLiveData;
    }

    /**
     * Loads Earthquakes from API.
     */
    public void retrieveEarthquakes() {

        /* Post NetworkState as Loading */
        networkStateLiveData.postValue(NetworkState.LOADING);

        /* Retrieves the Earthquakes from API */
        earthquakeApiService.getEarthquakes().subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<Earthquakes>() {

            @Override
            public void onSubscribe(Disposable d) {

                /* Add to Disposables so we can clear later on */
                disposables.add(d);
            }

            @Override
            public void onNext(Earthquakes earthquakes) {

                /* Post the Results to LiveData */
                earthquakesLiveData.setValue(earthquakes.getEarthquakes());
            }

            @Override
            public void onError(Throwable e) {

                Log.e(TAG, "Error retrieving earthquakes : " + e.getMessage());

                /* Update NetworkState as FAILED */
                networkStateLiveData.postValue(new NetworkState(NetworkState.Status.FAILED, e.getMessage()));
            }

            @Override
            public void onComplete() {

                Log.d(TAG, "Earthquakes Retrieved");
                networkStateLiveData.postValue(NetworkState.LOADED);

                /* Once We are done with Loading Data, Clean Disposables */
                disposables.clear();
            }
        });
    }
}
