package realexp.realexp;

import android.content.Context;
import android.hardware.Camera;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import java.util.List;

public class Heartrate extends AppCompatActivity implements SensorEventListener {

    SensorManager sensorM;
    Sensor sensorHeartrate;

    // Sensor
    @Override
    public void onSensorChanged(SensorEvent event) {
        Log.i("Beats per min", Float.toString(event.values[0]));
    }
    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
        if (accuracy == SensorManager.SENSOR_STATUS_NO_CONTACT ||
                accuracy == SensorManager.SENSOR_STATUS_UNRELIABLE) {


        }
    }

    // Activity
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_heartrate);

        sensorM = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        sensorHeartrate = sensorM.getDefaultSensor(Sensor.TYPE_HEART_RATE);
        sensorM.registerListener(this, sensorHeartrate, SensorManager.SENSOR_DELAY_FASTEST);

        List<Sensor> l = sensorM.getSensorList(Sensor.TYPE_HEART_RATE);
        if (l.size() == 0) {
            Log.i("Heart Rate", "No Heart Rate Sensor Detected");
        }
        for (int i = 0; i < l.size(); ++i) {
            Log.i("Sensor List " + Integer.toString(i), l.get(i).toString());
        }

    }

    @Override
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
