package demos.okan.earthquakes.di;

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import demos.okan.earthquakes.repository.api.EarthquakeApiService;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class AppModule  {

    private static final String TAG = "AppModule";

    @Singleton
    @Provides
    static Retrofit provideRetrofitInstance() {
        return new Retrofit.Builder()
                .baseUrl(EarthquakeApiService.BASE_API_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
    }

    @Provides
    @Singleton
    EarthquakeApiService provideDogApiService(Retrofit retrofit) {
        return retrofit.create(EarthquakeApiService.class);
    }
}
