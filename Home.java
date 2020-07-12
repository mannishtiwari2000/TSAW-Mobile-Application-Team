package com.example.tsaw.javaFile;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.tsaw.R;
import com.google.gson.JsonObject;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

import okhttp3.WebSocketListener;

import static android.widget.Toast.LENGTH_SHORT;

public class Home extends AppCompatActivity {
    private String your_user_name;
    private EditText Password, Email;
    private Button Register;
    private Button login;
    TextView forget;
    public String token;
    SharedPreferences sp;
    private SharedPreferences mPreferences;

       Dbhelper mydb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Email = findViewById(R.id.Email);
        Password = findViewById(R.id.Password);
        forget = (TextView) findViewById(R.id.forget_password);
        Register = (Button) findViewById(R.id.Register);
        login = (Button) findViewById(R.id.Login);
          mydb = new Dbhelper(this);




        Email.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
                if (Email.getText().toString().isEmpty()) {
                    Email.setError("enter email address");

                } else {
                    if (Email.getText().toString().trim().matches(emailPattern)) {
                        Toast.makeText(Home.this, "valid email address", Toast.LENGTH_SHORT).show();

                    } else {
                        Email.setError("Invalid email address");

                    }
                }
            }
        });
        Password.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (Password.getText().toString().isEmpty()) {
                    Password.setError("enter Password");
                }
            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = Email.getText().toString().trim();
                String password = Password.getText().toString().trim();
                userlogin(email, password);
                Clear();
//                Toast.makeText(Home.this, "CLICK", Toast.LENGTH_LONG).show();


            }
        });

        Register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent intent = new Intent(Home.this, com.example.tsaw.javaFile.Register.class);
                startActivity(intent);
            }
        });
        forget.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(Home.this, Forget_password.class);
                startActivity(intent);
            }
        });

    }

    private void userlogin(final String email, final String password) {
        final RequestQueue requestQueue = Volley.newRequestQueue(this);
        String url = "https://tsaw.tech/AppApi/Operator/login.php";
        HashMap<String, String> params = new HashMap<String, String>();
        params.put("email", email);
        params.put("password", password);


        JsonObjectRequest getRequest = new JsonObjectRequest(Request.Method.POST, url, new JSONObject(params),
                new Response.Listener<JSONObject>() {
                    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
                    @Override
                    public void onResponse(JSONObject response) {

                        try {
                            token = response.getString("jwt");
                            Toast.makeText(Home.this, "Sucessfully", Toast.LENGTH_LONG).show();
                            Intent intent = new Intent(Home.this, Welcome.class);
                            startActivity(intent);

                            SharedPreferences prefs = getSharedPreferences("Token", MODE_PRIVATE);
                            SharedPreferences.Editor editor = prefs.edit();
                            editor.putString("Token", token);
                            editor.commit();
                            //Fetchdata(email,password,token);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
//
//                        Toast.makeText(Home.this, response.toString(), Toast.LENGTH_LONG).show();
                    }


                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(Home.this, "ID or password Incorrect"
                                , Toast.LENGTH_SHORT).show();
                        System.out.println("Error "+"Error: " + error
                                + ">>" + error.networkResponse.statusCode
                                + ">>" + error.networkResponse.data
                                + ">>" + error.getCause()
                                + ">>" + error.getMessage());
                    }
                }
        );

// add it to the RequestQueue
        requestQueue.add(getRequest);
    }

    private void Clear() {
        Password.setText("");
        Email.setText("");

    }

//    private void Fetchdata(String email, String password, String token)
//    {
//        Cursor c = mydb.Getdata();
//        if (c.getCount() == 0) {
//            boolean insert = mydb.InsertData(email,password,token);
//            if (insert == true) {
//                Toast.makeText(Home.this, "Successfully Data Insert", LENGTH_SHORT).show();
//            } else {
//                Toast.makeText(Home.this, "Failed Data Insert", LENGTH_SHORT).show();
//            }
//
//        }
//        else {
//           Boolean update= mydb.Updatedata(email,password,token,"1");
//            if (update==true)
//            {
//                Toast.makeText(Home.this,"Welcome Back", Toast.LENGTH_SHORT).show();
//            }
//            else
//            {
//                Toast.makeText(Home.this,"Sorry", Toast.LENGTH_SHORT).show();
//            }
//        }
//    }

}