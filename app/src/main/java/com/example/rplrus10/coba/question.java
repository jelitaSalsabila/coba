package com.example.rplrus10.coba;

import java.util.ArrayList;

public class question {
    ArrayList<String>answer = new ArrayList<String>();
    private String id_question;
    private String question;
    public String getId_question() {
        return id_question;
    }

    public void setId_question(String id_question) {
        this.id_question = id_question;
    }

    public String getQuestion() {
        return question;
    }

    public ArrayList<String> getAnswer() {
        return answer;
    }

    public void setAnswer(ArrayList<String> answer) {
        this.answer = answer;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

}
