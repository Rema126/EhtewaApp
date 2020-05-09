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
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import android.util.Log;

import com.example.ehtewa.chat.Utility;
import com.example.ehtewa.itemData.ItemData_save_new_user;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;



public class login extends AppCompatActivity {

    private ProgressBar progressBar;
    private EditText inputEmail, inputPassword;
    private FirebaseAuth auth;
    private Button btnLogin, forgetPass;
    private FirebaseUser user;

    DatabaseReference ref ;
    public static final String FireBase_Root_Url_Login   = "https://ehtewa-20369.firebaseio.com/Users";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Toolbar toolbar = findViewById(R.id.tool);
        setSupportActionBar(toolbar);


        inputEmail = findViewById(R.id.email);
        inputPassword = findViewById(R.id.pwdInp);
        btnLogin = findViewById(R.id.logInBtn);
        forgetPass = findViewById(R.id.forgetPass);
        progressBar = findViewById(R.id.progressBar);

        auth = FirebaseAuth.getInstance();

        // go back to the main activity
        toolbar.setNavigationIcon(getResources().getDrawable(R.drawable.ic_back2));
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(login.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = inputEmail.getText().toString();
                final String password = inputPassword.getText().toString();

                if(email.equals("consult_1@gmail.com")&&password.equals("consult1"))
                {
                    Utility.getInstance().setDataBykeyValue(getApplicationContext(),"email","consult_1@gmail.com");
                    Utility.getInstance().setDataBykeyValue(getApplicationContext(),"ProviderId","consult1");
                    Utility.getInstance().setDataBykeyValue(getApplicationContext(),"Uid","consult1");
                    Utility.getInstance().setDataBykeyValue(getApplicationContext(),"account_type","consult");

                    Intent intent = new Intent(login.this, Home.class);
                    startActivity(intent);
                    finish();
                }
                else if(email.equals("consult_2@gmail.com")&&password.equals("consult2"))
                {
                    Utility.getInstance().setDataBykeyValue(getApplicationContext(),"email","consult_2@gmail.com");
                    Utility.getInstance().setDataBykeyValue(getApplicationContext(),"ProviderId","consult2");
                    Utility.getInstance().setDataBykeyValue(getApplicationContext(),"Uid","consult2");
                    Utility.getInstance().setDataBykeyValue(getApplicationContext(),"account_type","consult");

                    Intent intent = new Intent(login.this, Home.class);
                    startActivity(intent);
                    finish();
                }
                else if(email.equals("consult_3@gmail.com")&&password.equals("consult3"))
                {
                    Utility.getInstance().setDataBykeyValue(getApplicationContext(),"email","consult_3@gmail.com");
                    Utility.getInstance().setDataBykeyValue(getApplicationContext(),"ProviderId","consult3");
                    Utility.getInstance().setDataBykeyValue(getApplicationContext(),"Uid","consult3");
                    Utility.getInstance().setDataBykeyValue(getApplicationContext(),"account_type","consult");

                    Intent intent = new Intent(login.this, Home.class);
                    startActivity(intent);
                    finish();
                }
                else if(email.equals("consult_4@gmail.com")&&password.equals("consult4"))
                {
                    Utility.getInstance().setDataBykeyValue(getApplicationContext(),"email","consult_4@gmail.com");
                    Utility.getInstance().setDataBykeyValue(getApplicationContext(),"ProviderId","consult4");
                    Utility.getInstance().setDataBykeyValue(getApplicationContext(),"Uid","consult4");
                    Utility.getInstance().setDataBykeyValue(getApplicationContext(),"account_type","consult");

                    Intent intent = new Intent(login.this, Home.class);
                    startActivity(intent);
                    finish();
                }
                else if(email.equals("consult_5@gmail.com")&&password.equals("consult5"))
                {
                    Utility.getInstance().setDataBykeyValue(getApplicationContext(),"email","consult_5@gmail.com");
                    Utility.getInstance().setDataBykeyValue(getApplicationContext(),"ProviderId","consult5");
                    Utility.getInstance().setDataBykeyValue(getApplicationContext(),"Uid","consult5");
                    Utility.getInstance().setDataBykeyValue(getApplicationContext(),"account_type","consult");
                    Intent intent = new Intent(login.this, Home.class);
                    startActivity(intent);
                    finish();
                }

                else if(email.equals("ghadir@gmail.com")&&password.equals("ghadir1"))
                {
                    Utility.getInstance().setDataBykeyValue(getApplicationContext(),"email","ghadir@gmail.com");
                    Utility.getInstance().setDataBykeyValue(getApplicationContext(),"ProviderId","ghadir1");
                    Utility.getInstance().setDataBykeyValue(getApplicationContext(),"Uid","ghadir1");
                    Utility.getInstance().setDataBykeyValue(getApplicationContext(),"account_type","consult");

                    Intent intent = new Intent(login.this, Home.class);
                    startActivity(intent);
                    finish();
                }

                else {

                if (TextUtils.isEmpty(email)) {
                    inputEmail.setError("ادخل عنوان البريد الإلكتروني!");
                    return;
                }

                if (TextUtils.isEmpty(password)) {
                    inputPassword.setError("ادخل كلمة المرور!");
                    return;
                }

                //authenticate user
                 if(checkInternetConnection()){
                progressBar.setVisibility(View.VISIBLE);
                auth.signInWithEmailAndPassword(email, password)
                        .addOnCompleteListener(login.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                              progressBar.setVisibility(View.GONE);
                                if (!task.isSuccessful()) {
                                        // error message will be displayed depending on the kind of the error
                                        Toast.makeText(login.this, "فشل المصادقة" +task.getException(), Toast.LENGTH_LONG).show();

                                } else {
                                    AddNewUser(task.getResult().getUser().getEmail()
                                            ,task.getResult().getUser().getProviderId()
                                            ,task.getResult().getUser().getUid());
                                }
                            }
                        });
            }else {
                     Toast.makeText(login.this, "تحقق من اتصال الانترنت ", Toast.LENGTH_LONG).show();
                 }
                }
            }
        });

        forgetPass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(login.this, resetPassword.class));
            }
        });
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

    //------------------------------------------------------------------ add new user ----------------------------------------

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
        Intent intent = new Intent(login.this, Home.class);
        startActivity(intent);
        finish();

    }


}
