package realexp.realexp;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

public class Main extends AppCompatActivity {

    User user;
    private ProgressBar mProgress;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        user = ((User)getApplication()); //Figure out why this works.

        //Sets up exp bar.
        mProgress = (ProgressBar) findViewById(R.id.exp_bar);
        mProgress.setProgress(user.get_curr_exp());
        mProgress.setMax(user.get_max_exp());

        //Sets up text to update level and exp.
        TextView user_name = (TextView) findViewById(R.id.user_name);
        TextView user_level = (TextView) findViewById(R.id.user_level);
        TextView user_exp = (TextView) findViewById(R.id.user_exp);

        user_level.setText("Level: " + user.get_level());
        user_exp.setText("Experience: " + user.get_curr_exp() + "/" + user.get_max_exp());
    }

    @Override
    protected void onResume() {
        super.onResume();

        //Sets up text to update level and exp.
        TextView user_name = (TextView) findViewById(R.id.user_name);
        TextView user_level = (TextView) findViewById(R.id.user_level);
        TextView user_exp = (TextView) findViewById(R.id.user_exp);

        user_level.setText("Level: " + user.get_level());
        user_exp.setText("Experience: " + user.get_curr_exp() + "/" + user.get_max_exp());

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

    public void btnPedometer_Click(View v) {
        Intent intent = new Intent(this, Pedometer.class);
        intent.putExtra("user", user);
        startActivityForResult(intent, 420);
    }

    public void btnHeartrate_Click (View v) {
        Intent intent = new Intent(this, Heartrate.class);
        startActivity(intent);
    }

    public void btnfog_Click (View v)
    {
        Intent i = new Intent(this, Travel_Map.class);
        startActivity(i);
    }

    public void btnRunToMe_Click (View v)
    {
        Intent i = new Intent(this, Run_To_Me.class);
        startActivity(i);
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) //USED ONLY setResult is used
    {
        if(resultCode == RESULT_OK)  //Did the intent actually go through?
        {
            user = data.getParcelableExtra("updated_user"); //Update the user data.
            Log.i("Main", "Updated User " + Float.toString(user.get_iSteps()));
        }
        else if (resultCode == RESULT_CANCELED) {
            Log.i("Main", "Activity result failed");
        }
    }

    public void Gain1Exp(View view) {
        user.gain_exp(1);
        mProgress.incrementProgressBy(1);

        Snackbar snackbar = Snackbar.make(view, "You got 1 EXP!", Snackbar.LENGTH_LONG);
        snackbar.setAction("Action", null).show();

        if(mProgress.getProgress() >= mProgress.getMax())
        {
            LevelUp(view);
        }
    }

    public void Gain50Exp(View view) {
        user.gain_exp(50);
        mProgress.incrementProgressBy(50);

        Snackbar snackbar = Snackbar.make(view, "You got 50 EXP!", Snackbar.LENGTH_LONG);
        snackbar.setAction("Action", null).show();

        if(mProgress.getProgress() >= mProgress.getMax())
        {
            LevelUp(view);
        }
    }

    public void LevelUp(View view)
    {
        mProgress.setProgress(user.get_curr_exp());
        mProgress.setMax(user.get_max_exp()); //TO DO: Set scaling of max EXP

        /*Intent intent = new Intent(this, Test_Activity.class);
        intent.putExtra("user", user);
        startActivityForResult(intent, 42);*/
    }
}
