package com.example.mybaseadapterlistview;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class RegisterActivity extends AppCompatActivity{
    Button back, register;
    EditText name, last, user, pass, phone, comments;
    Context activity = this;
    String URL = "";//put your register_user.php here
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        name = findViewById(R.id.name);
        last = findViewById(R.id.last);
        user = findViewById(R.id.user);
        pass = findViewById(R.id.pass);
        phone = findViewById(R.id.phone);
        comments = findViewById(R.id.comments);
        back = findViewById(R.id.back);
        register = findViewById(R.id.register);
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                register();
            }
        });
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(RegisterActivity.this, MainActivity.class));
            }
        });
    }
    private void register(){
        final String nameReg = name.getText().toString();
        final String lastReg = last.getText().toString();
        final String userReg = user.getText().toString();
        final String passReg = pass.getText().toString();
        final String phoneReg = phone.getText().toString();
        final String commentsReg = comments.getText().toString();
        StringRequest request = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if(response.contains("Success")){
                    startActivity(new Intent(RegisterActivity.this,MainActivity.class));
                    Toast.makeText(activity, "You are registered now", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(activity, "Error", Toast.LENGTH_SHORT).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String>params = new HashMap<String, String>();
                params.put("name", nameReg);
                params.put("last", lastReg);
                params.put("user", userReg);
                params.put("pass", passReg);
                params.put("phone", phoneReg);
                params.put("comments", commentsReg);
                return params;
            }
        };
        Volley.newRequestQueue(activity).add(request);
    }
}