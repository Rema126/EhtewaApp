package com.example.ehtewa.ui.Consultants;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ehtewa.FirebaseViewHolder;
import com.example.ehtewa.R;
import com.example.ehtewa.counselors;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class ConsultantsFragment extends Fragment {

    private ConsultantsViewModel consultantsViewModel;

    private RecyclerView recyclerView;
    private FirebaseRecyclerAdapter<counselors, FirebaseViewHolder> adapter;
    private ArrayList<counselors> arrayList;
    private FirebaseRecyclerOptions<counselors> options;
    private DatabaseReference databaseReference;


    @Override
    public void onStart() {
        super.onStart();
        adapter.startListening();

    }

    @Override
    public void onStop() {
        super.onStop();
        adapter.stopListening();
    }


    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        consultantsViewModel = ViewModelProviders.of(this).get(ConsultantsViewModel.class);
        View root = inflater.inflate(R.layout.fragment_consultants, container, false);
        final TextView textView = root.findViewById(R.id.text_Consultants);
        consultantsViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });

        recyclerView = root.findViewById(R.id.recView);
        recyclerView.setAdapter(adapter);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));


        arrayList = new ArrayList<counselors>();
        databaseReference = FirebaseDatabase.getInstance().getReference().child("Counselors");
        databaseReference.keepSynced(true);
        options = new FirebaseRecyclerOptions.Builder<counselors>().setQuery(databaseReference,counselors.class).build();


        adapter = new FirebaseRecyclerAdapter<counselors, FirebaseViewHolder>(options) {
            @Override
            protected void onBindViewHolder(@NonNull final FirebaseViewHolder firebaseViewHolder, int i, @NonNull final counselors datasetFire) {

                firebaseViewHolder.Specialisations.setText(datasetFire.getSpecialisations());
                firebaseViewHolder.Name.setText(datasetFire.getName());
              /*  firebaseViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(getContext(), chat_between_two_user.class);
                        intent.putExtra("Specialisations",datasetFire.getSpecialisations());
                        intent.putExtra("Name",datasetFire.getName());
                        startActivity(intent);

                    }
                });*/
            }

            @NonNull
            @Override
            public FirebaseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                return new FirebaseViewHolder(LayoutInflater.from(getContext()).inflate(R.layout.my_cons,parent,false));
            }
        };



        recyclerView.setAdapter(adapter);




        return root;
    }
}

