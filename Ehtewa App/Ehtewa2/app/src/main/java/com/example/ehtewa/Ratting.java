package com.example.ehtewa;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatRatingBar;
import androidx.appcompat.widget.Toolbar;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Ratting extends AppCompatActivity {
    private Button submit;
    private AppCompatRatingBar Rattingbar;
    private FirebaseAuth auth;
    private FirebaseUser user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ratting);
        Toolbar toolbar = findViewById(R.id.tool);
        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(getResources().getDrawable(R.drawable.ic_back2));
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Ratting.this, Home.class);
                startActivity(intent);
                finish();

            }
        });
        submit=findViewById(R.id.submit);
        Rattingbar=findViewById(R.id.penilaian);


        Rattingbar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar  ratingBar, float nilai, boolean b) {

            }
        });

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                auth = FirebaseAuth.getInstance();
                user =auth.getCurrentUser();
                if(user != null){
                    Toast.makeText(Ratting.this," شكرًا لتقييمك "+ user.getEmail(),Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(getApplicationContext(), Home.class);
                    startActivity(i);

                }
            }
        });

    }
}
