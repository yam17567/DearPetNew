package com.example.dearpet;

import android.app.IntentService;
import android.app.NotificationManager;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;

public class DateServies extends IntentService {


    public DateServies(String name) {
        super ( "Timer Service" );
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        NotificationCompat.Builder nb = new NotificationCompat.Builder ( this );
        nb.setContentText ( "its your pet birthday! coungrts! now you need to celebrate" );
        nb.setContentTitle ("birthday");
        nb.setSmallIcon ( R.mipmap.ic_icon );

        NotificationManager nm = (NotificationManager)getSystemService ( NOTIFICATION_SERVICE );
        nm.notify ( 1,nb.build () );

    }
}
