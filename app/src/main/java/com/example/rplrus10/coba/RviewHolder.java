package com.example.rplrus10.coba;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

public class RviewHolder extends RecyclerView.ViewHolder {
    public TextView tv_form_field;
    public RadioGroup radioGroup;
    public RadioButton radio_yes,radio_no;

    public RviewHolder(View layoutView) {
        super(layoutView);

        tv_form_field = (TextView)itemView.findViewById(R.id.tv_form_field);
        radioGroup = itemView.findViewById(R.id.radioGroup);
        radio_yes = itemView.findViewById(R.id.radio_yes);
        radio_no = itemView.findViewById(R.id.radio_no);
    }
}
