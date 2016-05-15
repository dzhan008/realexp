package realexp.realexp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class QuestViewInfo_Activity extends FragmentActivity implements View.OnClickListener {
    Button deleteButt;
    Button editButt;

    //vq stands for view quest
    TextView questTitle;
    TextView questDescription;
    TextView questType;
    TextView questDeadline;
    TextView questDifficulty;
    QuestRecyclerInfo selected_quest;
    int position;
    String status; //true if the user editted the information
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.quest_view_info);
        selected_quest = getIntent().getExtras().getParcelable("quest");
        position = getIntent().getExtras().getInt("position");
        status = "unchanged";

        questTitle = (TextView) findViewById(R.id.vq_title);
        questDescription = (TextView) findViewById(R.id.vq_description);
        questType = (TextView) findViewById(R.id.vq_type);
        questDeadline = (TextView) findViewById(R.id.vq_deadline);
        questDifficulty = (TextView)findViewById(R.id.vq_difficulty);

       questTitle.setText("Quest: " + selected_quest.get_title());
        if (!(selected_quest.get_description().matches(""))){
           questDescription.setText("Description: " + selected_quest.get_description());
        }
       questType.setText("Type: " + selected_quest.get_type());
        if (selected_quest.get_time().matches("")) {
            questDeadline.setText("Deadline: " + selected_quest.get_date());
        }
        else {
            questDeadline.setText("Deadline: " + selected_quest.get_date() + " at " +
                    selected_quest.get_time());
        }
        questDeadline.setText("Deadline: " + selected_quest.get_date());
        questDifficulty.setText("Difficulty: " + selected_quest.get_difficulty());


    }
    public void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        if (requestCode == 10) //Request code is 10 from CreateQuest_Activity
        {
            //If the intent went through
            if (resultCode == RESULT_OK) //Did the intent go through?
            {
                selected_quest = data.getExtras().getParcelable("quest");
            }
            //If user did not create a new QuestRecyclerInfo
            if (resultCode == RESULT_CANCELED)
            {
                //write code if there is no result
            }
        }
    }

    private void pressedDelete(){
        status = "delete";
        Intent intent = new Intent(this, QuestDisplay_Activity.class);
        intent.putExtra("position", position);
        intent.putExtra("update", status);
        setResult(RESULT_OK, intent);
        finish();
    }
    private void pressedBack(){
        Intent i = new Intent(this, QuestDisplay_Activity.class);
        i.putExtra("quest", selected_quest);
        i.putExtra("position", position);
        i.putExtra("update", status);
        setResult(RESULT_OK, i);
        finish();
    }
    private void pressedEdit(){
        status = "edit";
        Intent i = new Intent(this, CreateQuest_Activity.class);
        startActivityForResult(i, 10);
    }
    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.edit_btn:
                pressedEdit();
                break;
            case R.id.delete_btn:
                pressedDelete();
                break;
            case R.id.back_btn:
                pressedBack();
                break;
            default:
                break;
        }
    }
}
