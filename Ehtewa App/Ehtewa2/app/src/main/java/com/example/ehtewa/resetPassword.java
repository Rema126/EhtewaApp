package com.example.ehtewa;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;


public class resetPassword extends AppCompatActivity {
    private FirebaseAuth auth;
    private EditText inputEmail;
    private Button btnReset;
    private ProgressBar progressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset_password);
        Toolbar toolbar = findViewById(R.id.tool);
        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(getResources().getDrawable(R.drawable.ic_back2));
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(resetPassword.this, login.class);
                startActivity(intent);
                finish();
            }
        });
        inputEmail = findViewById(R.id.email);
        btnReset = findViewById(R.id.confirm);
        progressBar = findViewById(R.id.progressBar);
        auth = FirebaseAuth.getInstance();

        btnReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String email = inputEmail.getText().toString().trim();
                if (TextUtils.isEmpty(email)) {
                    inputEmail.setError("ادخل عنوان البريد الالكتروني المسجل لدينا");
                    return;
                }
                if(checkInternetConnection())
                {
                    progressBar.setVisibility(View.VISIBLE);
                    auth.sendPasswordResetEmail(email)
                            .addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    progressBar.setVisibility(View.GONE);
                                    if (task.isSuccessful()) {
                                        Toast.makeText(resetPassword.this, "تم ارسال تعليمات اعادة تعيين كلمة المرور ", Toast.LENGTH_LONG).show();
                                        Intent i = new Intent(getApplicationContext(), login.class);
                                        startActivity(i);
                                        finish();
                                    } else {
                                        Toast.makeText(resetPassword.this, "فشل الارسال", Toast.LENGTH_LONG).show();
                                    }

                                }

                            });

            }
            else {
                Toast.makeText(resetPassword.this, "تحقق من اتصال الانترنت ", Toast.LENGTH_LONG).show();
                }}
        });

    }

    public boolean checkInternetConnection() {
        ConnectivityManager cm = (ConnectivityManager) getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = cm.getActiveNetworkInfo();
        return (activeNetworkInfo != null && activeNetworkInfo.isAvailable() && activeNetworkInfo.isConnected());
    }

    @Override
    protected void onResume() {
        super.onResume();
        progressBar.setVisibility(View.GONE);
    }
}
