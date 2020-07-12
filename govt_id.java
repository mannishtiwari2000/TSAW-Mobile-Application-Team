package com.example.tsaw.javaFile;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.tsaw.R;

public class govt_id extends AppCompatActivity {
    Button front, back, submit;
    ImageView front_img, back_img;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_govt_id);

        front = findViewById(R.id.front);
        back = findViewById(R.id.back);
        submit = findViewById(R.id.submit_ID);
        front_img = findViewById(R.id.front_img);
        back_img = findViewById(R.id.back_img);


    }
}
