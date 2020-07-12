package com.example.tsaw.javaFile;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.tsaw.R;

public class MainActivity extends AppCompatActivity {
private static int SPLASH_TIME_OUT=3000;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
      new Handler().postDelayed(new Runnable() {
      public void run()
      {
          Intent Home = new Intent(MainActivity.this, splash1.class);
          startActivity(Home);
          finish();
      }
      },SPLASH_TIME_OUT);

    }
}
