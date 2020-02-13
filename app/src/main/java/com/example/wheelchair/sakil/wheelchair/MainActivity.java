package com.example.wheelchair.sakil.wheelchair;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.io.Console;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {


    ImageButton upBtn, downBtn, rightBtn, leftBtn, powerBtn;
    RequestQueue requestQueue;
    int count = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()){

            case R.id.upBtn:
                makerequest("up");
                System.out.println("Pressed");
                break;
            case R.id.downBtn:
                makerequest("down");
                break;
            case R.id.rightBtn:
                makerequest("right");
                break;
            case R.id.leftBtn:
                makerequest("left");
                break;
            case R.id.powerBtn:
                if(count % 2 == 0) {
                    makerequest("start");
                }
                else{
                    makerequest("stop");
                }
                count = count + 1;
                break;

        }

    }

    public void makerequest(final String direction){

        String url = "https://iot-wheelchair-268006.uc.r.appspot.com/direction/";

        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        Log.d("Response : ", response);

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                error.printStackTrace();

            }
        }){

            @Override
            protected Map<String, String> getParams() throws AuthFailureError {

                Map<String, String> params = new HashMap<String, String>();
                params.put("direction", direction);
                return params;

            }
        };

        requestQueue.add(stringRequest);

    }

    public void init(){

        upBtn = findViewById(R.id.upBtn);
        downBtn = findViewById(R.id.downBtn);
        rightBtn = findViewById(R.id.rightBtn);
        leftBtn = findViewById(R.id.leftBtn);
        powerBtn = findViewById(R.id.powerBtn);

        upBtn.setOnClickListener(this);
        downBtn.setOnClickListener(this);
        rightBtn.setOnClickListener(this);
        leftBtn.setOnClickListener(this);
        powerBtn.setOnClickListener(this);

        requestQueue = Volley.newRequestQueue(this);

    }
}