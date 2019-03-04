package com.example.rplrus10.coba;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class ListSurvey extends AppCompatActivity {

    TextView tv_no1, tv_question, tv_answer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_survey);

        tv_no1 = findViewById(R.id.tv_no1);
        tv_answer = findViewById(R.id.tv_answer1);
        tv_question = findViewById(R.id.tv_question1);


    }
}
