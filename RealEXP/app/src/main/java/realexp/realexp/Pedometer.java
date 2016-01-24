package realexp.realexp;

import android.content.Context;
import android.content.res.Configuration;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;


public class Pedometer extends AppCompatActivity implements SensorEventListener {

    float steps;
    TextView txtSteps;
    SensorManager sensorM;
    Sensor sensorSteps;

    // Sensor Event Listener
    @Override
    public void onSensorChanged(SensorEvent event) {
        steps = event.values[0];
        txtSteps.setText(Float.toString(steps));
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }


    // Activity
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location);

        steps = 0;
        txtSteps = (TextView) findViewById(R.id.txtSteps);
        sensorM = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        sensorSteps = sensorM.getDefaultSensor(Sensor.TYPE_STEP_COUNTER);
        sensorM.registerListener(this, sensorSteps, SensorManager.SENSOR_DELAY_FASTEST);
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

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);

        txtSteps.setText(Float.toString(steps));
    }


    /*public void btnLocation_Click(View v) {
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
    */




}
