package com.example.ehtewa;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {

    private FirebaseAuth auth;
    private FirebaseUser user;
    private FirebaseAuth.AuthStateListener authStateListener;
    private static final  String TAG = "ToolFragment";


    @Override
    public void onStart(){
        super.onStart();
        FirebaseAuth.getInstance().addAuthStateListener(authStateListener);
    }
    @Override
    public void onStop(){
        super.onStop();
        if(authStateListener != null){
            FirebaseAuth.getInstance().removeAuthStateListener(authStateListener);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setupFirebaseListener();
        auth = FirebaseAuth.getInstance();

        //This line is for tool bar
      Toolbar toolbar = findViewById(R.id.tool);
       setSupportActionBar(toolbar);


    }


    public void signIn(View v){
        Intent i = new Intent(getApplicationContext(), login.class);
        startActivity(i);
        finish();

    }

    public void signUp(View v){
        Intent i = new Intent(getApplicationContext(), newUser.class);
        startActivity(i);
        finish();

    }

    private void setupFirebaseListener (){
        authStateListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                user = auth.getCurrentUser();
                if(user!=null){
                    Log.d(TAG, "addAuthStateChanged : sign_in " +user.getUid());
                    Intent i =new Intent(MainActivity.this, Home.class);
                    i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK| Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(i);

                }

            }
        };
    }

}
