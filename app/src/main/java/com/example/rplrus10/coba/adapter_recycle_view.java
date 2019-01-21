package com.example.rplrus10.coba;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

public class adapter_recycle_view extends RecyclerView.Adapter<RviewHolder> {

    private ArrayList<question> questionArrayList ;
    Context context;
    private String[] answers;

    public adapter_recycle_view(Context context, ArrayList<question> questionArrayList){
        this.context = context;
        this.questionArrayList = questionArrayList;
    }

    @NonNull
    @Override
    public RviewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View layoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_item,parent,false);
        RviewHolder rcv = new RviewHolder(layoutView);
        return rcv;
    }

    @Override
    public void onBindViewHolder(final RviewHolder holder,final int position) {
        final question question = questionArrayList.get(position);
        holder.tv_form_field.setText(questionArrayList.get(position).getQuestion());
    }
    @Override
    public int getItemCount() {
        return questionArrayList.size();
    }
    // tempat menaruh dan mensetting recycle view
    // layout nya namanya row_item.xml
}
