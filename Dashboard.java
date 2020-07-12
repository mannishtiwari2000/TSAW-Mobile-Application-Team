package com.example.tsaw.javaFile;

import android.app.Notification;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.tsaw.R;

import static com.example.tsaw.javaFile.App_Notification.Channel_1_ID;
import static com.example.tsaw.javaFile.App_Notification.Channel_2_ID;

public class Dashboard extends AppCompatActivity {
    Button call,launch,feedback;
    private NotificationManagerCompat managerCompat;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        managerCompat =NotificationManagerCompat.from(this);

        call=(Button)findViewById(R.id.Call_Customer);
        launch=(Button)findViewById(R.id.Lounch);
        feedback=(Button)findViewById(R.id.Feedback);
        feedback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Dashboard.this,"Feedback submiting ",Toast.LENGTH_SHORT).show();
            }
        });

        call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//            Toast.makeText(Dashboard.this,"Call connecting",Toast.LENGTH_SHORT).show();
                sendonChannel1();
            }
        });
        launch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//            Toast.makeText(Dashboard.this,"Task launching on maps",Toast.LENGTH_SHORT).show();
                sendonChannel2();
            }
        });

    }

    private void sendonChannel2() {
        Intent activityIntent = new Intent(this,Dashboard.class);

        Notification notification = new NotificationCompat.Builder(this,Channel_2_ID)
                .setSmallIcon(R.drawable.tv)
                .setContentTitle("Notification Demo 2")
                .setContentText("THis is a Demo Notification2 for TSAW App")
                .setPriority(NotificationCompat.PRIORITY_LOW)
                .setCategory(NotificationCompat.CATEGORY_MESSAGE)
                .build();
        managerCompat.notify(1,notification);
    }

    private void sendonChannel1() {
        Notification notification = new NotificationCompat.Builder(this,Channel_1_ID)
                .setSmallIcon(R.drawable.notifications)
                .setContentTitle("Notification Demo")
                .setContentText("THis is a Demo Notification for TSAW App")
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setCategory(NotificationCompat.CATEGORY_MESSAGE)
                .build();
        managerCompat.notify(1,notification);
    }

}