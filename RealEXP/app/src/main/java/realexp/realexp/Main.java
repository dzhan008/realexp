package realexp.realexp;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

public class Main extends AppCompatActivity {

    private static final int PROGRESS = 0x1;

    private ProgressBar mProgress;
    int level = 1;
    int max_exp = 100;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mProgress = (ProgressBar) findViewById(R.id.exp_bar);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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

    public void GainExp(View view)
    {
        mProgress.incrementProgressBy(5);
        if(mProgress.getProgress() >= mProgress.getMax())
        {
            level = level + 1;
            mProgress.setMax(mProgress.getMax() + 10);
            mProgress.setProgress(0);

            Toast toasty = Toast.makeText(this, "Level Up! You are now level " + level + ".", Toast.LENGTH_LONG);
            toasty.show();

        }

        Snackbar snackbar = Snackbar.make(view, "You got 1 EXP!", Snackbar.LENGTH_LONG);
        snackbar.setAction("Action", null).show();
    }
}
