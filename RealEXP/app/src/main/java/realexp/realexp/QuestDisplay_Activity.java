package realexp.realexp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageButton;

import java.util.ArrayList;

public class QuestDisplay_Activity extends ActionBarActivity implements View.OnClickListener {

    ImageButton addToDo;
    RecyclerView recyclerView;
    QuestRecyclerAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.checklist);

        /*Set On click Listener to "createToDo" button*/
        addToDo = (ImageButton) findViewById(R.id.addToDoItem);
        addToDo.setOnClickListener(this);

        /*~~~Creating the Recycler View~~~*/
        ArrayList<QuestRecyclerInfo> initList = new ArrayList<>(); //ArrayList to initialize data

        /*Test quests for the beginning*/
        /*QuestRecyclerInfo quest = new QuestRecyclerInfo(1, "Title","Description", "type", 5, 28, 1996, 0, 0,
        "AM/PM", "difficulty", 100, 200, 1);
        for(int i = 0; i < 60; i++) initList.add(quest);*/

        //Inflate Recycler View Object
        recyclerView = (RecyclerView) findViewById(R.id.recyclerview);

        //Create adapter
        adapter = new QuestRecyclerAdapter(this, initList);

        //Set layour Manager
        recyclerView.setLayoutManager(new LinearLayoutManager(QuestDisplay_Activity.this));

        //Set adapter
        recyclerView.setAdapter(adapter);
    }

    protected void onRestart()
    {
        super.onRestart();
        /*Setting the Adapter again to update the List*/
        recyclerView.setAdapter(adapter);
    }

    protected void onStop()
    {
        super.onStop(); //always call the superclass method first
        //write some code that saves the quests onto the database.
    }

    /*Used only when setResult is used*/
    public void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        if (requestCode == 10) //Request code is 10 from CreateQuest_Activity
        {
            //If the intent went through
            if (resultCode == RESULT_OK) //Did the intent go through?
            {
                //Get information from the createToDo Activity and store in a QuestRecyclerInfo
                QuestRecyclerInfo quest = data.getExtras().getParcelable("quest");
                //Add new quest to the data list
                adapter.addQuest(quest);
            }
            //If user did not create a new QuestRecyclerInfo
            if (resultCode == RESULT_CANCELED)
            {
                //write code if there is no result
            }
        }
        else if (requestCode == 20) //Request code is 20 from QuestViewInfo_Activity
        {
            //If the intent went through
            if (resultCode == RESULT_OK) //Did the intent go through?
            {
                int position = data.getExtras().getInt("position");
                if (data.getExtras().getString("status") == "edit") {
                    QuestRecyclerInfo quest = data.getExtras().getParcelable("quest");
                    adapter.updateQuest(quest, position);
                }
                else if (data.getExtras().getString("status") == "delete")
                {
                    adapter.deleteQuest(position);
                }

            }
            //If user did not create a new QuestRecyclerInfo
            if (resultCode == RESULT_CANCELED)
            {
                //write code if there is no result
            }
        }
    }

    public void onClick(View v)
    {
        switch(v.getId())
        {
            case R.id.addToDoItem:
                Intent i = new Intent(this, CreateQuest_Activity.class);
                startActivityForResult(i, 10);
                break;

        }
    }

}
