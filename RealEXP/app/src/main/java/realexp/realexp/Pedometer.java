package realexp.realexp;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;


public class Pedometer extends AppCompatActivity implements SensorEventListener {

    Intent i;
    User user;
    float iSteps;
    float steps;
    int weight = 180; //pounds
    int height = 66; //inches
    float speed = 1; // steps per second
    TextView txtSteps;
    SensorManager sensorM;
    Sensor sensorSteps;

    // Sensor Event Listener
    @Override
    public void onSensorChanged(SensorEvent event) {
        if (iSteps == -1) {
            iSteps = event.values[0];
            Log.i("Initial Steps", Float.toString(iSteps));
        }
        steps = event.values[0] - iSteps;
        Log.i("Timestamp Step", Long.toString(event.timestamp));
        txtSteps.setText(Integer.toString((int) steps));
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }


    // Activity
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pedometer);

        i = getIntent();
        user = i.getParcelableExtra("user");

        steps = user.get_steps();
        iSteps = user.get_iSteps();

        txtSteps = (TextView) findViewById(R.id.txtSteps);
        sensorM = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        sensorSteps = sensorM.getDefaultSensor(Sensor.TYPE_STEP_COUNTER);
        sensorM.registerListener(this, sensorSteps, SensorManager.SENSOR_DELAY_FASTEST);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_pedometer, menu);
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

    }

    @Override
    public void finish() {
        user.set_steps(steps);
        user.set_iSteps(iSteps);

        Intent resultIntent = new Intent();
        resultIntent.putExtra("updated_user", user);
        setResult(Main.RESULT_OK, resultIntent);
        super.finish();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putFloat("steps", steps);
        outState.putFloat("iSteps", iSteps);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        steps = savedInstanceState.getFloat("steps");
        iSteps = savedInstanceState.getFloat("iSteps");
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
