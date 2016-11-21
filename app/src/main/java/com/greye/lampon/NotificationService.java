package com.greye.lampon;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.service.notification.NotificationListenerService;
import android.service.notification.StatusBarNotification;
import android.support.annotation.RequiresApi;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by Greye on 12/11/2016.
 */

@RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR2)
public class NotificationService extends NotificationListenerService {
    Context context;
    MainActivity main=new MainActivity();

    @Override

    public void onCreate() {

        super.onCreate();
        context = getApplicationContext();

    }

    @Override
    public void onNotificationPosted(StatusBarNotification sbn) {
        Log.i("Msg","Hay una notificacion");
        Intent i = new Intent();
        i.setAction("com.greye.lampon.NOTIFICATION_POSTED_RECEIVER");
        sendBroadcast(i);
       main.sendData("2");
        //Aqui se envia datos al arduino --->> 1
    }

    @Override
    public void onNotificationRemoved(StatusBarNotification sbn) {
        Log.i("Msg","Se eliminó la notificacion");
        Intent i = new Intent();
        i.setAction("com.greye.lampon.NOTIFICATION_POSTED_RECEIVER");
        sendBroadcast(i);
        //Aqui se envia datos al arduino --->> 0
       main.sendData("3");
    }
}
