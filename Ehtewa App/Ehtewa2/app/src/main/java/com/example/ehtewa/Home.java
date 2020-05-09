package com.example.ehtewa;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.ehtewa.chat.Utility;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


public class Home extends AppCompatActivity {
    private FirebaseAuth auth;
    private FirebaseUser user;
    private static final  String TAG = "ToolFragment";
    private AppBarConfiguration mAppBarConfiguration;
    String account_type;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        auth = FirebaseAuth.getInstance();
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);

        account_type = Utility.getInstance().getDataByKey(getApplicationContext(), "account_type", "");
        if (account_type.equals("user")) {
            mAppBarConfiguration = new AppBarConfiguration.Builder(
                    R.id.nav_home, R.id.nav_Consultants,
                    R.id.nav_tools)
                    .setDrawerLayout(drawer)
                    .build();
        }
        else {
            mAppBarConfiguration = new AppBarConfiguration.Builder(R.id.nav_home)
                    .build();

        }
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.home, menu);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }


    public void changepassword (View v){
        Intent i = new Intent(getApplicationContext(), changePassword.class);
        startActivity(i);
        finish();
    }
    public void deleteAccount(View v ){
        user = auth.getCurrentUser();
        if (user != null) {
            AlertDialog.Builder builder = new AlertDialog.Builder(Home.this);
            builder.setMessage("هل انت متأكد ؟");
            builder.setCancelable(true);
            builder.setNegativeButton("نعم", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    user.delete()
                            .addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if (task.isSuccessful()) {
                                        Toast.makeText(Home.this, "تم حذف حسابك بنجاح !", Toast.LENGTH_SHORT).show();
                                        Intent i = new Intent(getApplicationContext(), MainActivity.class);
                                        startActivity(i);
                                        finish();
                                    } else {
                                        Toast.makeText(Home.this, "فشل حذف حسابك !", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                }
            });
            builder.setPositiveButton("لا", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    dialogInterface.cancel();
                }
            });
            AlertDialog alertDialog = builder.create();
            alertDialog.show();

        }
    }

    public void signOut(View v){
        auth.signOut();
        Intent i =new Intent(Home.this, MainActivity.class);
        Toast.makeText(Home.this, "تم تسجيل الخروج بنجاح", Toast.LENGTH_SHORT).show();
        Log.d(TAG, "addAuthStateChanged : sign_out");
        i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(i);

    }
}
