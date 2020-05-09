package com.example.ehtewa.chat;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.ehtewa.R;
import com.example.ehtewa.itemData.itemData_get_all_person;
import android.widget.Filter;
import android.widget.Filterable;
import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

public class RecyclerAdapter_all_users extends RecyclerView.Adapter<RecyclerAdapter_all_users.RecyclerviewHolder>{


    private ArrayList<itemData_get_all_person> itemDatas;

    public static int positions_of_item ;
    public Context context;
    View view ;
    private int lastPosition = -1;
    String account_type;

    public RecyclerAdapter_all_users(Context context, ArrayList<itemData_get_all_person> item_Data)
    {
        this.context       = context;
        this.itemDatas     = item_Data;
    }

    @Override
    public RecyclerviewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        view = LayoutInflater.from(parent.getContext()).inflate(R.layout.r_row_all_person,parent,false);
        RecyclerviewHolder recyclerviewHolder = new RecyclerviewHolder(view);

        return recyclerviewHolder;
    }
    @Override
    public void onBindViewHolder(final RecyclerviewHolder holder, final int position)
    {
        holder.txt_name.setText(itemDatas.get(position).Get_Fld3());
        holder.txt_description.setText(itemDatas.get(position).Get_Fld4());



         account_type = Utility.getInstance().getDataByKey(context,"account_type","");


        holder.constraint_r_general.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                if(account_type.equals("user"))
                {
                    positions_of_item = position;
                    Intent intent = new Intent(context, chat_between_two_user.class);
                    intent.putExtra("username",itemDatas.get(position).Get_Fld8());
                    context.startActivity(intent);
                }
                else
                {
                    positions_of_item = position;
                    Intent intent = new Intent(context, chat_between_two_user.class);
                    intent.putExtra("username",itemDatas.get(position).Get_Fld8());
                    context.startActivity(intent);
                }
            }
        });
    }

    @Override
    public void onViewAttachedToWindow(@NonNull RecyclerAdapter_all_users.RecyclerviewHolder holder) {
        super.onViewAttachedToWindow(holder);
    }

    public int getItemCount()
    {
        return itemDatas.size();
    }



    static class  RecyclerviewHolder extends RecyclerView.ViewHolder
    {

        TextView txt_name, txt_description;
        ConstraintLayout constraint_r_general ;
        public RecyclerviewHolder(View view)
        {
            super(view);

            constraint_r_general  = view.findViewById(R.id.constraint_r_general);
            txt_name              = view.findViewById(R.id.txt_name);
            txt_description       = view.findViewById(R.id.txt_description);


        }
    }



}
