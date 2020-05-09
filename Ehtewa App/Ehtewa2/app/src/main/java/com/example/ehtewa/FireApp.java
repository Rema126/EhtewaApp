package com.example.ehtewa;

import android.app.Application;
import android.widget.Toast;

import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;

public class FireApp extends Application {
    private FirebaseAuth auth;
    private FirebaseUser user;
    @Override
    public void onCreate() {
        super.onCreate();
        //Newer version of Firebase " To connect with DB"
        if(!FirebaseApp.getApps(this).isEmpty()) {
            FirebaseDatabase.getInstance().setPersistenceEnabled(true);
        }

        // To display welcome message when the user enter the app again
        auth = FirebaseAuth.getInstance();
        user =auth.getCurrentUser();
        if(user != null){
            Toast.makeText(this,   " مرحباً " + user.getEmail(), Toast.LENGTH_SHORT).show();

        }

    }

}