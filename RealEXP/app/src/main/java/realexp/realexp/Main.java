package realexp.realexp;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;

public class Main extends Activity{

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_main);
    }

     public void g_map_Fitness(View view) {
        Uri gmmIntentUri = Uri.parse("geo:33.975,-117.329");
        Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
        if(mapIntent.resolveActivity(getPackageManager()) != null) {
            startActivity(mapIntent);
        }
    }
    public void g_map_Travel(View view) {
        Uri gmmIntentUri = Uri.parse("google.navigation:q=5262KingStreet+Riverside+UnitedStates=tf");
        Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
        mapIntent.setPackage("com.google.android.apps.maps");
        if(mapIntent.resolveActivity(getPackageManager()) != null) {
            startActivity(mapIntent);
        }
    }
}