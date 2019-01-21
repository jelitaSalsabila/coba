package com.example.rplrus10.coba;

import android.annotation.SuppressLint;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

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
    RecyclerView recyclerView;
    JSONArray Hasiljson;
    question question;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tampilan);
        question = new question();
        recyclerView = findViewById(R.id.recycler);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(Tampilan.this));
        new load_data().execute();
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
            } catch (Exception e) {
                jsonObject = null;
            }
            return jsonObject;
        }

        @Override
        protected void onPostExecute(JSONObject jsonObject) {
            Log.d("hasil json ", "onPostExecute: " + jsonObject.toString());
            try {
                questionArrayList = new ArrayList<>();
                Hasiljson = jsonObject.getJSONArray("hasil");
                for (int i = 0; i < Hasiljson.length(); i++) {
                    question s = new question();
                    s.setId_question(Hasiljson.getJSONObject(i).getString("id_question"));
                    s.setQuestion(Hasiljson.getJSONObject(i).getString("question"));

                    questionArrayList.add(s);
                }
                //slide_adapter = new slide_adapter(tampilanPertanyaan.this,questionArrayList);
                adapter = new adapter_recycle_view(Tampilan.this, questionArrayList);
                recyclerView.setAdapter(adapter);

            } catch (JSONException e1) {
                e1.printStackTrace();
            }
        }
    }
}
