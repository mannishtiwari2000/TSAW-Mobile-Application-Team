package com.example.tsaw.javaFile;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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


import org.json.JSONObject;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class Register extends AppCompatActivity {

    Button submit;

    EditText Name, mobile, Email, Pincode, pass;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        Name = (EditText) findViewById(R.id.name);
        mobile = (EditText) findViewById(R.id.mobile);
        Email = (EditText) findViewById(R.id.email);
        Pincode = (EditText) findViewById(R.id.pin_code);
        pass = (EditText) findViewById(R.id.password);
        submit = (Button) findViewById(R.id.submit);

        Name.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                final String SName=Name.getText().toString();

                if(SName.length()==0)
                {
                    Name.setError("FIELD CANNOT BE EMPTY");
                }
                else if(!SName.matches("[a-zA-Z ]+"))
                {
                    Name.setError("ENTER ONLY ALPHABETICAL CHARACTER");
                }
            }
        });
        Email.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
                if(Email.getText().toString().isEmpty()) {
                  Email.setError("Enter Email Address");

                }else {
                    if (Email.getText().toString().trim().matches(emailPattern)) {
                        Email.setError("valid email address");

                    } else {
           if(Email.getText().toString().trim().matches(emailPattern)) {
               Email.setError("Invalid email address");

           }
                    }
                }


            }
        });

        mobile.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(mobile.getText().toString().isEmpty()) {
                    mobile.setError("enter email address..........");
                }
                else if(mobile.getText().toString().length()>10 && mobile.getText().toString().length()<10)
                {
                    mobile.setError("Mobile number must be 10 digit..........");
                }
            }
        });
        Pincode.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(Pincode.length()==0)
                {
                   Pincode.setError("FIELD CANNOT BE EMPTY");
                }
            }
        });

        pass.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(pass.length()==0)
                {

                    pass.setError("FIELD CANNOT BE EMPTY");
                }
            }
        });

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = Name.getText().toString().trim();
                String email = Email.getText().toString().trim();
                String mobile_no = mobile.getText().toString().trim();
                String password = pass.getText().toString().trim();
                String pincode = Pincode.getText().toString().trim();
                UserRegister(name, email, password, pincode, mobile_no);
//        Toast.makeText(Register.this,"Hello",Toast.LENGTH_SHORT).show();

            }
        });


    }

    private void UserRegister(String name, String email, String password, String pincode, String mobile_no) {

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        String url = "https://tsaw.tech/AppApi/Operator/create_operator.php";
        HashMap<String, String> params = new HashMap<String, String>();
        params.put("name", name);
        params.put("email", email);
        params.put("password", password);
        params.put("mobile_no", mobile_no);
        params.put("pincode", pincode);

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, new JSONObject(params), new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Toast.makeText(Register.this, response.toString(), Toast.LENGTH_SHORT).show();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(Register.this, "Failed", Toast.LENGTH_SHORT).show();
            }
        });
        requestQueue.add(jsonObjectRequest);

    }

}