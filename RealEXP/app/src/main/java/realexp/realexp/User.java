package realexp.realexp;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

public class User extends Application {

    ProgressBar expBar;
    static int level;
    static int curr_exp;
    static int max_exp = 250;

    public User()
    {
        level = 1;
        curr_exp = 0;
        max_exp = 200;
    }

    static Context context;// = getApplicationContext();

    static int get_max_exp()
    {
        return max_exp;
    }

    void init()
    {
        expBar.setMax(max_exp);
    }

    static void GainExp(int amount)
    {
        curr_exp = curr_exp + amount;
        if(curr_exp >= max_exp)
        {
            //LevelUp();
        }
    }

    void LevelUp()
    {
        Intent intent = new Intent(context, User.class);
        startActivity(intent);
        level = level + 1;

        curr_exp = curr_exp - max_exp; //Carries over exp from before
        max_exp = max_exp + 10; //TO DO: Set scaling of max EXP

        //expBar.setProgress(expBar.getProgress() - expBar.getMax()); //This always returns 0, since we can't go over the max. Should we roll over exp?
        //expBar.setMax(expBar.getMax() + 10);
        Toast toasty = Toast.makeText(context, "Level Up! You are now level " + level + ".", Toast.LENGTH_LONG);
        toasty.show();
    }

}
