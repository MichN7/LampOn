package com.greye.lampon;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.Toast;

import java.security.Provider;
import java.util.List;
import java.util.Map;

/**
 * Created by emmanuelgarcia on 13/11/16.
 */

public class RingTonePlayingService extends Service {

    MediaPlayer media_song;


    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    public  int onStartCommand(Intent intent,int flags,int startId){

        Log.i("En el servicio","Received star id"+startId + ":"+intent);

        media_song = MediaPlayer.create(this,R.raw.bell);
        media_song.start();

        //toma los strings que se metieron como valores extas del intent

//        String estado = intent.getExtras().getString("extras");



        return START_NOT_STICKY;
    }
    public void onDestroy(){

        Toast.makeText(this,"de destrulle la llamada",Toast.LENGTH_SHORT).show();;

    }

}
