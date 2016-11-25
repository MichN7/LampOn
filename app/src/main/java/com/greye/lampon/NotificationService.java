package com.greye.lampon;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.service.notification.NotificationListenerService;
import android.service.notification.StatusBarNotification;
import android.support.annotation.RequiresApi;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;
import android.widget.Toast;


@RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR2)
public class NotificationService extends NotificationListenerService {
    Context context;


    @Override

    public void onCreate() {

        super.onCreate();
        context = getApplicationContext();

    }

    @Override
    public void onNotificationPosted(StatusBarNotification sbn) {
        Log.d("Msg","Hay una notificacion");
        String pack = sbn.getPackageName();
        Intent msgrcv = new Intent("Msg");
        msgrcv.putExtra("package", pack);
        LocalBroadcastManager.getInstance(context).sendBroadcast(msgrcv);
    }

    @Override
    public void onNotificationRemoved(StatusBarNotification sbn) {
        Log.d("Msg","Se eliminó la notificacion");
        Intent i = new Intent();
        i.setAction("com.greye.lampon.NOTIFICATION_POSTED_RECEIVER");
        sendBroadcast(i);

    }


}
