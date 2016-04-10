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

public class createToDo_Activity extends FragmentActivity implements View.OnClickListener, AdapterView.OnItemSelectedListener {
    Button bSubmit;
    Button bCancel;
    Button bDate;
    Button bTime;

    EditText tdtitle, tddescription, tdtype;
    TextView tddate, tdtime;

    Spinner spinner;
    String difficulty;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_createtodo);

        tdtitle = (EditText) findViewById(R.id.add_title);
        tddescription = (EditText) findViewById(R.id.add_description);
        tdtype = (EditText) findViewById(R.id.add_type);

        bSubmit = (Button) findViewById(R.id.button_submit);
        bSubmit.setOnClickListener(this);

        bCancel = (Button) findViewById(R.id.button_cancel);
        bCancel.setOnClickListener(this);

        bDate = (Button) findViewById(R.id.button_date);
        bDate.setOnClickListener(this);

        bTime = (Button) findViewById(R.id.button_time);
        bTime.setOnClickListener(this);

        spinner = (Spinner) findViewById(R.id.spinner);
        ArrayAdapter difficultyAdapter = ArrayAdapter.createFromResource(this, R.array.Difficulty,
                android.R.layout.simple_spinner_item);
        spinner.setAdapter(difficultyAdapter);
        spinner.setOnItemSelectedListener(this);
    }

    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l)
    {
        TextView diffText = (TextView) view;
        Toast.makeText(this, "You selected " + diffText.getText(), Toast.LENGTH_SHORT).show();
        difficulty = String.valueOf(diffText.getText());
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    public void onClick(View v)
    {
        switch(v.getId())
        {
            case R.id.button_date:
                DialogFragment myDatePickerFragment = new DatePickerFragment();
                myDatePickerFragment.show(getSupportFragmentManager(), "datePicker");
                break;

            case R.id.button_time:
                DialogFragment myTimePickerFragment = new TimePickerFragment();
                myTimePickerFragment.show(getSupportFragmentManager(), "timePicker");
                break;

            case R.id.button_submit:
                //get the text the user put in and convert it to a string
                if (!checkEmptyInput()) {
                    createQuest();
                }
                break;
            case R.id.button_cancel:
                Intent i = new Intent(this, CheckList_Activity.class);
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

        return false;
    }

    private void createQuest()
    {
        /*
        private int ID;
        private String title;
        private String description;
        private int month;
        private int date;
        private int year;
        private int hourTime;
        private int minuteTime;
        private String suffxiTime;
        private int difficulty; //1 Easy, 2 Medium, 3 Hard
        private int experience;
        private int gold;
        private int priority;
         */


        //Store information to create ToDo object
        String Title = tdtitle.getText().toString();
        String Description = tddescription.getText().toString();
        String Type = tdtype.getText().toString();


        tddate = (TextView) findViewById(R.id.date_text);
        tdtime = (TextView) findViewById(R.id.time_text);

        //Get deadline date info
        String Deadline = tddate.getText().toString();
        String[] splitStringArray = Deadline.split("/");
        Integer month = Integer.valueOf(splitStringArray[0]);
        Integer date = Integer.valueOf(splitStringArray[1]);
        Integer year  = Integer.valueOf(splitStringArray[2]);

        //Get deadline time info
        String Time = tdtime.getText().toString();
        String[] splitSuffix = Time.split(" ");
        String suffixTime = String.valueOf(splitSuffix[1]);

        String numTime = String.valueOf(splitSuffix[0]);
        String [] splitHourandTime = numTime.split(":");
        Integer hourTime  = Integer.parseInt(splitHourandTime[0]);
        Integer minuteTime  = Integer.parseInt(splitHourandTime[1]);


        //Toast.makeText(this, Title, Toast.LENGTH_SHORT).show();

        //Create ToDo object to pass back to the calling activity
        ToDo quest = new ToDo(1, Title,Description, Type, month, date, year, hourTime, minuteTime,
                suffixTime, difficulty, 100, 200, 1);

        //Create an intent and add the object to the intent
        Intent intent = new Intent(this, CheckList_Activity.class);
        intent.putExtra("quest", quest);
        setResult(RESULT_OK, intent);
        finish();

    }
}

