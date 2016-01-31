package realexp.realexp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import java.util.ArrayList;

public class CheckList_Activity extends ActionBarActivity implements View.OnClickListener {

    ImageButton addToDo;
    CheckList myCheckList;
    private ArrayList<ToDo> whattheheck = new ArrayList<>();
    public static final String STATE_TODO = "state_quest";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.checklist);

        addToDo = (ImageButton) findViewById(R.id.addToDoItem);
        addToDo.setOnClickListener(this);




    }

    protected void onRestart()
    {
        super.onRestart();
        setContentView(R.layout.checklist);

        Toast.makeText(this, "You got Mail!", Toast.LENGTH_SHORT).show();
        Intent i = getIntent();
        ToDo testing = (ToDo) i.getParcelableExtra("com.package.ToDo");
        whattheheck.add(testing);
        if (whattheheck.isEmpty())
        {
            Toast.makeText(this, "IT EMPTY MAN", Toast.LENGTH_SHORT).show();
        }
        addToDo = (ImageButton) findViewById(R.id.addToDoItem);
        addToDo.setOnClickListener(this);
    }
    //protected void onRestart(Bundle savedInstanceState)
    public void onClick(View v)
    {
        switch(v.getId())
        {
            case R.id.addToDoItem:
                finish();
                startActivity(new Intent(this, createToDo_Activity.class));
                break;

        }
    }
}
