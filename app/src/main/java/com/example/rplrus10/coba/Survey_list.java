package com.example.rplrus10.coba;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Button;

import java.util.ArrayList;

public class Survey_list extends AppCompatActivity implements onCheckedListener {

    ArrayList<question> questionArrayList;
    RecyclerView rView;
    Button send_btn;
    survey_Adapter survey_adapter;
    ArrayList<String>answerArraylist;
    survey_Adapter.MyHolder myHolder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_survey_list);
        rView = findViewById(R.id.rv);
        send_btn = findViewById(R.id.send_btn);


        answerArraylist = new ArrayList<>();
        rView.setHasFixedSize(true);
        rView.setLayoutManager(new LinearLayoutManager(Survey_list.this));
        survey_adapter = new survey_Adapter(this.questionArrayList,getApplicationContext());
        rView.setAdapter(survey_adapter);
        survey_adapter.setListener(Survey_list.this);

//        for (int i = 0; i < questionArrayList.size(); i++){
//            if(questionArrayList.get(i).isSelected()) {
//                tv.setText(tv.getText() + " " + questionArrayList.get(i).getQuestion());
//            }
//        }
    }

    @Override
    public void onChecked(int position, String answer) {
        Log.d("isinya", "onChecked: " + position + " " + answer);
    }
}
