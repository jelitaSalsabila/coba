package com.example.user1.myapplication.QuestionHeader;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Toast;

import com.example.user1.myapplication.Database.DatabaseProvider;
import com.example.user1.myapplication.Model.MainGroupResponse;
import com.example.user1.myapplication.Network.SurveyHelper;
import com.example.user1.myapplication.R;
import com.weiwangcn.betterspinner.library.material.MaterialBetterSpinner;

public class NextActivity extends AppCompatActivity {

    private static final String TAG = DatabaseProvider.class.getSimpleName();
    ProgressDialog progress;
    Button btnstart;
    String period;
    private static final String[] periodList = new String[]{"1", "2", "3"};
    private Bundle extras;
    private DatabaseProvider db;
    private int position = 0;
    private MainGroupResponse mgResponses;
    private SurveyHelper helper;
    String password="";
    private SharedPreferences sharedPreferences;
    MaterialBetterSpinner spinner;

    @Override
    public boolean onSupportNavigateUp(){
        finish();
        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_next);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        helper = SurveyHelper.getInstance(this);
        db = DatabaseProvider.getInstance();
        extras = getIntent().getExtras();

        sharedPreferences = getSharedPreferences("pref_user", Context.MODE_PRIVATE);
        password = sharedPreferences.getString("user_password","");

        if (extras != null) {
            mgResponses = extras.getParcelable("extra_maingroup");
            position = extras.getInt("extra_position");
        }

        Log.e(TAG, "onCreate: " + periodList.length);
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_dropdown_item_1line, periodList);
        spinner = findViewById(R.id.android_material_design_spinner);
        spinner.setAdapter(arrayAdapter);
        spinner.setOnItemClickListener((parent, view, position, id) -> period = parent.getItemAtPosition(position).toString());
        btnstart = (Button) findViewById(R.id.btngo);
        btnstart.setOnClickListener(v -> {
            if (period==null || period.isEmpty() || period.equalsIgnoreCase(null) || period.equalsIgnoreCase("")){
                Toast.makeText(getApplicationContext(), "pilih period ", Toast.LENGTH_SHORT).show();

            }else {

                if (db.isQuestionsEmpty(period)) {
                    Toast.makeText(getApplicationContext(), "fetching question " + period, Toast.LENGTH_SHORT).show();
                    helper.getAllQuestions(password, period, mgResponses, position);
                } else {
                    Toast.makeText(getApplicationContext(), "data sudah ada", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(NextActivity.this, QuestionHeaderActivity.class);
                    Log.e(TAG, "Period: " + period);
                    intent.putExtra("extra_period", period);
                    intent.putExtra("extra_maingroup", mgResponses);
                    intent.putExtra("extra_position", position);
                    startActivity(intent);
                }
            }
        });


    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        helper.closeSource();
    }
}
