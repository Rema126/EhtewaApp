package com.example.ehtewa;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.ehtewa.chat.Utility;
import com.example.ehtewa.itemData.ItemData_save_new_user;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

import static com.example.ehtewa.login.FireBase_Root_Url_Login;


public class newUser extends AppCompatActivity {
    private EditText inputEmail, inputPassword ;
    private Button  btnSignUp;
    private ProgressBar progressBar;
    private FirebaseUser user;
    private FirebaseAuth auth;
    DatabaseReference ref ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_user);

        progressBar = findViewById(R.id.progressBar);

        Toolbar toolbar = findViewById(R.id.tool);
        setSupportActionBar(toolbar);
        // go back to the main
        toolbar.setNavigationIcon(getResources().getDrawable(R.drawable.ic_back2));
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(newUser.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });

        //Get Firebase auth instance
        auth = FirebaseAuth.getInstance();
        btnSignUp = findViewById(R.id.signUpbtn);
        inputEmail = findViewById(R.id.mail);
        inputPassword = findViewById(R.id.pwdInp);
//        inputUsername = findViewById(R.id.username);

        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String email = inputEmail.getText().toString().trim();
                String password = inputPassword.getText().toString().trim();

                if (TextUtils.isEmpty(email)) {
                    inputEmail.setError("ادخل عنوان البريد الإلكتروني!");
                    return;
                }

                if (TextUtils.isEmpty(password)) {
                    inputPassword.setError("ادخل كلمة المرور!");
                    return;
                }

                    if (password.length() < 6) {
                        inputPassword.setError("كلمة المرور قصيرة جدًا ، أدخل ٦ أحرف على الأقل !");
                        return;
                    }

                    if(checkInternetConnection()){
                progressBar.setVisibility(View.VISIBLE);
                //create user
                auth.createUserWithEmailAndPassword(email, password )
                        .addOnCompleteListener(newUser.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                               if(task.isSuccessful()){
                                   AddNewUser(task.getResult().getUser().getEmail()
                                           ,task.getResult().getUser().getProviderId()
                                           ,task.getResult().getUser().getUid());
                                   Toast.makeText(getApplicationContext(), "تم التسجيل بنجاح !", Toast.LENGTH_SHORT).show();
                               }
                                progressBar.setVisibility(View.GONE);
                                if (!task.isSuccessful()) {
                                    // error message will be displayed depending on the kind of the error
                                    Toast.makeText(newUser.this, " فشل المصادقة " + task.getException(),
                                            Toast.LENGTH_LONG).show();
                                } else {
                                    startActivity(new Intent(newUser.this, Home.class));
                                    finish();
                                }
                            }
                        });

            }
                    else {
                        Toast.makeText(newUser.this, "تحقق من اتصال الانترنت ", Toast.LENGTH_LONG).show();
                    }
            }
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
    public void AddNewUser(String UserEmail,String UserProvider,String UserID)
    {
        ref = FirebaseDatabase.getInstance() .getReferenceFromUrl(FireBase_Root_Url_Login);

        ItemData_save_new_user UserLocation = new ItemData_save_new_user(UserID,UserEmail,UserProvider);
        ref.child(UserID).setValue(UserLocation); // Every Added Generate New branch And Store Data In This branch

        Utility.getInstance().setDataBykeyValue(getApplicationContext(),"email",UserEmail);
        Utility.getInstance().setDataBykeyValue(getApplicationContext(),"ProviderId",UserProvider);
        Utility.getInstance().setDataBykeyValue(getApplicationContext(),"Uid",UserID);
        Utility.getInstance().setDataBykeyValue(getApplicationContext(),"account_type","user");


        Log.e("email",String.valueOf(UserEmail));
        Log.e("ProviderId",String.valueOf(UserProvider));
        Log.e("Uid",String.valueOf(UserID));
        Intent intent = new Intent(newUser.this, Home.class);
        startActivity(intent);
        finish();

    }

}