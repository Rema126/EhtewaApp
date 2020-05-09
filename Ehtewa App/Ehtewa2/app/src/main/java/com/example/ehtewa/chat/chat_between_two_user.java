package com.example.ehtewa.chat;


import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import com.example.ehtewa.R;
import com.firebase.client.ChildEventListener;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import java.util.HashMap;
import java.util.Map;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;


public class chat_between_two_user extends AppCompatActivity {


    LinearLayout layout;
    RelativeLayout layout_2;
    ImageView sendButton;
    EditText messageArea;
    ScrollView scrollView;
    Firebase reference1, reference2;


    public static final String FireBase_Root_Url_Include = "https://ehtewa-20369.firebaseio.com/";


    String  chat_with , Uid ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.f_chat_between_two_user);
        Toolbar toolbar = findViewById(R.id.tool);
        setSupportActionBar(toolbar);
        layout = findViewById(R.id.layout1);
        layout_2 = findViewById(R.id.layout2);
        sendButton = findViewById(R.id.sendButton);
        messageArea = findViewById(R.id.messageArea);
        scrollView  = findViewById(R.id.scrollView);



        Intent getIntent = getIntent();
        if (getIntent != null)
        {
            chat_with = getIntent.getStringExtra("username");

            Uid = Utility.getInstance().getDataByKey(getApplicationContext(),"Uid","rema");

            Log.e("chat_with",String.valueOf(chat_with));
            Log.e("Uid",String.valueOf(Uid));

            Firebase.setAndroidContext(getApplicationContext());
            reference1 = new Firebase(FireBase_Root_Url_Include+"messages/" + Uid + "_" + chat_with);
            reference2 = new Firebase(FireBase_Root_Url_Include+"messages/" + chat_with + "_" + Uid);
        }
        else
        {

        }


        sendButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                String messageText = messageArea.getText().toString();  // (Waiting) Here We Will Check The Message Before Send To The Other User

                if(!messageText.equals(""))
                {
                    Map<String, String> map = new HashMap<String, String>();
                    map.put("message", messageText);
                    map.put("Uid", Uid);
                    reference1.push().setValue(map);
                    reference2.push().setValue(map);
                    messageArea.setText("");
                }
            }
        });


        reference1.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                Map map = dataSnapshot.getValue(Map.class);
                String message = map.get("message").toString();
                String uid = map.get("Uid").toString();

                if(uid.equals(Uid))
                {
                    AddMessageBox( message, 1);
                }
                else
                {

                    AddMessageBox( message, 2);
                }
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });


    }
    public void AddMessageBox(String message, int type)
    {
        TextView textView = new TextView(getApplicationContext());
        textView.setText(message);

        LinearLayout.LayoutParams lp2 = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        lp2.weight = 1.0f;

        if(type == 1)
        {
            lp2.gravity = Gravity.RIGHT;
            textView.setBackgroundResource(R.drawable.bubble_in);
            textView.setTextColor(Color.BLACK);
        }
        else
        {
            lp2.gravity = Gravity.LEFT;
            textView.setBackgroundResource(R.drawable.bubble_out);
            textView.setTextColor(Color.BLACK);
        }
        textView.setLayoutParams(lp2);
        layout.addView(textView);

        scrollView.fullScroll(View.FOCUS_DOWN);
        scrollView.post(new Runnable() {
            @Override
            public void run() {
                scrollView.fullScroll(View.FOCUS_DOWN);
            }
        });
    }



}
