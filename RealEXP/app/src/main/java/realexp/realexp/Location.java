package realexp.realexp;

import android.content.Context;
import android.content.pm.PackageManager;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.location.*;
import android.view.View;
import android.widget.TextView;

import java.util.jar.Manifest;

public class Location extends AppCompatActivity {

    android.location.LocationManager locationM;
    float steps;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location);

        locationM = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        steps = 0;

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_location, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void btnLocation_Click(View v) {
        try {
            android.location.Location l = locationM.getLastKnownLocation(locationM.GPS_PROVIDER);
            double lat = l.getLatitude();
            double lon = l.getLongitude();
            TextView latitude = (TextView) findViewById(R.id.txtLatiude);
            TextView longitude = (TextView) findViewById(R.id.txtLongitude);
            latitude.setText(Double.toString(lat));
            longitude.setText(Double.toString(lon));


        } catch (SecurityException e) {
            Log.e("PERMISSION", "ACCESS_FINE_LOCATION");
        }
    }

    public void btnSteps_Click(View v) {
        final TextView txtsteps = (TextView) findViewById(R.id.txtSteps);

        SensorManager sensorM = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        Sensor sensorSteps = sensorM.getDefaultSensor(Sensor.TYPE_STEP_COUNTER);
        sensorM.registerListener(new SensorEventListener() {
            @Override
            public void onSensorChanged(SensorEvent event) {
                if (event.values.length > 0) {
                    steps = event.values[0];
                }
                txtsteps.setText(Float.toString(steps));
            }

            @Override
            public void onAccuracyChanged(Sensor sensor, int accuracy) {

            }

        }, sensorSteps, SensorManager.SENSOR_DELAY_FASTEST);

    }


}
