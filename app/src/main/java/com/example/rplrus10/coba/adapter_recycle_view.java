package com.example.rplrus10.coba;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class adapter_recycle_view extends RecyclerView.Adapter<adapter_recycle_view.Holder> {

    private ArrayList<question> questionArrayList;
    Context context;
    private String[] answers;
    private RadioGroup lastcheck = null;

    public adapter_recycle_view(Context context, ArrayList<question> questionArrayList) {
        this.context = context;
        this.questionArrayList = questionArrayList;
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        View layoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_item, parent, false);
        Holder rcv = new Holder(layoutView);
        return rcv;
    }

    @Override
    public void onBindViewHolder(final Holder holder, final int position) {
        final question question = questionArrayList.get(position);
        holder.tv_form_field.setText(question.getQuestion());
        int id = (position + 1) * 100;
        for (String answer : question.getAnswer()) {
            RadioButton radioButton = new RadioButton(adapter_recycle_view.this.context);
            radioButton.setId(id++);
            radioButton.setText(answer);
            holder.radioGroup.addView(radioButton);
        }
//        holder.radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
//            @Override
//            public void onCheckedChanged(RadioGroup group, int checkedId) {
//                RadioButton check = (RadioButton)group.findViewById(checkedId);
//                if (radioButton!= null){
//                    radioButton.setChecked(false);
//                    Toast.makeText(context,
//                            "Radio button clicked " + group.getCheckedRadioButtonId(),
//                            Toast.LENGTH_SHORT).show();
//                }
//                radioButton = check;
//            }
//        });
    }

    @Override
    public int getItemCount() {
        return questionArrayList.size();
    }

    public class Holder extends RecyclerView.ViewHolder {
        public TextView tv_form_field;
        public RadioGroup radioGroup;

        public Holder(@NonNull View itemView) {
            super(itemView);
            tv_form_field = (TextView)itemView.findViewById(R.id.tv_form_field);
            radioGroup = itemView.findViewById(R.id.radioGroup);
            radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(RadioGroup group, int checkedId) {
                    if (lastcheck!= null){
                        Toast.makeText(context,"Radio click "+radioGroup.getCheckedRadioButtonId(),Toast.LENGTH_SHORT).show();
                    }
                    lastcheck = group;
                }
            });
        }
    }
    // tempat menaruh dan mensetting recycle view
    // layout nya namanya row_item.xml
}
