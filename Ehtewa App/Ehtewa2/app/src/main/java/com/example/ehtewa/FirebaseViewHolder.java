package com.example.ehtewa;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class FirebaseViewHolder extends RecyclerView.ViewHolder {

    public TextView Specialisations,Name;

    public FirebaseViewHolder(@NonNull View itemView) {
        super(itemView);
        Specialisations = itemView.findViewById(R.id.spec1);
        Name = itemView.findViewById(R.id.name1);

    }
}
