package realexp.realexp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class EditQuest_Activity extends FragmentActivity implements View.OnClickListener, AdapterView.OnItemSelectedListener {
    Button bSubmit;
    Button bCancel;
    Button bDate;
    Button bTime;

    EditText tdtitle, tddescription;
    TextView tddate, tdtime;

    Spinner spinnerDifficulty;
    String difficulty;

    Spinner spinnerType;
    String type;

    QuestRecyclerInfo selected_quest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_createtodo);
        selected_quest = getIntent().getExtras().getParcelable("quest");
        String difficultyArray[] = {"Easy", "Medium", "Hard"};
        String typeArray[] = {"Math", "Cooking", "English", "Sports", "Exercise", "Club", "Science",
        "Social Studies", "Event", "Other"};
        int diff_pos = 0;
        int type_pos = 0;
        for (int i = 0; i < difficultyArray.length; i++)
        {
            if(selected_quest.get_difficulty().matches(difficultyArray[i])) {
                diff_pos = i + 1;
                break;
            }
        }


        for (int i = 0; i < typeArray.length; i++)
        {
            if(selected_quest.get_type().matches(typeArray[i])){
                type_pos = i + 1;
                break;
            }
        }
        Toast.makeText(this, "Diff position: " + diff_pos + " Type position: " + type_pos, Toast.LENGTH_SHORT).show();
        tdtitle = (EditText) findViewById(R.id.add_title);
        tdtitle.setText(selected_quest.get_title(), TextView.BufferType.EDITABLE);

        tddescription = (EditText) findViewById(R.id.add_description);
        tddescription.setText(selected_quest.get_description(), TextView.BufferType.EDITABLE);

        TextView quest_date = (TextView) findViewById(R.id.date_text);
        quest_date.setText(selected_quest.get_deadline());

        TextView quest_time = (TextView) findViewById(R.id.time_text);
        if (selected_quest.get_time().matches("")) quest_time.setText("00:00 AM/PM");
        else quest_time.setText(selected_quest.get_time());


        bSubmit = (Button) findViewById(R.id.button_submit);
        bSubmit.setOnClickListener(this);

        bCancel = (Button) findViewById(R.id.button_cancel);
        bCancel.setOnClickListener(this);

        bDate = (Button) findViewById(R.id.button_date);
        bDate.setOnClickListener(this);

        bTime = (Button) findViewById(R.id.button_time);
        bTime.setOnClickListener(this);

        spinnerDifficulty = (Spinner) findViewById(R.id.spinnerDifficulty);
        ArrayAdapter difficultyAdapter = ArrayAdapter.createFromResource(this, R.array.Difficulty,
                android.R.layout.simple_spinner_item);
        spinnerDifficulty.setAdapter(difficultyAdapter);
        spinnerDifficulty.setSelection(diff_pos);
        spinnerDifficulty.setOnItemSelectedListener(this);

        spinnerType = (Spinner) findViewById(R.id.spinnerType);
        ArrayAdapter typeAdapter = ArrayAdapter.createFromResource(this, R.array.Type,
                android.R.layout.simple_spinner_item);
        spinnerType.setAdapter(typeAdapter);
        spinnerType.setSelection(type_pos);
        spinnerType.setOnItemSelectedListener(this);

    }

    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l)
    {
        TextView spinnerText = (TextView) view;
        switch (adapterView.getId())
        {
            case R.id.spinnerType:
                type = String.valueOf(spinnerText.getText());
                break;
            case R.id.spinnerDifficulty:
                difficulty = String.valueOf(spinnerText.getText());
                break;
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    public void onClick(View v)
    {
        switch(v.getId())
        {
            case R.id.button_date:
                DialogFragment myDatePickerFragment = new Picker_DateFragment();
                myDatePickerFragment.show(getSupportFragmentManager(), "datePicker");
                break;

            case R.id.button_time:
                DialogFragment myTimePickerFragment = new Picker_TimeFragment();
                myTimePickerFragment.show(getSupportFragmentManager(), "timePicker");
                break;

            case R.id.button_submit:
                //get the text the user put in and convert it to a string
                if (!checkEmptyInput()) {
                    createQuest();
                }
                break;
            case R.id.button_cancel:
                Intent i = new Intent(this, QuestDisplay_Activity.class);
                setResult(RESULT_CANCELED, i);
                finish();
                break;

        }
    }

    /*Checks if the user put in a Title or Deadline. These two things must be required to make a
    quest
    Return true if it was empty. False if filled in correctly*/
    private boolean checkEmptyInput()
    {
        EditText titleEditText = (EditText) findViewById(R.id.add_title);
        String questTitle = titleEditText.getText().toString();
        if (questTitle.matches("")) {
            Toast.makeText(this, "You did not enter a quest title", Toast.LENGTH_SHORT).show();
            return true;
        }
        TextView dateTextView = (TextView) findViewById(R.id.date_text);
        String dateSelect = dateTextView.getText().toString();
        if (dateSelect.matches("Month Day Year")) {
            Toast.makeText(this, "You did not enter a deadline date", Toast.LENGTH_SHORT).show();
            return true;
        }
        if (type.matches("Pick a Type"))
        {
            Toast.makeText(this, "You did not pick a type", Toast.LENGTH_SHORT).show();
            return true;
        }
        if (difficulty.matches("Pick a Difficulty"))
        {
            Toast.makeText(this, "You did not pick a difficulty", Toast.LENGTH_SHORT).show();
            return true;
        }
        return false;
    }

    private void createQuest()
    {
        /*
        Quest data types for reference
        private int ID;
        private String title;
        private String description;
        private String type;
        private int month;
        private int date;
        private int year;
        private int hourTime;
        private int minuteTime;
        private String suffixTime;
        private String difficulty; //1 Easy, 2 Medium, 3 Hard
        private int experience;
        private int gold;
        private int priority;
         */


        //Store information to create QuestRecyclerInfo object
        String Title = tdtitle.getText().toString();
        String Description = tddescription.getText().toString();


        tddate = (TextView) findViewById(R.id.date_text);
        tdtime = (TextView) findViewById(R.id.time_text);

        //Get deadline date info
        String Deadline = tddate.getText().toString();
        String[] splitStringArray = Deadline.split("/");
        Integer month = Integer.valueOf(splitStringArray[0]);
        Integer date = Integer.valueOf(splitStringArray[1]);
        Integer year  = Integer.valueOf(splitStringArray[2]);

        //Get deadline time info
        Integer hourTime = 0;
        Integer minuteTime = 0;
        String suffixTime = "";
        String Time = tdtime.getText().toString();
        if (!(Time.matches("00:00 AM/PM"))) {
            String[] splitSuffix = Time.split(" ");
            suffixTime = String.valueOf(splitSuffix[1]);

            String numTime = String.valueOf(splitSuffix[0]);
            String[] splitHourandTime = numTime.split(":");
            hourTime = Integer.parseInt(splitHourandTime[0]);
            minuteTime = Integer.parseInt(splitHourandTime[1]);
        }

        //Create QuestRecyclerInfo object to pass back to the calling activity
        QuestRecyclerInfo quest = new QuestRecyclerInfo(1, Title,Description, type, month, date, year, hourTime, minuteTime,
                suffixTime, difficulty, 100, 200, 1);

        //Create an intent and add the object to the intent
        Intent intent = new Intent(this, QuestDisplay_Activity.class);
        intent.putExtra("quest", quest);
        setResult(RESULT_OK, intent);
        finish();
    }
}

