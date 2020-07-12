package com.example.tsaw.javaFile;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.tsaw.R;

import static android.widget.Toast.LENGTH_SHORT;

public class splash1 extends AppCompatActivity {
    private static int SPLASH_TIME_OUT=3000;
    Boolean firstTime;
    SharedPreferences sharedPreferences;
    Button skip;
    private SharedPreferences mPreferences;
    private String your_user_name;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash1);
        skip=(Button)findViewById(R.id.Skip);


        mPreferences = getSharedPreferences("User", MODE_PRIVATE);
        SharedPreferences.Editor editor = mPreferences.edit();
        editor.putString("email", your_user_name);
        editor.commit();

        if (mPreferences.contains("email")) {
            Intent intent = new Intent(splash1.this, Welcome.class);
            startActivity(intent);
        }else {
            Intent intent = new Intent(splash1.this, Home.class);
            startActivity(intent);
        }

        skip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i= new Intent(splash1.this, Home.class);
                startActivity(i);
            }
        });


        if (getSharedPreferences("PREFERENCENAME",0).getBoolean("isLoginKey",false)){
            Intent i = new Intent(splash1.this, Welcome.class);
            startActivity(i);
            finish();
        }else{
            Intent i = new Intent(splash1.this, Home.class);
            startActivity(i);
            finish();
        }



        sharedPreferences =getSharedPreferences("myPrefs",MODE_PRIVATE);
        firstTime=sharedPreferences.getBoolean("firstTime",true);

        if(firstTime)
        {    new Handler().postDelayed(new Runnable() {
            public void run()
            {
                SharedPreferences.Editor editor =sharedPreferences.edit();
                firstTime =false;
                editor.putBoolean("firstTime",firstTime);
                editor.apply();
                Intent Home = new Intent(splash1.this, splash2.class);
                startActivity(Home);
                finish();
            }
        },SPLASH_TIME_OUT);

        }
        else
        {
            Intent i= new Intent(splash1.this, Home.class);
            startActivity(i);
        }



    }
}
