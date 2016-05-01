package com.example.skill.firebase_test;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.firebase.client.AuthData;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;

import java.util.Map;

public class SignUpActivity extends AppCompatActivity {

    EditText passwordEditText;
    EditText emailEditText;
    EditText fullnameEditText;
    Button signUpButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        passwordEditText = (EditText) findViewById(R.id.passwordEditText);
        emailEditText = (EditText) findViewById(R.id.emailEditText);
        fullnameEditText = (EditText) findViewById(R.id.fullnameEditText);
        signUpButton = (Button) findViewById(R.id.signUpButton);

        //Security Object to encrypt data. TODO: Check it out.
        //GuardedObject object;

    signUpButton.setOnClickListener(new View.OnClickListener(){
        @Override
    public void onClick(View view){
            Firebase ref = new Firebase("https://chatlisttest.firebaseio.com/");
            ref.createUser(emailEditText.getText().toString(), passwordEditText.getText().toString(), new Firebase.ValueResultHandler<Map<String, Object>>() {
                @Override
                public void onSuccess(Map<String, Object> result) {
                    Toast.makeText(SignUpActivity.this, "Account Successfully Created", Toast.LENGTH_SHORT).show();
                    Firebase ref = new Firebase("https://chatlisttest.firebaseio.com/");

                    ref.authWithPassword(emailEditText.getText().toString(), passwordEditText.getText().toString(), new Firebase.AuthResultHandler() {
                        @Override
                        public void onAuthenticated(AuthData authData) {
                            Toast.makeText(SignUpActivity.this, "User ID: " + authData.getUid() + ", Provider: " + authData.getProvider(), Toast.LENGTH_SHORT).show();

                            Firebase users = new Firebase("https://chatlisttest.firebaseio.com/user");
                            users.child(authData.getUid()).setValue("Name:");

                            Firebase userData = new Firebase("https://chatlisttest.firebaseio.com/users/" + authData.getUid());
                            userData.child("Fullname:").setValue(fullnameEditText.getText().toString());
                            userData.child("IP Address:").setValue("123.45.67.8.9");
                        }

                        @Override
                        public void onAuthenticationError(FirebaseError firebaseError) {
                            Toast.makeText(SignUpActivity.this, "Failed to load after sign up", Toast.LENGTH_SHORT).show();
                            Log.e("Login Error", firebaseError.toString());
                        }
                    });
                }
                @Override
                public void onError(FirebaseError firebaseError) {
                    Toast.makeText(SignUpActivity.this, "Account Creation Failed", Toast.LENGTH_SHORT).show();
                    Log.e("Account Creation Error", firebaseError.toString());
                }
            });

        }
    });


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_login, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
