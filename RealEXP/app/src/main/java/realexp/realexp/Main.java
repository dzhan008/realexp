package realexp.realexp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class Main extends FragmentActivity implements OnMapReadyCallback {

    protected GoogleMap map;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        MapFragment mapFragment = (MapFragment) getFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }
    @Override
    public void onMapReady(GoogleMap map) {
        map.setMapType(map.MAP_TYPE_HYBRID);

        LatLng UCR = new LatLng(33.975, -117.329);
        map.addMarker(new MarkerOptions().position(UCR).title("UCR"));
        moveToCurrentLocation(UCR);
    }
    private void moveToCurrentLocation(LatLng currentLocation)
    {
        map.moveCamera(CameraUpdateFactory.newLatLngZoom(currentLocation, 5.0f));
        //map.animateCamera(CameraUpdateFactory.zoomIn());
        //map.animateCamera(CameraUpdateFactory.zoomTo(15), 2000, null);
        map.setOnCameraChangeListener(null);
    }

    public void btnLocationA_onClick(View v) {
        Intent intent = new Intent(this, Pedometer.class);
        startActivity(intent);
    }

    public void btnHeartrate_Click (View v) {
        Intent intent = new Intent(this, Heartrate.class);
        startActivity(intent);
    }
}
