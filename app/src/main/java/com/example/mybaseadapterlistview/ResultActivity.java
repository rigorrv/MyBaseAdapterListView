package com.example.mybaseadapterlistview;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class ResultActivity extends AppCompatActivity{

    List<Subject>subjectList;
    ListAdapter adapter;
    ListView listView;
    Subject subject;
    Context activity = this;
    String URL = "";//put your result.php here
    Button back;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        listView = findViewById(R.id.listView);
        back = findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ResultActivity.this, MainActivity.class));
            }
        });

        JSONParse();
    }
    private void JSONParse(){
        StringRequest request = new StringRequest(Request.Method.GET, URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try{
                    JSONArray jsonArray = new JSONArray(response);
                    JSONObject jsonObject;
                    subjectList = new ArrayList<>();
                    for (int i = 0; i < jsonArray.length(); i++){
                        subject = new Subject();
                        jsonObject = jsonArray.getJSONObject(i);
                        subject.subject_name = jsonObject.getString("name");
                        subject.subject_last = jsonObject.getString("last");
                        subject.subject_phone = jsonObject.getString("phone");
                        subject.subject_comments = jsonObject.getString("comments");
                        subjectList.add(subject);
                        adapter = new ListAdapter(subjectList, activity);
                        listView.setAdapter(adapter);
                    }
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        Volley.newRequestQueue(activity).add(request);
    }
}