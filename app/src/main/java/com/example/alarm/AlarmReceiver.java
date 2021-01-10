package com.example.alarm;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;

import androidx.core.app.NotificationCompat;

public class AlarmReceiver extends BroadcastReceiver {

    private static final String CHANNEL_ID="SAMPLE_CHANNEL";
    @Override
    public void onReceive(Context context, Intent intent) {
        //getting ID and message from intent.

        int notificationId=intent.getIntExtra("notificationId",0);
        String message=intent.getStringExtra("message");

        //call mainactivity when notification is tapped

        Intent mainIntent =new Intent(context,MainActivity.class);
        PendingIntent contentIntent=PendingIntent.getActivity(
                context,0,mainIntent,0
        );

        //Notifications

        NotificationManager notificationManager=(NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O)
        {
           //for API 26 and above

           CharSequence channel_name="My Notification";
           int importance=NotificationManager.IMPORTANCE_DEFAULT;

            NotificationChannel channel=new NotificationChannel(CHANNEL_ID,channel_name,importance);
            notificationManager.createNotificationChannel(channel);
        }

        //prepare notification

        NotificationCompat.Builder builder= new NotificationCompat.Builder(context,CHANNEL_ID)
                .setSmallIcon(android.R.drawable.ic_dialog_info)
                .setContentTitle("TITLE")
                .setContentText(message)
                .setContentIntent(contentIntent)
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .setAutoCancel(true);

        //Notify
        notificationManager.notify(notificationId,builder.build());



    }
}
