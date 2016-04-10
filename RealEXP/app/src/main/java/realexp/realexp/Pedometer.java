package realexp.realexp;

import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import java.text.DecimalFormat;


public class Pedometer extends AppCompatActivity implements SensorEventListener {

    Intent i;
    realexp.realexp.User user;
    float iSteps;
    float steps;
    int weight = 180; // in pounds
    int height = 177; // in centimeters
    float strideLength; // in feet
    float speed; // steps per second
    float timeStep1;
    float timeStep2;

    TextView txtSteps;
    TextView txtDistance;
    TextView txtCalories;
    SensorManager sensorM;
    Sensor sensorSteps;

    // Sensor Event Listener
    @Override
    public void onSensorChanged(SensorEvent event) {
        // Get initial step count
        if (iSteps == -1) {
            iSteps = event.values[0];
            Log.i("Initial Steps", Float.toString(iSteps));
        }

        // Get steps and timestamp
        Log.i("Timestamp", Float.toString(event.timestamp));
        steps = event.values[0] - iSteps;
        if (timeStep1 == 0) timeStep1 = event.timestamp;
        else if (timeStep2 == 0) {
            timeStep2 = event.timestamp;
            float t = timeStep2 - timeStep1;
            Log.i("time ns", Float.toString(t));
            t /= 1000000000; // nanoseconds to seconds
            Log.i("time s", Float.toString(t));
            if (t <= 2 && t >= .1) { // steps are less than 2 seconds apart
                t /= 60; // convert to hours
                if (speed == 0) speed = strideLength / t;
                else {
                    speed += strideLength / t;
                    speed = speed / 2;
                }
                Log.i("Speed", Float.toString(speed));
            }
            timeStep1 = 0;
            timeStep2 = 0;
            // float minmile = 5280 / speed;
        }

        float distance = steps * strideLength / 5280; // in miles
        float hours;
        if (speed == 0) {
            hours = distance / 3.5f;
        }
        else {
            hours = distance / ((speed / 5280) * 60);
        }

        // Update Displays
        txtSteps.setText(Integer.toString((int) steps));
        txtDistance.setText(new DecimalFormat("##.##").format(distance)
            + " Miles");
        /*
        17 min/mile 1.92 Cal/hr * lb
        15 min/mile 2.16 Cal/hr * lb
        13 min/mile 2.40 Cal/hr * lb
        */
        // TODO: Variable speed implementation
        txtCalories.setText(Float.toString((int) (2.16f * hours * weight))
                + " Calories burned");

        TextView txtSpeed = (TextView) findViewById(R.id.txtSpeed);
        txtSpeed.setText(Float.toString(speed) + " feet/min");


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
        timeStep1 = 0;
        timeStep2 = 0;
        speed = user.get_speed();
        strideLength = height * .415f;
        strideLength *= .0328084; // centimeters to feet

        txtSteps = (TextView) findViewById(R.id.txtSteps);
        txtDistance = (TextView) findViewById(R.id.txtDistance);
        txtCalories = (TextView) findViewById(R.id.txtCal);


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
    public void finish() {
        user.set_steps(steps);
        user.set_iSteps(iSteps);
        user.set_speed(speed);

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
        outState.putFloat("timeStep1", timeStep1);
        outState.putFloat("timeStep2", timeStep2);
        outState.putFloat("speed", speed);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        steps = savedInstanceState.getFloat("steps");
        iSteps = savedInstanceState.getFloat("iSteps");
        timeStep1 = savedInstanceState.getFloat("timeStep1");
        timeStep2 = savedInstanceState.getFloat("timeStep2");
        speed = savedInstanceState.getFloat("speed");

    }



}
