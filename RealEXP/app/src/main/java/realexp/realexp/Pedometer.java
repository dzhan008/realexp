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
import java.util.ArrayList;
import java.util.List;


public class Pedometer extends AppCompatActivity implements SensorEventListener {

    Intent i;
    User user;
    float iSteps;
    float steps;
    int expSteps; // Counts up to 100 steps then gives exp
    int weight = 180; // in pounds
    int height = 177; // in centimeters
    float strideLength; // in feet
    int calories;
    float timeStep1;
    float timeStep2;

    float avgSpeed; // Holds the average speed of SpeedArray
    long numSpeed; // Holds the number of times the average speed has been computed

    TextView txtSteps;
    TextView txtDistance;
    TextView txtCalories;
    TextView txtSpeed;
    SensorManager sensorM;
    Sensor sensorSteps;


    // Sensor Event Listener
    @Override
    public void onSensorChanged(SensorEvent event) {
        // Local variables
        float speed = 0;

        // Get initial step count
        if (iSteps == -1) {
            iSteps = event.values[0];
            Log.i("Initial Steps", Float.toString(iSteps));
        }

        // Get steps and timestamp
        steps = event.values[0] - iSteps;

        // Calculate exp
        if (expSteps == 0) {
            expSteps = (int)steps;
        }
        if (steps - expSteps >= 100) {
            user.gain_exp(10);
            expSteps += 100;
            Log.i("EXP Manager", "Gained 10 EXP!");
        }

        // Calculate speed based on time between steps
        if (timeStep1 == 0) timeStep1 = event.timestamp;
        else if (timeStep2 == 0) {
            timeStep2 = event.timestamp;
            float t = timeStep2 - timeStep1;
            t /= 1000000000; // nanoseconds to seconds
            if (t <= 2 && t >= .1) { // steps are less than 2 seconds apart
                speed = (strideLength / t); // feet / second
                Log.i("Local speed", Float.toString(speed));
                numSpeed++;
                Log.i("numSpeed", Long.toString(numSpeed));
                avgSpeed += ((speed - avgSpeed) / (numSpeed)) ; // feet / sec
                Log.i("Average Speed", Float.toString(avgSpeed));
            }
            timeStep1 = 0;
            timeStep2 = 0;
        }

        // Caluculate distance
        float distance = steps * strideLength; // in feet

        float hours = distance / (avgSpeed * 3600);


        // Variable speed Calorie burn rate
        /*
            17 min/mile 1.92 Cal/hr * lb
            15 min/mile 2.16 Cal/hr * lb
            13 min/mile 2.40 Cal/hr * lb
            */
        // Y = Slope * x + b
        // slope = -.12
        // 2.4 = -.12 * 13 + b
        // b = 3.96
        float CalorieConstant = (float) (((5280 / (avgSpeed * 60)) * -.12) + 3.96);
        calories = (int) (CalorieConstant * hours * weight);



        // Update Displays
        txtCalories.setText(Integer.toString(calories)
                + " Calories burned");
        txtSteps.setText(Integer.toString((int) steps));
        txtDistance.setText(new DecimalFormat("##.##").format(distance / 5280)
            + " Miles");
        txtSpeed.setText(Float.toString(avgSpeed) + " feet/sec");


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

        timeStep1 = 0;
        timeStep2 = 0;
        expSteps = 0;

        steps = user.get_steps();
        iSteps = user.get_iSteps();
        avgSpeed = user.get_avgSpeed();
        numSpeed = user.get_numSpeed();
        calories = user.get_calories();

        strideLength = height * .415f;
        strideLength *= .0328084; // centimeters to feet

        txtSteps = (TextView) findViewById(R.id.txtSteps);
        txtDistance = (TextView) findViewById(R.id.txtDistance);
        txtCalories = (TextView) findViewById(R.id.txtCal);
        txtSpeed  = (TextView) findViewById(R.id.txtSpeed);

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
        user.set_avgSpeed(avgSpeed);
        user.set_numSpeed(numSpeed);
        user.set_calories(calories);

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
        outState.putFloat("avgSpeed", avgSpeed);
        outState.putLong("numSpeed", numSpeed);
        outState.putInt("calories", calories);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        steps = savedInstanceState.getFloat("steps");
        iSteps = savedInstanceState.getFloat("iSteps");
        timeStep1 = savedInstanceState.getFloat("timeStep1");
        timeStep2 = savedInstanceState.getFloat("timeStep2");
        avgSpeed = savedInstanceState.getFloat("avgSpeed");
        numSpeed = savedInstanceState.getLong("numSpeed");
        calories = savedInstanceState.getInt("calories");
    }


}
