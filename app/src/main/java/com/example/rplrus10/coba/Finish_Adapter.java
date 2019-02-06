package com.example.rplrus10.coba;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class Finish_Adapter extends RecyclerView.Adapter<Finish_Adapter.MyHolder> {

    private ArrayList<question> questionArrayList;
    private Context context;

    public Finish_Adapter(ArrayList<question>questionArrayList, Context context){
        this.questionArrayList = questionArrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public Finish_Adapter.MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.finish_item, parent, false);
        Finish_Adapter.MyHolder rcv = new Finish_Adapter.MyHolder(view);
        return rcv;
    }

    @Override
    public void onBindViewHolder(@NonNull Finish_Adapter.MyHolder myHolder, int i) {
        question question = questionArrayList.get(i);
        myHolder.updateUI(question, i + 1);
    }

    @Override
    public int getItemCount() {
        return questionArrayList.size();
    }

    public class MyHolder extends RecyclerView.ViewHolder {

        TextView tvQuestion;
        TextView tvAnswer;
        TextView tvAnswer1;

        public MyHolder(@NonNull View itemView) {
            super(itemView);

            tvQuestion = itemView.findViewById(R.id.tv_question);
            tvAnswer = itemView.findViewById(R.id.tv_answer);
            tvAnswer1 = itemView.findViewById(R.id.tv_bagian);
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
            tvAnswer1.setText(answers.toString());
        }
    }
}
