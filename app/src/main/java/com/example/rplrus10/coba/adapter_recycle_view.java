package com.example.rplrus10.coba;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class adapter_recycle_view extends RecyclerView.Adapter<adapter_recycle_view.Holder> {

    private ArrayList<question> questionArrayList;
    Context context;
    private RadioButton lastChecked = null;
    private String answer;
    private int lastSelectedPosition = -1;

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
    public void onBindViewHolder(final adapter_recycle_view.Holder holder, final int position) {
        final question question = questionArrayList.get(position);
        holder.tv_form_field.setText(question.getQuestion());
        holder.radio.setTag(position);
        holder.radio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Integer pos = (Integer) holder.radio.getTag();
                Toast.makeText(context, questionArrayList.get(pos).getQuestion() + " clicked!", Toast.LENGTH_SHORT).show();
                if (questionArrayList.get(pos).isSelected()) {
                    questionArrayList.get(pos).setSelected(false);
                } else {
                    questionArrayList.get(pos).setSelected(true);
                }
            }
        });

        holder.rbYes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (questionArrayList.size() == 0) {
                    holder.bagian.setVisibility(View.GONE);
                } else {
                    holder.bagian.setVisibility(View.VISIBLE);
                    answer = holder.bagian.getText().toString();
                    question.setAnswers(answer);
                }
            }
        });
        holder.rbNo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (questionArrayList.size() == 0) {

                } else {
                    holder.bagian.setVisibility(View.GONE);
                }
            }
        });
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

//    public  boolean isAllFieldAnswered() {
//        for (String answer : answers) {
//            if (answer == null) {
//                return false;
//            }
//        }
//        return true;
//    }

    public class Holder extends RecyclerView.ViewHolder {
        public TextView tv_form_field;
        public RadioGroup radio;
        public RadioButton rbYes, rbNo;
        public EditText bagian;

        public Holder(View itemView) {
            super(itemView);
            tv_form_field = (TextView)itemView.findViewById(R.id.tv_form_field);
            radio = itemView.findViewById(R.id.radio);
            rbYes = itemView.findViewById(R.id.rb_yes);
            rbNo = itemView.findViewById(R.id.rb_no);
            bagian = itemView.findViewById(R.id.ed_bagian);
        }
        }
    }