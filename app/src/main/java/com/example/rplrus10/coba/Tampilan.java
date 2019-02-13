package com.example.rplrus10.coba;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.nfc.Tag;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Tampilan extends AppCompatActivity {

    // menampilkan recycleview dan asnyctask
    ArrayList<question> questionArrayList;
    adapter_recycle_view adapter;
    adapter_recycle_view.Holder holder;
    RecyclerView recyclerView;
    JSONArray Hasiljson;
    question question;
    ArrayList<String>answerArraylist;
    Button start_btn;
    String selectType;
    View view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tampilan);
        question = new question();
        answerArraylist = new ArrayList<>();
        start_btn = (Button) findViewById(R.id.start_btn);
        questionArrayList = populateList();
        recyclerView = findViewById(R.id.recycler);
        new load_data().execute();
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
//        holder.rbYes.setChecked(true);
//        selectType = holder.rbYes.getText().toString();

        start_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                int selectedId = holder.radio.getCheckedRadioButtonId();
//                holder.rbYes = findViewById(selectedId);

//                if (holder.rbYes.isChecked() || holder.rbNo.isChecked()) {
                if (holder.radio.getCheckedRadioButtonId() == -1)
                {
                    // no radio buttons are checked
                }
                else
                {
                    // one of the radio buttons is checked
                }
                    Intent intent = new Intent(Tampilan.this, Survey_list.class);
                    //intent.putExtra("REST2", selectType);
                    startActivity(intent);
//                    Toast.makeText(getApplicationContext(), selectType, Toast.LENGTH_SHORT).show();
//                } else {
//                    Toast.makeText(getApplicationContext(), "Please choose 1 answer", Toast.LENGTH_SHORT).show();
//                }
//                int i = holder.radio.getCheckedRadioButtonId();
//                RadioButton rb =(RadioButton)holder.radio.findViewById(i);
//                Log.d("pilihanku", "onClick: "+rb);
//                final String bagian = holder.bagian.getText().toString();
//                Toast.makeText(getApplicationContext(), "bagian" + bagian +"pilih" + rb.getText(), Toast.LENGTH_SHORT).show();
                //startActivity(new Intent(getApplicationContext(),Survey_list.class));
            }
        });
    }

    public  void onRadioButtonClicked(View v){
        int radioId = holder.radio.getCheckedRadioButtonId();
        holder.rbYes = findViewById(radioId);
        Toast.makeText(this, " Select Radio Button : " + holder.rbYes.getText(), Toast.LENGTH_SHORT).show();
    }
    private ArrayList<question> populateList(){

        ArrayList<question> list = new ArrayList<>();

        for(int i = 0; i < list.size(); i++){
            question quest = new question();
            quest.setAnswers(String.valueOf(i));
            list.add(quest);
        }
        return list;
    }
    @SuppressLint("StaticFieldLeak")
    public class load_data extends AsyncTask<Void, Void, JSONObject> {

        @Override
        protected void onPreExecute() {
        }

        @Override
        protected JSONObject doInBackground(Void... params) {
            JSONObject jsonObject;

            try {
                String url = confiq_url.url2 + "question.php";
                System.out.println("url " + url);
                DefaultHttpClient httpClient = new DefaultHttpClient();
                HttpGet httpGet = new HttpGet(url);
                HttpResponse httpResponse = httpClient.execute(httpGet);
                HttpEntity httpEntity = httpResponse.getEntity();
                InputStream inputStream = httpEntity.getContent();
                BufferedReader reader = new BufferedReader(new InputStreamReader(
                        inputStream, "iso-8859-1"
                ), 8);
                StringBuilder stringBuilder = new StringBuilder();
                String line;
                while ((line = reader.readLine()) != null) {
                    stringBuilder.append(line).append("\n");
                }
                inputStream.close();
                String json = stringBuilder.toString();
                jsonObject = new JSONObject(json);
                Log.d("error try", "doInBackground: ");
            } catch (Exception e) {
                jsonObject = null;
                Log.d("error catch", "doInBackground: ");
            }
            return jsonObject;
        }

        @Override
        protected void onPostExecute(JSONObject jsonObject) {
            Log.d("hasil json ", "onPostExecute: " + jsonObject.toString());
            try {
                questionArrayList = new ArrayList<>();
                Hasiljson = jsonObject.getJSONArray("Result");
                for (int i = 0; i < Hasiljson.length(); i++) {
                    question s = new question();
                    s.setId_question(Hasiljson.getJSONObject(i).getString("id_question"));
                    s.setQuestion(Hasiljson.getJSONObject(i).getString("question"));
                    questionArrayList.add(s);
                }
                recyclerView.setHasFixedSize(true);
                adapter = new adapter_recycle_view(Tampilan.this, questionArrayList);
                recyclerView.setAdapter(adapter);
            } catch (JSONException e1) {
                e1.printStackTrace();
            }
        }
    }
}
