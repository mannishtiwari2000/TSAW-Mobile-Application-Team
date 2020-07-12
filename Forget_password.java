package com.example.tsaw.javaFile;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.tsaw.R;

public class Forget_password extends AppCompatActivity {
    Button submit;
    EditText New_pass, New_password, Email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget_password);
        Email = findViewById(R.id.Email_forget);
        New_pass = findViewById(R.id.New_pass);
        New_password = findViewById(R.id.New_password);
        submit = (Button) findViewById(R.id.submit);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Clear();
                Toast.makeText(Forget_password.this, "Submitted Data", Toast.LENGTH_SHORT).show();
            }
        });
        Email.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+";
                if (Email.getText().toString().isEmpty()) {
                    Email.setError("Enter Email Address");
                } else {
                    if (Email.getText().toString().trim().matches(emailPattern)) {
                        Email.setError("Invalid email address");

                    }
                }
            }
        });
        New_pass.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (New_pass.length() == 0) {
                    New_pass.setError("FIELD CANNOT BE EMPTY");
                }
            }
        });
        New_password.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (New_password.length() == 0) {
                    New_password.setError("FIELD CANNOT BE EMPTY");
                }
            }
        });
    }

    private void Clear() {
        New_password.setText("");
        Email.setText("");
        New_pass.setText("");

    }
}