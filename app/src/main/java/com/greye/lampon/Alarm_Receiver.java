package com.greye.lampon;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.media.MediaPlayer;
import android.media.Ringtone;
import android.support.v7.app.AlertDialog;
import android.util.Log;

import java.util.Calendar;
import java.util.Date;

/**
 * Created by emmanuelgarcia on 10/11/16.
 */

public class Alarm_Receiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {



        String toma_string = intent.getExtras().getString("extra");
        Log.e("la llave es",toma_string);
        Intent service_intent = new Intent(context,RingTonePlayingService.class);

        service_intent.putExtra("extra",toma_string);

                //Se inicia el servicio de ringtone
        context.startService(service_intent);

    }
}

