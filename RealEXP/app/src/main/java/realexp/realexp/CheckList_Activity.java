package realexp.realexp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class CheckList_Activity extends ActionBarActivity implements View.OnClickListener {

    ImageButton addToDo;
    private ArrayList<ToDo> initList = new ArrayList<>();
    //public static final String STATE_TODO = "state_quest";
    private ListView test;
    private CheckList mainList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.checklist);

        /*Set On click Listener to "createToDo" button*/
        addToDo = (ImageButton) findViewById(R.id.addToDoItem);
        addToDo.setOnClickListener(this);

        /*Added some smaple quests in the beginning*/

        /*Creating the List View*/
        test = (ListView) findViewById(R.id.listView);
        mainList = new CheckList(this, initList);
        test.setAdapter(mainList);
        test.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Object o = test.getItemAtPosition(position);
                ToDo selectedQuest = (ToDo) o;
                //Toast.makeText(this, "Selected: " + selectedQuest, Toast.LENGTH_SHORT).show();
            }
        });

    }

    protected void onRestart()
    {
        super.onRestart();

        /*
        if (whattheheck.isEmpty()) {
            Toast.makeText(this, "ArrayList is empty on RESTART", Toast.LENGTH_SHORT).show();
        }
        else  Toast.makeText(this, "ArrayList is not empty on RESTART", Toast.LENGTH_SHORT).show();
        */

        /*Setting the Adapter again to update the List*/
        test.setAdapter(mainList);
        test.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Object o = test.getItemAtPosition(position);
                ToDo selectedQuest = (ToDo) o;
                //Toast.makeText(this, "Selected: " + selectedQuest, Toast.LENGTH_SHORT).show();
            }
        });
    }

    protected void onStop()
    {
        super.onStop(); //always call the superclass method first

        //write some code that saves the quests onto the database.
    }

    /*Used only when setResult is used*/
    public void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        if (requestCode == 10) //Request code is 10 for my case
        {
            Toast.makeText(this, "RequestCode was 10", Toast.LENGTH_SHORT).show();
            //If the intent went through
            if (resultCode == RESULT_OK) //Did the intent go through?
            {
                Toast.makeText(this, "onActivityResult", Toast.LENGTH_SHORT).show();
                //Intent i = getIntent(); //Gets the intent from previous activity

                //Get information from the createToDo Activity and store in a ToDo
                ToDo task = data.getExtras().getParcelable("quest");
                //Add the ToDo to CheckList's Array of ToDos. Crashes here.
                mainList.addToDo(task);
            }
            //If user did not create a new ToDo
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
                Intent i = new Intent(this, createToDo_Activity.class);
                startActivityForResult(i, 10);
                break;

        }
    }

}
