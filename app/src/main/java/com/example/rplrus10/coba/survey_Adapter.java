package com.example.rplrus10.coba;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class survey_Adapter extends RecyclerView.Adapter<survey_Adapter.MyHolder> {

    private ArrayList<question> questionArrayList;
    Context context;

    public survey_Adapter(ArrayList<question>questionArrayList, Context context){
        this.questionArrayList = questionArrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public survey_Adapter.MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.finish_item, parent, false);
        survey_Adapter.MyHolder rcv = new survey_Adapter.MyHolder(view);
        return rcv;
    }

    @Override
    public void onBindViewHolder(@NonNull survey_Adapter.MyHolder myHolder, int i) {
        final question question = questionArrayList.get(i);
        //myHolder.updateUI(question, i + 1);
        myHolder.tvQuestion.setText(question.getQuestion());
        Log.d("tanya", "onBindViewHolder: " + question.getQuestion());
        myHolder.tvNo.setText(question.getId_question());
        Log.d("chek", "onBindViewHolder: " + question.getId_question());
    }
    @Override
    public int getItemCount() {
        return questionArrayList.size();
    }

    public class MyHolder extends RecyclerView.ViewHolder {

        TextView tvQuestion;
        TextView tvAnswer;
        TextView tvNo;

        public MyHolder(@NonNull View itemView) {
            super(itemView);
            tvNo = itemView.findViewById(R.id.tv_no);
            tvQuestion = itemView.findViewById(R.id.tv_question);
            tvAnswer = itemView.findViewById(R.id.tv_answer);
        }
        void updateUI(question quest, int position) {
            tvQuestion.setText(quest.getQuestion());
            StringBuilder answers = new StringBuilder();
            for (int i = 0; i < quest.getAnswer().size(); i++){
                String answer = quest.getAnswer().get(i);
                if (i == quest.getAnswer().size() - 1)
                    answers.append(answer);
                else
                    answers.append(answer+ ", ");
            }
            tvAnswer.setText(answers.toString());
        }
    }
}
