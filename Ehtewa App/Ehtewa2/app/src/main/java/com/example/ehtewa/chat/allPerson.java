package com.example.ehtewa.chat;

import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.example.ehtewa.R;
import com.example.ehtewa.itemData.itemData_get_all_person;
import com.example.ehtewa.ui.home.HomeFragment;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class allPerson extends Fragment {


    View rootView ;
    String account_type;

    DatabaseReference databaseReference ;

    private RecyclerView recycle ;
    private RecyclerAdapter_all_users adapter ;
    private RecyclerView.LayoutManager layoutManager ;
    ArrayList<itemData_get_all_person> itemData = new ArrayList<itemData_get_all_person>();



    boolean AdapterIsEmpty = false ;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.f_all_person, container, false);


        recycle = rootView.findViewById(R.id.recycle);


        account_type = Utility.getInstance().getDataByKey(getActivity(),"account_type","");

        if(account_type.equals("user"))
        {
            get_all_consult();
        }
        else
        {
            get_all_user();
        }

        return rootView;
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
                    HomeFragment fragment = new HomeFragment();
                    FragmentManager fm = getFragmentManager();
                    fm.beginTransaction()
                    .replace(R.id.nav_host_fragment, fragment ,fragment.getTag()).addToBackStack(null).commit();
                    return true;
                }
                return false;
            }
        });


    }

    public void get_all_user()
    {
        databaseReference = FirebaseDatabase.getInstance().getReference("Users");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot)
            {
                Log.e("dataSnapshot",String.valueOf(dataSnapshot));
                itemData.clear();
                recycle.setAdapter(null);


                String email = Utility.getInstance().getDataByKey(getActivity(),"email","");

                for(DataSnapshot snapshot : dataSnapshot.getChildren())
                {
                    String fld0    = snapshot.child("userEmail").getValue(String.class);
                    String fld1    = snapshot.child("userId").getValue(String.class);
                    String fld2    = snapshot.child("userProvider").getValue(String.class);

                    Log.e("fld0",String.valueOf(fld0));
                    Log.e("fld1",String.valueOf(fld1));
                    Log.e("fld2",String.valueOf(fld2));

                    if(fld0.equals(email))
                    {

                    }
                    else
                    {
                        itemData.add(new itemData_get_all_person(
                                fld0,
                                fld1,
                                fld2,
                                fld0,
                                "",
                                "",
                                "",
                                "",
                                fld1
                        ));
                    }

                }


                adapter = new RecyclerAdapter_all_users(getActivity(), itemData);
                layoutManager = new LinearLayoutManager(getActivity());
                recycle.setLayoutManager(layoutManager);
                recycle.setHasFixedSize(true);
                recycle.setAdapter(adapter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError)
            {

            }
        });
    }

    public void get_all_consult()
    {


        itemData.add(new itemData_get_all_person(

                "consult4@gmail.com",
                "consult4",
                "consult4",
                "د.أيمن اليحيى",
                "الإستشارات المتعلقة بذوي الموهبة و التفوق",
                "",
                "",
                "",
                "consult4"

        ));

        itemData.add(new itemData_get_all_person(
                "consult3@gmail.com",
                "consult3",
                "consult3",
                "أ.د.عبدالله السبيعي",
                "استاذ دكتور في الطب النفسي",
                "",
                "",
                "",
                "consult3"

        ));


        itemData.add(new itemData_get_all_person(


                "consult1@gmail.com",
                "consult1",
                "consult1",
                "أ.صابرين الجدعاني",
                "الإستشارات المتعلقة بذوي الاحتياجات الخاصه",
                "",
                "",
                "",
                "consult1"
        ));


        itemData.add(new itemData_get_all_person(
                "consult4@gmail.com",
                "consult4",
                "consult4",
                "أ.رانيا الضاوي",
                "الإستشارات المتعلقة بسلوك الطفل والفروقات الفردية",
                "",
                "",
                "",
                "consult3"
        ));


        itemData.add(new itemData_get_all_person(

                "consult2@gmail.com",
                "consult2",
                "consult2",
                "أ.أسماء الشليل",
                "الإستشارات المتعلقة بذوي الاعاقة",
                "",
                "",
                "",
                "consult2"
        ));


        adapter = new RecyclerAdapter_all_users(getActivity(), itemData);
        layoutManager = new LinearLayoutManager(getActivity());
        recycle.setLayoutManager(layoutManager);
        recycle.setHasFixedSize(true);
        recycle.setAdapter(adapter);
    }
}
