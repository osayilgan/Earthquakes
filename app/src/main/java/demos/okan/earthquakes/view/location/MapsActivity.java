package demos.okan.earthquakes.view.location;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import demos.okan.earthquakes.R;
import demos.okan.earthquakes.repository.model.Earthquake;

public class MapsActivity extends AppCompatActivity implements OnMapReadyCallback {

    private GoogleMap mMap;

    public static final String MAP_ACTIVITY_BUNDLE = "MAP_ACTIVITY_BUNDLE";
    private Earthquake earthquake;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

        /* Initialise Bundle Data */
        retrieveData();

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    /**
     * Retrieves the earthquake details data from IntentExtras.
     */
    private void retrieveData() {

        Bundle bundle = getIntent().getExtras();
        if (bundle == null) return;

        Earthquake earthquake = (Earthquake) bundle.getSerializable(MAP_ACTIVITY_BUNDLE);
        this.earthquake = earthquake;
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        /* Add a marker if the data is Not Null */
        if (earthquake == null) return;

        LatLng sydney = new LatLng(earthquake.getLatitude(), earthquake.getLongitude());
        mMap.addMarker(new MarkerOptions().position(sydney).title("Depth: " + earthquake.getDepth() + " Magnitude: " + earthquake.getMagnitude()));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
    }
}
