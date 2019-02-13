package com.example.rplrus10.coba;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

public class Survey_list extends AppCompatActivity {

    ArrayList<question> questionArrayList;
    RecyclerView rView;
    Button send_btn;
    survey_Adapter survey_adapter;
    ArrayList<String>answerArraylist;
    survey_Adapter.MyHolder myHolder;
    TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_survey_list);
        rView = (RecyclerView) findViewById(R.id.rv);
        send_btn = (Button) findViewById(R.id.send_btn);
        answerArraylist = new ArrayList<>();
        survey_adapter = new survey_Adapter(questionArrayList, this);

//        for (int i = 0; i < questionArrayList.size(); i++){
//            if(questionArrayList.get(i).isSelected()) {
//                tv.setText(tv.getText() + " " + questionArrayList.get(i).getQuestion());
//            }
//        }
    }
}
