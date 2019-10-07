package demos.okan.earthquakes.di;

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;
import demos.okan.earthquakes.viewmodel.EarthquakeViewModel;
import demos.okan.earthquakes.viewmodel.ViewModelProviderFactory;

@Module
public abstract class EarthquakeViewModelModule {

    @Binds
    public abstract ViewModelProvider.Factory bindViewModelFactory(ViewModelProviderFactory viewModelProviderFactory);

    @Binds
    @IntoMap
    @ViewModelKey(EarthquakeViewModel.class)
    public abstract ViewModel bindDogViewModel(EarthquakeViewModel earthquakeViewModel);
}
