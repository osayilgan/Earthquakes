package demos.okan.earthquakes.di;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;
import demos.okan.earthquakes.view.earthquakes.EarthquakeActivity;

@Module
public abstract class ActivityBuildersModule {

    @ContributesAndroidInjector( modules = { EarthquakeViewModelModule.class } )
    abstract EarthquakeActivity contributeMainActivity();
}
