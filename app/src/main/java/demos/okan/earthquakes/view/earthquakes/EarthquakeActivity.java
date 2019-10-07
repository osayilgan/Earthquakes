package demos.okan.earthquakes.view.earthquakes;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import javax.inject.Inject;

import dagger.android.support.DaggerAppCompatActivity;
import demos.okan.earthquakes.R;
import demos.okan.earthquakes.repository.model.Earthquake;
import demos.okan.earthquakes.repository.model.NetworkState;
import demos.okan.earthquakes.view.location.MapsActivity;
import demos.okan.earthquakes.viewmodel.EarthquakeViewModel;

public class EarthquakeActivity extends DaggerAppCompatActivity implements SwipeRefreshLayout.OnRefreshListener, EarthquakeAdapter.ClickListener {

    private static final String TAG = "EarthquakeActivity";

    @Inject
    EarthquakeAdapter adapter;

    @Inject
    EarthquakeViewModel viewModel;

    /* UI */
    private SwipeRefreshLayout swipeToRefresh;
    private TextView errorTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /* init UI */
        initUi();

        /* Subscribe Observers */
        subscribeObservers();
    }

    @Override
    public void onRefresh() {

        /* Load Data again from ViewModel */
        viewModel.retrieveEarthquakes();

        /* Remove Error Text View since we are refreshing */
        updateErrorView(null);
    }

    @Inject
    public void enableClickEvents(EarthquakeAdapter earthquakeAdapter) {
        earthquakeAdapter.setClickListener(this);
    }

    @Override
    public void onClickItem(Earthquake earthquake) {

        Intent mapsActivity = new Intent(this, MapsActivity.class);
        mapsActivity.putExtra(MapsActivity.MAP_ACTIVITY_BUNDLE, earthquake);
        startActivity(mapsActivity);
    }

    /**
     * initializes UI.
     */
    private void initUi() {

        /* Recycler View */
        RecyclerView earthquakeListView = findViewById(R.id.earthquake_list_view);
        earthquakeListView.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL, false));

        /* Set Adapter */
        earthquakeListView.setAdapter(adapter);

        /* Swipe To Refresh Layout */
        swipeToRefresh = findViewById(R.id.swipeToRefresh);
        swipeToRefresh.setOnRefreshListener(this);

        /* Error Text View */
        errorTextView = findViewById(R.id.errorTextView);
    }

    /**
     * Observe Results.
     */
    private void subscribeObservers() {

        /* Observe Earthquakes */
        viewModel.getEarthquakes().observe(this, earthquakes -> {

            /* Update the Adapter with the Latest Data */
            adapter.setData(earthquakes);
        });

        /* Observe NetworkState and update the Spinner Visibility accordingly */
        viewModel.getNetworkState().observe(this, networkState -> {

            Log.i(TAG, "Network State updated : " + networkState.getStatus().toString());

            /* Show/Hide Spinner */
            showSpinner(networkState == NetworkState.LOADING);

            /* Update Error Text View */
            if (networkState.getStatus() == NetworkState.FAILED.getStatus()) updateErrorView(networkState.getMsg());
        });
    }

    /**
     * Show/Hide Progress Bar.
     *
     * @param isVisible
     */
    private void showSpinner(boolean isVisible) {
        swipeToRefresh.setRefreshing(isVisible);
    }

    /**
     * Updates the Message in Error Text View.
     *
     * @param errorMessage
     */
    private void updateErrorView(String errorMessage) {

        if (errorMessage == null) {
            errorTextView.setVisibility(View.GONE);
        } else {
            errorTextView.setVisibility(View.VISIBLE);
            errorTextView.setText(errorMessage);
        }
    }
}
