package com.example.tsaw.javaFile;

import android.Manifest;
import android.app.DownloadManager;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.tsaw.R;
import com.google.gson.JsonObject;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;

import static android.widget.Toast.LENGTH_LONG;

public class profile_form extends AppCompatActivity {
  private   Bitmap bitmap;
    int PICK_IMAGE_REQUEST = 1;
    Button open_camera, Submit;
    ImageView profile_pic;
    String JWT;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_form);
        open_camera = findViewById(R.id.Open_Camera);
        profile_pic = findViewById(R.id.imageView4);
        Submit = findViewById(R.id.upload);

        Submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                uploadImage();

            }
        });
        SharedPreferences prefs = getSharedPreferences("Token", MODE_PRIVATE);
        JWT = prefs.getString("Token", null);
        Toast.makeText(profile_form.this, JWT, Toast.LENGTH_SHORT).show();
        open_camera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showFileChooser();
            }
        });
    }

    public String getStringImage(Bitmap bmp){
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bmp.compress(Bitmap.CompressFormat.PNG, 100, baos);
        byte[] imageBytes = baos.toByteArray();
        String encodedImage = Base64.encodeToString(imageBytes, Base64.DEFAULT);
        return encodedImage;
    }

    private void uploadImage(){
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        String url="https://tsaw.tech/AppApi/Operator/upload_operator_data.php";
        //Showing the progress dialog
        String image = getStringImage(bitmap);
        //Getting Image Name

        //Creating parameters
        Map<String,String> params = new HashMap<>();

        params.put("profile_image", image);
        params.put("docFront",image);
        params.put("docBack",image);
        params.put("droneFront",image);
        params.put("droneTop",image);
        params.put("model","Q1RP");
        params.put("dgca","1");
        params.put("payloads","PAY");
        params.put("payload_capacity","Full");
        params.put("drone_age","20");
        params.put("drone_cost","10000");
        params.put("oan","23231231");
        params.put("dan","88343848332");
        params.put("jwt",JWT) ;

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, new JSONObject(params), new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Toast.makeText(profile_form.this, response.toString(), Toast.LENGTH_SHORT).show();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(profile_form.this, error.toString(), Toast.LENGTH_SHORT).show();
                System.out.println("Error: " + error
                        + ">>" + error.networkResponse.statusCode
                        + ">>" + error.networkResponse.data
                        + ">>" + error.getCause()
                        + ">>" + error.getMessage());

            }
        });
        requestQueue.add(jsonObjectRequest);

    }

    private void showFileChooser() {
        Intent pickImageIntent = new Intent(Intent.ACTION_PICK,
                android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        pickImageIntent.setType("image/*");
        pickImageIntent.putExtra("aspectX", 1);
        pickImageIntent.putExtra("aspectY", 1);
        pickImageIntent.putExtra("scale", true);
        pickImageIntent.putExtra("outputFormat",
                Bitmap.CompressFormat.JPEG.toString());
        startActivityForResult(Intent.createChooser(pickImageIntent, "Select Picture"), PICK_IMAGE_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {
            Uri filePath = data.getData();
            try {
                //Getting the Bitmap from Gallery
                bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), filePath);
                //Setting the Bitmap to ImageView
              profile_pic.setImageBitmap(bitmap);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
