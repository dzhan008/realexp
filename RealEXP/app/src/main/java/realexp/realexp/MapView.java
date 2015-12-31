package realexp.realexp;

import android.content.Context;
import android.os.Bundle;
import android.util.AttributeSet;
import com.google.android.gms.maps.GoogleMapOptions;
import com.google.android.gms.maps.OnMapReadyCallback;


public class MapView {
    //Constructors
    public MapView(Context context){}
    public MapView (Context context, AttributeSet attrs) {}
    public MapView (Context context, AttributeSet attrs, int defStyle) {}
    public MapView (Context context, GoogleMapOptions options) {}

    public void getMapAsync (OnMapReadyCallback callback) {
        //Returns a non-null instance of GoogleMap
    }
    public final void OnCreate(Bundle savedInstanceState) {

    }
    public final void onDestroy() {

    }
    public final void onPuase() {

    }
    public final void onResume() {

    }
    public final void onSaveInstanceState (Bundle outState) {
        //Provides a Bundle to store the state of the Fragment before it gets destroyed.
    }
}
