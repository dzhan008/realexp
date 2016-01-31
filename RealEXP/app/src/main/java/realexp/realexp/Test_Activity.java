package realexp.realexp;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

public class Test_Activity extends AppCompatActivity {

    Intent i;
    User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        i = getIntent();
        user = i.getParcelableExtra("user");
        user.set_curr_exp(user.get_curr_exp()+5);


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    public void Check(View view) //TEST
    {
        Intent resultIntent = new Intent();
        resultIntent.putExtra("updated_user",user);
        setResult(RESULT_OK, resultIntent);

        Snackbar snackbar = Snackbar.make(view, "You have " + user.get_curr_exp() + " exp.", Snackbar.LENGTH_LONG);
        snackbar.setAction("Action", null).show();
    }

}
