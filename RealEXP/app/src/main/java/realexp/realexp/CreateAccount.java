package realexp.realexp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;

import java.util.Map;

public class CreateAccount extends AppCompatActivity {

    EditText textEmail;
    EditText textPass;
    EditText textFname;
    EditText textLname;

    Spinner spinnerGender;
    Spinner spinnerMonth;
    Spinner spinnerDay;
    Spinner spinnerYear;

    Button buttonConfirm;
    Button buttonCancel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);

        textEmail = (EditText) findViewById(R.id.email);
        textPass = (EditText) findViewById(R.id.password);
        textFname = (EditText) findViewById(R.id.firstname);
        textLname = (EditText) findViewById(R.id.lastname);

        spinnerGender = (Spinner) findViewById(R.id.gender);
        spinnerMonth = (Spinner) findViewById(R.id.month);
        spinnerDay = (Spinner) findViewById(R.id.day);
        spinnerYear = (Spinner) findViewById(R.id.year);

        buttonConfirm = (Button) findViewById(R.id.confirm);
        buttonCancel = (Button) findViewById(R.id.cancel);

        buttonConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Firebase ref = new Firebase("https://realexp.firebaseio.com/");
                ref.createUser(textEmail.getText().toString(), textPass.getText().toString() , new Firebase.ValueResultHandler<Map<String, Object>>() {
                    @Override
                    public void onSuccess(Map<String, Object> result) {
                        Toast.makeText(CreateAccount.this, "Account Successfully Created", Toast.LENGTH_SHORT).show();

                        Firebase userData = new Firebase("https://realexp.firebaseio.com/users/" + result.get("uid"));
                        userData.child("fullname").setValue(textFname.getText().toString() + " " + textLname.getText().toString());
                    }

                    @Override
                    public void onError(FirebaseError firebaseError) {
                        Toast.makeText(CreateAccount.this, "Account Creation Failed", Toast.LENGTH_SHORT).show();
                        Log.e("Account Creation Error",firebaseError.toString());
                    }
                });

            }
        });

    }
}
