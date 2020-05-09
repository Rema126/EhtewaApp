package com.example.ehtewa.ui.home;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import com.example.ehtewa.AboutUs;
import com.example.ehtewa.MainActivity;
import com.example.ehtewa.R;
import com.example.ehtewa.Ratting;
import com.example.ehtewa.chat.Utility;
import com.example.ehtewa.chat.allPerson;
import com.example.ehtewa.recommendation;
import com.google.firebase.auth.FirebaseAuth;


public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;
    private TextView recom ,conv , aboutUs , ratting;
    private Button signout;
    String account_type;
    private FirebaseAuth auth;
    private static final  String TAG = "State";

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        homeViewModel = ViewModelProviders.of(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);
        final TextView textView = root.findViewById(R.id.text_home);

        auth = FirebaseAuth.getInstance();
       recom = root.findViewById(R.id.textView3);
       conv = root.findViewById(R.id.textView2);
       aboutUs = root.findViewById(R.id.aboutUs);
       ratting = root.findViewById(R.id.ratting);
       signout=root.findViewById(R.id.button2);

        account_type = Utility.getInstance().getDataByKey(getActivity(),"account_type","");
     if (account_type.equals(("consult")))
        {
            conv.setText(" الرسائل ");
            conv.setTextSize(40);
            recom.setVisibility(View.GONE);
            aboutUs.setVisibility(View.GONE);
            ratting.setVisibility(View.GONE);

        }
     else {

             conv.setText("تحدث مع مستشارك");
             signout.setVisibility(View.GONE);

     }

        signout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                auth.signOut();
                Intent i =new Intent(getContext(), MainActivity.class);
                Toast.makeText(getContext(), "تم تسجيل الخروج بنجاح", Toast.LENGTH_SHORT).show();
                Log.d(TAG, "addAuthStateChanged : sign_out");
                i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(i);
            }
        });
        conv.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                if(account_type.equals("user"))
                {
                    allPerson fragment = new allPerson();
                    FragmentManager fm = getFragmentManager();
                    fm.beginTransaction()
                    .replace(R.id.nav_host_fragment, fragment,fragment.getTag()).addToBackStack(null).commit();
                }
                else
                {
                    allPerson fragment = new allPerson();
                    FragmentManager fm = getFragmentManager();
                    fm.beginTransaction()
                            .replace(R.id.nav_host_fragment, fragment,fragment.getTag()).addToBackStack(null).commit();
                }
            }
        });



        homeViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });

      /*  conv = (TextView) root.findViewById(R.id.textView2);
        conv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ConsultantsFragment consultantsFragment = new ConsultantsFragment();
                FragmentManager f1 = getFragmentManager();
                f1.beginTransaction()
                        .replace(R.id.nav_host_fragment, consultantsFragment, consultantsFragment.getTag()).addToBackStack(null).commit();
            }
        });*/


        recom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getContext(), recommendation.class);
                startActivity(i);
            }

        });

        aboutUs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getContext(), AboutUs.class);
                startActivity(i);
            }
        });

        ratting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getContext(), Ratting.class);
                startActivity(i);
            }
        });
        return root;
    }

    @Override
    public void onResume()
    {
        super.onResume();
        getView().setFocusableInTouchMode(true);
        getView().requestFocus();
        getView().setOnKeyListener(new View.OnKeyListener()
        {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event)
            {

                if (event.getAction() == KeyEvent.ACTION_UP && keyCode == KeyEvent.KEYCODE_BACK)
                {
                    getActivity().finish();
                    return true;
                }
                return false;
            }
        });


    }

}