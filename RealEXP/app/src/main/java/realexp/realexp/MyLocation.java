package realexp.realexp;

import android.content.pm.PackageManager;
import android.support.v4.content.ContextCompat;
import java.util.jar.Manifest;

public class MyLocation {
    //Need to add in-app system permissions in order for this class to work
    //Note: Should be included in Location.java but for coding purposes I'm keeping this stuff to my branch

    //Checks that user gave permission for location to be used
    if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
        mMap.setMyLocationEnabled(true);
    } else {
        //Show rationale and request permission
    }
    //Handles the result of the permission request
    public void onRequestPermissionResult(int requestCode, String[] permissions, int[] grantResults) {
        if (requestCode == MY_LOCATION_REQUEST_CODE) {
            if(permissions.lenfth == 1 && permissions[0] == Manifest.permission.ACCESS_FINE_LOCATION && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                mMap.setMyLocationEnabled(true);
            } else {
                System.out.print("Cannot continue without user location. Please grant permission. \n")
            }
        }
    }
}
