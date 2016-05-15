package realexp.realexp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.firebase.client.AuthData;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;

public class Login extends AppCompatActivity {

    User user;

    EditText textEmail;
    EditText textPass;

    Button buttonLogin;
    Button buttonCreateAccount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Firebase.setAndroidContext(this);

        textEmail = (EditText) findViewById(R.id.email);
        textPass = (EditText) findViewById(R.id.password);

        buttonLogin = (Button) findViewById(R.id.login);
        buttonCreateAccount = (Button) findViewById(R.id.createAccount);

        buttonCreateAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick (View view){
                Intent i = new Intent(getApplicationContext(), CreateAccount.class);
                startActivity(i);
            }
        });

        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick (View view){
                Firebase ref = new Firebase("https://realexp.firebaseio.com/");
                ref.authWithPassword(textEmail.getText().toString(), textPass.getText().toString(), new Firebase.AuthResultHandler() {
                    @Override
                    public void onAuthenticated(AuthData authData) {
                        Toast.makeText(Login.this, "User ID: " + authData.getUid() + ", Provider: " + authData.getProvider(), Toast.LENGTH_SHORT).show();

                        Intent intent = new Intent( getApplicationContext(), Main.class);
                        startActivity(intent);

                    }

                    @Override
                    public void onAuthenticationError(FirebaseError firebaseError) {
                        Toast.makeText(Login.this, "Login Failed", Toast.LENGTH_SHORT).show();
                        Log.e("Login Error", firebaseError.toString());
                    }
                });
            }
        });

    }

}
