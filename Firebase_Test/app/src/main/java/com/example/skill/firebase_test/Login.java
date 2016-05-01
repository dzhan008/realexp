package com.example.skill.firebase_test;

import android.content.Intent;
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
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

public class Login extends AppCompatActivity {

    EditText passwordEditText;
    EditText usernameEditText;
    Button loginButton;
    Button signUpButton;
    Button blogButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        //Link Java Variable with XML Object
        passwordEditText = (EditText) findViewById(R.id.passwordEditText);
        usernameEditText = (EditText) findViewById(R.id.usernameEditText);
        loginButton = (Button) findViewById(R.id.loginButton);
        signUpButton = (Button) findViewById(R.id.signUpButton);
        blogButton = (Button) findViewById(R.id.blogButton);

        //When Login Button is clicked, do this
        loginButton.setOnClickListener(new View.OnClickListener()
        {
            //TODO: FIX EVERYTHING
            @Override
                    public void onClick(View view)
            {
                Firebase ref = new Firebase("https://chatlisttest.firebaseio.com/");
                ref.authWithPassword(usernameEditText.getText().toString(), passwordEditText.getText().toString(), new Firebase.AuthResultHandler() {
                    @Override
                    public void onAuthenticated(AuthData authData) {
                        Toast.makeText(Login.this, "User ID: " + authData.getUid() + ", Provider: " + authData.getProvider(), Toast.LENGTH_SHORT).show();

                        Firebase users = new Firebase("https://chatlisttest.firebaseio.com/user");
                        users.child(authData.getUid()).setValue("Name:");

                        Firebase userData = new Firebase("https://chatlisttest.firebaseio.com/users/" + authData.getUid());
                        userData.child("Fullname:").setValue("Bob");
                        userData.child("IP Address:").setValue("123.45.67.8.9");
                    }

                    @Override
                    public void onAuthenticationError(FirebaseError firebaseError) {
                        Toast.makeText(Login.this, "Login Failed", Toast.LENGTH_SHORT).show();
                        Log.e("Login Error", firebaseError.toString());
                    }
                });
            }

        });
        //When SignUp Button is Clicked, do this
        signUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), SignUpActivity.class);
                startActivity(intent);
            }
        });

        blogButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view) {
                // Get a reference to our posts
                Firebase ref = new Firebase("https://docs-examples.firebaseio.com/web/saving-data/fireblog/posts");

                // Attach an listener to read the data at our posts reference

                ref.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot snapshot) {
                        System.out.println("There are " + snapshot.getChildrenCount() + " blog posts");
                        for (DataSnapshot postSnapshot: snapshot.getChildren()) {
                            BlogPost post = postSnapshot.getValue(BlogPost.class);
                            System.out.println(post.getAuthor() + " - " + post.getTitle());
                        }
                    }

                    @Override
                    public void onCancelled(FirebaseError firebaseError) {
                        System.out.println("The read failed: " + firebaseError.getMessage());
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
