package com.example.tsaw.javaFile;

import android.app.Application;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.os.Build;
import android.support.annotation.RequiresApi;

import com.example.tsaw.R;

public class App_Notification extends Application {
    public static final String Channel_1_ID = "Channel 1";
    public static final String Channel_2_ID = "Channel 2";


    public void onCreate() {
        super.onCreate();
        createNotificationChannel();

    }

    private void createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel1 = new NotificationChannel(
                    Channel_1_ID,"Channel 1",NotificationManager.IMPORTANCE_HIGH
            );
            channel1.setDescription("This is Channel 1");

            NotificationChannel channel2 = new NotificationChannel(
                    Channel_2_ID,"Channel 1",NotificationManager.IMPORTANCE_LOW
            );
            channel2.setDescription("This is Channel 1");

            NotificationManager manager = getSystemService(NotificationManager.class);
            manager.createNotificationChannel(channel1);
            manager.createNotificationChannel(channel2);
        }

    }

}
