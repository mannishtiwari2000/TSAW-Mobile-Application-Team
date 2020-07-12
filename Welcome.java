package com.example.tsaw.javaFile;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.tsaw.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

public class Welcome extends AppCompatActivity {
 //   Dbhelper mydb;
    TextView user_name,happy;

    String JWT;
    int verifed;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        user_name=findViewById(R.id.User_name);
        happy=findViewById(R.id.Happy);
//
        SharedPreferences prefs = getSharedPreferences("Token", MODE_PRIVATE);
        JWT = prefs.getString("Token", null);
//        System.out.println("Token Value"+JWT);
        //Toast.makeText(Welcome.this,JWT,Toast.LENGTH_SHORT).show();
//        mydb=new Dbhelper(this);
//
//        Cursor c = mydb.Getdata();
//        if (c.getCount() == 0) {
//            Toast.makeText(Welcome.this, "Nothing Found", Toast.LENGTH_LONG).show();
//        }
//
//        while (c.moveToNext()) {
//            JWT=c.getColumnName(3);
//        }
        OperatorApi(JWT);

        Button Profile = (Button) findViewById(R.id.profile);
        Profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Welcome.this, profile_form.class);
                startActivity(intent);
            }
        });
        Button proof = (Button) findViewById(R.id.govt);

        proof.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Welcome.this, govt_id.class);
                startActivity(intent);
            }
        });
        Button drone = (Button) findViewById(R.id.drone_d);
        drone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Welcome.this, Drone_detail.class);
                startActivity(intent);
            }
        });
        Button dash = (Button) findViewById(R.id.dashborad);
        dash.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Welcome.this, Dashboard.class);
                startActivity(intent);
            }
        });
        Button logout =(Button)findViewById(R.id.logout);
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getIntent().replaceExtras(new Bundle());
                getIntent().setAction("");
                getIntent().setData(null);
                getIntent().setFlags(0);
                Intent intent = new Intent(Welcome.this, Home.class);
                startActivity(intent);
            }
        });

    }



    public void OperatorApi(String JWT)
    {        final RequestQueue requestQueue = Volley.newRequestQueue(this);
        String url = "https://tsaw.tech/AppApi/Operator/operator-data.php";
        HashMap<String, String> params = new HashMap<>();
        params.put("jwt", JWT);
        JsonObjectRequest getRequest = new JsonObjectRequest(Request.Method.POST, url, new JSONObject(params), new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    user_name.setText(response.getString("name"));
                    verifed=response.getInt("isverify");
                    System.out.println("Varified"+verifed);
                    System.out.println("Data "+response.toString());
                    if(verifed==0)
                    {
                        Intent i = new Intent(Welcome.this,govt_id.class);
                        startActivity(i);
                        Toast.makeText(getBaseContext(),"Please Verify your Document",Toast.LENGTH_SHORT).show();
                    }



                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(Welcome.this,"Failed",Toast.LENGTH_SHORT).show();

            }
        }
        );
        // add it to the RequestQueue
        requestQueue.add(getRequest);

    }

}