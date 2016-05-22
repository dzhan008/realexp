package realexp.realexp;

import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.ImageFormat;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.hardware.Camera;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.TextView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class Heartrate extends AppCompatActivity implements SensorEventListener {

    TextView txtNoSensor;
    TextView txtHeartRate;

    SensorManager sensorM;
    Sensor sensorHeartrate;




    float time1;
    float time2;
    List<Float> BPMS;
    @Override
    public void onSensorChanged(SensorEvent event) {
        if (event.accuracy == SensorManager.SENSOR_STATUS_ACCURACY_HIGH ||
                event.accuracy == SensorManager.SENSOR_STATUS_ACCURACY_MEDIUM) {
            if (time1 == 0) time1 = event.timestamp;
            time2 = event.timestamp;

            Log.i("Beats per min", Float.toString(event.values[0]));
            txtHeartRate.setText(Float.toString(event.values[0]));
            BPMS.add(event.values[0]);

        /*
        Log.i("Time", Float.toString(time2 - time1));
        if ((time2 - time1) >= 15) {
            Log.i("Listner", "Unregister");
            sensorM.unregisterListener(this, sensorHeartrate);
        }
        */
        }

    }
    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
        if (accuracy == SensorManager.SENSOR_STATUS_NO_CONTACT ||
                accuracy == SensorManager.SENSOR_STATUS_UNRELIABLE) {
                // TODO tell user to place finger on sensor
            txtHeartRate.setText("Please keep your finger still on the sensor");
            time1 = 0;
            BPMS.clear();
        }
    }

    public void btnMeasureHeartRate_click(View v) {
        time1 = 0;
        time2 = 0;
        BPMS.clear();
        List<Sensor> sensors = sensorM.getSensorList(Sensor.TYPE_HEART_RATE);

        if (sensors == null) {
            //TODO
            txtNoSensor.setVisibility(View.VISIBLE);
        }
        else {
            txtNoSensor.setVisibility(View.INVISIBLE);
        }

        for (Sensor s : sensors) {
            //TODO Decide which one to use
            Log.i("Heart Rate Sensor", s.getName());
            sensorHeartrate = s;
        }

        if (!sensorM.registerListener(this, sensorHeartrate, SensorManager.SENSOR_DELAY_FASTEST)) {
            Log.e("Heart Rate Sensor", "Failed to register listener");
            txtHeartRate.setText("Could not connect to device.");
            return;
        }

        txtHeartRate.setText("Waiting to measure heart rate.");
    }

    // Activity
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_heartrate);

        txtNoSensor = (TextView)findViewById(R.id.txtError);
        txtHeartRate = (TextView)findViewById(R.id.txtHeartRate);

        sensorM = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        BPMS = new ArrayList<>();






    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_heartrate, menu);
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

}
