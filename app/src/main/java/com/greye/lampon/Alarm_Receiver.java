package com.greye.lampon;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

/**
 * Created by emmanuelgarcia on 10/11/16.
 */

public class Alarm_Receiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {

        Log.e("esto es la clase Alarm","que ");


        Intent service_intent = new Intent(context,RingTonePlayingService.class);

                //Se inicia el servicio de ringtone
        context.startService(service_intent);


    }
}
