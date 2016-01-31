package realexp.realexp;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;

public class Main extends AppCompatActivity {

    User user;
    private ProgressBar mProgress;
    int max_exp;
    int curr_exp;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        user = ((User)getApplication()); //Figure out why this works.
        mProgress = (ProgressBar) findViewById(R.id.exp_bar);
        mProgress.setMax(user.get_max_exp());

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

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        if(resultCode == RESULT_OK)
        {
            user = data.getParcelableExtra("updated_user");
        }
    }

    public void Gain1Exp(View view) {
        user.gain_exp(1); //FIX
        mProgress.incrementProgressBy(1);

        Snackbar snackbar = Snackbar.make(view, "You got 1 EXP!" + user.get_curr_exp(), Snackbar.LENGTH_LONG);
        snackbar.setAction("Action", null).show();

        if(mProgress.getProgress() >= mProgress.getMax())
        {
            LevelUp(view);
        }
    }

    public void Gain50Exp(View view) {
        user.gain_exp(50);
        mProgress.incrementProgressBy(50);

        Snackbar snackbar = Snackbar.make(view, "You got 50 EXP!" + user.get_curr_exp(), Snackbar.LENGTH_LONG);
        snackbar.setAction("Action", null).show();

        if(mProgress.getProgress() >= mProgress.getMax())
        {
            LevelUp(view);
        }
    }

    public void LevelUp(View view)
    {
        //user.level_up(view);

        mProgress.setProgress(user.get_curr_exp());
        mProgress.setMax(user.get_max_exp()); //TO DO: Set scaling of max EXP

        Intent intent = new Intent(this, Test_Activity.class);
        intent.putExtra("user", user);
        startActivityForResult(intent, 42);
    }
}
