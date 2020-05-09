package com.example.ehtewa;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


public class changePassword extends AppCompatActivity {
    private EditText newPassword;
    private ProgressBar progressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);
        Toolbar toolbar = findViewById(R.id.tool);
        setSupportActionBar(toolbar);
        newPassword=findViewById(R.id.newpwdInp);
        progressBar = findViewById(R.id.progressBar);
        toolbar.setNavigationIcon(getResources().getDrawable(R.drawable.ic_back2));
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(changePassword.this,Home.class);
                startActivity(intent);
                finish();

            }
        });
    }
public void changePass(View V){
    String password = newPassword.getText().toString().trim();
    if (TextUtils.isEmpty(password)) {
        Toast.makeText(getApplicationContext(), "ادخل كلمة المرور!", Toast.LENGTH_SHORT).show();
        return;
    }
    if (password.length() < 6) {
        newPassword.setError("كلمة المرور قصيرة جدًا ، أدخل ٦ أحرف على الأقل !");
        return;
    }
    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
    if(checkInternetConnection()){
    progressBar.setVisibility(View.VISIBLE);
        user.updatePassword(password)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(changePassword.this, "تم تحديث كلمة المرور ", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(changePassword.this, "فشل تحديث كلمة المرور حاول لاحقا ً" +task.getException(), Toast.LENGTH_LONG).show();
                            progressBar.setVisibility(View.GONE);
                        }
                        Intent i = new Intent(getApplicationContext(), Home.class);
                        startActivity(i);
                        finish();
                    }
                });

}
    else
    {
        Toast.makeText(changePassword.this, "تحقق من اتصال الانترنت ", Toast.LENGTH_LONG).show();
    }
    }
    //يتحقق من اتصال الانترنت
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




