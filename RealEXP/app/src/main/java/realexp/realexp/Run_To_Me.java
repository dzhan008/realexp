package realexp.realexp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.drive.Drive;
import com.google.android.gms.location.LocationServices;

public class Run_To_Me extends AppCompatActivity {

    Button buttonInit;
    Button buttonGoal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_run_to_me);

        GoogleApiClient mGoogleApiClient = new GoogleApiClient.Builder(this)
                .enableAutoManage(this /* FragmentActivity */,
                        this /* OnConnectionFailedListener */)
                .addApi(Drive.API)
                .addScope(Drive.SCOPE_FILE)
                .build();
        if (mGoogleApiClient == null) {
            mGoogleApiClient = new GoogleApiClient.Builder(this)
                    .addConnectionCallbacks(this)
                    .addOnConnectionFailedListener(this)
                    .addApi(LocationServices.API)
                    .build();
        }
        //TODO: Allow user to switch views to see how far they have to go
        buttonInit = (Button) findViewById(R.id.btn_SetInit);
        buttonGoal = (Button) findViewById(R.id.btn_gp);

        buttonInit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick (View view) {
                //TODO: Add marker at user's location
                Toast.makeText(Run_To_Me.this, "Current Location Set as Starting Point", Toast.LENGTH_SHORT).show();
            }
        });
        buttonGoal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick (View view) {
                Intent i = new Intent(getApplicationContext(), Travel_Map.class);
                startActivity(i);
            }
        });
    }
}
