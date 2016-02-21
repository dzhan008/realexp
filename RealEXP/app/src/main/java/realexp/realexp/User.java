package realexp.realexp;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import java.io.Serializable;

import java.io.Serializable;

/**
 * Created by Skillinazn on 12/24/2015.
 */
public class User extends Application implements Parcelable {

    private int level = 1;
    private int curr_exp = 0;
    private int max_exp = 100;
    private float steps = 0;
    private float iSteps = -1;

    //static Context context = getApplicationContext();

    public User()
    {
        level = 1;
        curr_exp = 0;
        max_exp = 100;
        steps = 0;
        iSteps = -1;
    }

    public User(Parcel p)
    {   //Note: This is read in order of what was written!
        level = p.readInt();
        curr_exp = p.readInt();
        max_exp = p.readInt();
        steps = p.readFloat();
        iSteps = p.readFloat();
    }

    public int describeContents()
    {
        return 0;
    }

    public void writeToParcel (Parcel dest, int flags)
    {
        dest.writeInt(level);
        dest.writeInt(curr_exp);
        dest.writeInt(max_exp);
        dest.writeFloat(steps);
        dest.writeFloat(iSteps);
    }

    public static final Parcelable.Creator<User> CREATOR = new  Parcelable.Creator<User>()
    {
        @Override
        public User createFromParcel(Parcel parcel)
        {
            return new User(parcel);
        }

        @Override
        public User[] newArray(int size)
        {
            return new User[size];
        }

    };

    public int get_max_exp()
    {
        return this.max_exp;
    }

    public int get_curr_exp() { return this.curr_exp; }

    void set_max_exp(int exp)
    {
        max_exp = exp;
    }

    void set_curr_exp(int exp)
    {
        curr_exp = exp;
    }

    void gain_exp(int amount)
    {
        this.curr_exp = this.curr_exp + amount;
        if(curr_exp >= max_exp)
        {
            //LevelUp();
        }
    }

    void set_level(int lvl)
    {
        level = lvl;
    }

    void level_up(View view)
    {
        level = level + 1;
        if(curr_exp > max_exp)
        {
            curr_exp -= max_exp; //Carries over exp from before
        }
        else
        {
            curr_exp *= 0;
        }
        max_exp += 10; //TO DO: Set scaling of max EXP

        Snackbar snackbar = Snackbar.make(view, "Level up! You are now level " + level + ".", Snackbar.LENGTH_LONG);
        snackbar.setAction("Action", null).show();
    }

    void set_steps (float s) {
        steps = s;
    }
    float get_steps () {
        return steps;
    }

    void set_iSteps (float s) {
        iSteps = s;
    }

    float get_iSteps() {
        return iSteps;
    }

}
