package com.example.tsaw.javaFile;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
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

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Drone_detail extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
//    String drone_type[]={"Select Drone Type","Drone A","Drone B"};
    String certified[] ={"Certified ","Yes","No"};
    ImageView drone_image_front,drone_image_back;
    Button take_pic,front,Back;
//    int PICK_IMAGE_REQUEST = 1;
//    Bitmap bitmap;

     @Override
    protected void onCreate(Bundle savedInstanceState) {
         super.onCreate(savedInstanceState);
         setContentView(R.layout.activity_drone_detail);
         Spinner spinner2 = (Spinner) findViewById(R.id.spinner2);
         //Spinner 1 code
//        Spinner spinner1=(Spinner)findViewById(R.id.spinner);
//        spinner1.setOnItemSelectedListener(this);
//        ArrayAdapter aa = new ArrayAdapter(this,android.R.layout.simple_spinner_item,drone_type);
//        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//        spinner1.setAdapter(aa);
//Spinner 2 code
        spinner2.setOnItemSelectedListener(this);
         ArrayAdapter aa2 = new ArrayAdapter(this,android.R.layout.simple_spinner_item,certified);
         aa2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
         spinner2.setAdapter(aa2);
//
//
         drone_image_front=(ImageView)findViewById(R.id.drone_img_front);
         drone_image_back=(ImageView)findViewById(R.id.drone_img_back);
         take_pic =(Button)findViewById(R.id.Take_photo);
                   front=(Button)findViewById(R.id.front);
                   Back=(Button)findViewById(R.id.back);
//

//
//         take_pic.setOnClickListener(new View.OnClickListener() {
//             @Override
//             public void onClick(View v) {
////                 Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
////                 startActivityForResult(intent,100);
//             }
//         });
//
    }
//
//
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String text = parent.getItemAtPosition(position).toString();

        Toast.makeText(parent.getContext(),text,Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

}
