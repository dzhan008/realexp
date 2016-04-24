package realexp.realexp;

import android.content.Context;
import android.content.pm.PackageManager;
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
import android.widget.TextView;

import java.io.IOException;
import java.util.List;


public class Heartrate extends AppCompatActivity implements SensorEventListener {

    SensorManager sensorM;
    Sensor sensorHeartrate;
    Camera camera;
    SurfaceView sView;

    public enum COLOR {
        GREEN, RED
    }
    COLOR color = COLOR.RED;

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
        sView = (SurfaceView) findViewById(R.id.surfaceView);

        getCamera();

        /*
        TextView t = (TextView) findViewById(R.id.txtSensor);
        if (sensorHeartrate != null) { // a sensor exists
            sensorM.registerListener(this, sensorHeartrate, SensorManager.SENSOR_DELAY_FASTEST);
            Log.i("Heart Sensor", sensorHeartrate.getName());
            t.setText("Sensor found"); // TODO Erase This
        }
        else {  // start the camera sensor
            t.setText("No Sensor Found"); // TODO Erase This
            if (this.getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA)){
                // this device has a camera





            } else {
                // no camera on this device
                //TODO
            }
        }
        */

    }

    @Override
    protected void onResume() {
        super.onResume();
        camera = Camera.open();
    }

    @Override
    protected void onPause() {
        super.onPause();
        camera.release();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        camera.stopPreview();
        camera.release();
    }

    @Override
    protected void onStop() {
        super.onStop();
        camera.release();
    }

    public void getCamera() {
        camera = camera.open();
        SurfaceHolder sHolder = sView.getHolder();
        sHolder.addCallback(sHolderCallback);
        sHolder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);

    }

    private Camera.PreviewCallback previewCallback = new Camera.PreviewCallback() {

        @Override
        public void onPreviewFrame(byte[] data, Camera camera) {
            if (data == null) return;
            Camera.Size size = camera.getParameters().getPreviewSize();
            if (size == null) return;


        }
    };

    private SurfaceHolder.Callback sHolderCallback = new SurfaceHolder.Callback() {
        @Override
        public void surfaceCreated(SurfaceHolder holder) {
            try {
                camera.setDisplayOrientation(90);
                camera.setPreviewDisplay(holder);
                camera.setPreviewCallback(previewCallback);
                camera.startPreview();
            } catch (IOException e) {
                e.printStackTrace();
                Log.e("SurfaceHolderCallback", "Error in setPreview");
            }
        }
        @Override
        public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
            Camera.Parameters parameters = camera.getParameters();
            List<Camera.Size> previewSizes = parameters.getSupportedPreviewSizes();
            Camera.Size newSize = parameters.getPreviewSize();
            for (Camera.Size size : previewSizes) {
                if (size.width <= width && size.height < height) {
                    newSize = size;
                }
            }
            //parameters.setFlashMode(Camera.Parameters.FLASH_MODE_TORCH);
            //parameters.setPreviewFormat(format);
            parameters.setPreviewSize(newSize.width, newSize.height);

            try {
                camera.setParameters(parameters);
            } catch (Exception e) {
                e.printStackTrace();
                Log.e("Camera Paramters", "Bad parameters");
            }
            camera.startPreview();
        }
        @Override
        public void surfaceDestroyed(SurfaceHolder holder) {
            camera.stopPreview();
        }
    };

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
