package realexp.realexp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class createToDo_Activity extends FragmentActivity implements View.OnClickListener {
    Button bSubmit;
    Button bCancel;
    Button bDate;
    Button bTime;

    EditText tdtitle, tddescription, tdtype;
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
                    finish();
                    startActivity(new Intent(this, CheckList_Activity.class));
                }
                break;
        }
    }

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
        String Title = tdtitle.getText().toString();
        String Description = tddescription.getText().toString();
        String Type = tdtype.getText().toString();

        //ToDo temp = new ToDo(Title,Description,1, 1, 1, 1, 1, 1, 1);
        Intent parent = getIntent();
    }
}

