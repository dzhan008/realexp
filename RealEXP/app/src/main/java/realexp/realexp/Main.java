package realexp.realexp;

import android.content.Context;
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


    private ProgressBar mProgress;
    int level = 1;
    static int max_exp;
    User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        max_exp = user.get_max_exp();

        //user.expBar = (ProgressBar) findViewById(R.id.exp_bar);
        mProgress = (ProgressBar) findViewById(R.id.exp_bar);
        mProgress.setMax(max_exp);

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

    public void Gain1Exp(View view) {
        user.GainExp(1); //FIX
        Snackbar snackbar = Snackbar.make(view, "You got 1 EXP!", Snackbar.LENGTH_LONG);
        snackbar.setAction("Action", null).show();
    }

    public void Gain50Exp(View view) {
        //mProgress.incrementProgressBy(50);

        if(mProgress.getProgress() >= mProgress.getMax())
        {
            //LevelUp(view);
        }

        Snackbar snackbar = Snackbar.make(view, "You got 50 EXP!" + max_exp, Snackbar.LENGTH_LONG);
        snackbar.setAction("Action", null).show();

    }

    public void LevelUp(View view)
    {
        user.LevelUp();
        /*level = level + 1;
        mProgress.setProgress(mProgress.getProgress() - mProgress.getMax()); //This always returns 0, since we can't go over the max. Should we roll over exp?
        mProgress.setMax(mProgress.getMax() + 10); //TO DO: Set scaling of max EXP

        Toast toasty = Toast.makeText(this, "Level Up! You are now level " + level + ".", Toast.LENGTH_LONG);
        toasty.show();*/
    }
}
