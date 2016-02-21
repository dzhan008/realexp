package realexp.realexp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

//Placeholder main file with simple UI that opens Map
public class Main extends Activity{

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_main);
    }

    public void g_map_Click(View view) {
        Intent intent = new Intent(this, Map.class);
        startActivity(intent);
    }
}