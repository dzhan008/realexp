package com.example.skill.firebase_test;

import android.app.Application;

import com.firebase.client.Firebase;

/**
 * Created by skill on 4/13/2016.
 */
public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Firebase.setAndroidContext(this);

    }
}
