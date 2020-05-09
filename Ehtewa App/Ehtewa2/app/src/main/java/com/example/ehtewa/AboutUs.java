package com.example.ehtewa;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;


public class AboutUs extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_us);
        Toolbar toolbar = findViewById(R.id.tool);
        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(getResources().getDrawable(R.drawable.ic_back2));
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AboutUs.this, Home.class);
                startActivity(intent);
                finish();

            }
        });

    }

    public void Twitter(View v) {
        Intent l = new Intent(Intent.ACTION_VIEW, Uri.parse("https://twitter.com/home"));
        startActivity(l);
    }

    public void facebook(View v) {
        Intent l = new Intent(Intent.ACTION_VIEW, Uri.parse("https://facebook.com/home"));
        startActivity(l);
    }
    public void whatsapp(View v) {
        Intent l = new Intent(Intent.ACTION_VIEW, Uri.parse("https://whatsapp.com/home"));
        startActivity(l);
    }
    public void gmail(View v) {
        Intent l = new Intent(Intent.ACTION_VIEW, Uri.parse("https://gmail.com/home"));
        startActivity(l);
    }
}