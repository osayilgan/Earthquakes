package demos.okan.earthquakes.base;

import dagger.android.AndroidInjector;
import dagger.android.support.DaggerApplication;
import demos.okan.earthquakes.di.AppComponent;
import demos.okan.earthquakes.di.DaggerAppComponent;

public class BaseApplication extends DaggerApplication {

    private AppComponent appComponent;

    @Override
    protected AndroidInjector<? extends DaggerApplication> applicationInjector() {
        appComponent = DaggerAppComponent.builder().application(this).build();
        return appComponent;
    }

    public AppComponent getAppComponent() {
        return appComponent;
    }
}
