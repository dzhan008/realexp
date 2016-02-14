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


        if (whattheheck.isEmpty()) {
            Toast.makeText(this, "ArrayList is empty", Toast.LENGTH_SHORT).show();
        }
        else
        {
            Toast.makeText(this, "ArrayList is NOT empty", Toast.LENGTH_SHORT).show();
        }

    }


    protected void onRestart()
    {
        super.onRestart();

        if (whattheheck.isEmpty()) {
            Toast.makeText(this, "ArrayList is empty on RESTAR", Toast.LENGTH_SHORT).show();
        }
//        setContentView(R.layout.checklist);
//
//        Toast.makeText(this, "You got Mail!", Toast.LENGTH_SHORT).show();
//        Intent i = getIntent();
//        ToDo testing = (ToDo) i.getParcelableExtra("com.package.ToDo");
//        whattheheck.add(testing);
//        Toast.makeText(this, testing.get_title(), Toast.LENGTH_SHORT).show();
//        if (whattheheck.isEmpty())
//        {
//            Toast.makeText(this, "IT EMPTY MAN", Toast.LENGTH_SHORT).show();
//        }
//        addToDo = (ImageButton) findViewById(R.id.addToDoItem);
//        addToDo.setOnClickListener(this);
    }

    protected void onStop()
    {
        super.onStop(); //always call the superclass method first

        //write some code that saves the quests onto the database.
    }

    /*Used only when setResult is used*/
    public void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        if (requestCode == 10)
        {
            Toast.makeText(this, "RequestCode was 10", Toast.LENGTH_SHORT).show();
            if (resultCode == RESULT_OK) //Did the intent go through?
            {
                Toast.makeText(this, "onActivityResult", Toast.LENGTH_SHORT).show();
                ToDo task;
                Intent i;
                i = getIntent();

                task = i.getParcelableExtra("quest");

                whattheheck.add(task);

            }
            if (resultCode == RESULT_CANCELED)
            {
                //write code if there is no result
                Toast.makeText(this, "CANCELED", Toast.LENGTH_SHORT).show();
            }
        }
    }
    //protected void onRestart(Bundle savedInstanceState)
    public void onClick(View v)
    {
        switch(v.getId())
        {
            case R.id.addToDoItem:
                startActivityForResult(new Intent(this, createToDo_Activity.class), 10);
                break;

        }
    }
}
