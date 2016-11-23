package com.greye.lampon;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.app.NotificationBuilderWithBuilderAccessor;
import android.support.v7.app.NotificationCompat;
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
    int startId;
    boolean isRunning;



    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    public  int onStartCommand(Intent intent, int flags, int startId){

        Log.i("En el servicio","Received star id"+startId + ":"+intent);

        String estado = intent.getExtras().getString("extra");

        Log.e("ring tone extra is",estado);


        assert estado !=null;
        switch (estado) {
            case "alarm_on":
                startId = 1;
                break;
            case "alarm_off":
                startId = 0;
                break;
            default:
                startId = 0;
                break;
        }


        if(!this.isRunning && startId == 1){
            Log.e ("no hay musica,","y esta iniciando");



            media_song = MediaPlayer.create(this,R.raw.bell);
            media_song.start();

            this.isRunning = true;
            this.startId = 0;

            setNotification();
        }
        //aqui esta el la alarma sonando y el usuario presiona "btnparar"
        else if(this.isRunning && startId==0){
            Log.e("hay musica","y presionaste el boton parar");

        //se detienee la alarma
            media_song.stop();
            media_song.reset();
            this.isRunning = false;
            this.startId = 0;
        }
        else if(!this.isRunning && startId == 0){
            Log.e("no hay musica","y presionas el boton parar");
            this.isRunning = false;
            this.startId = 0;

        }
        else if(this.isRunning && startId == 1){
            Log.e("hay musica","y presionas el boton de guardar");
            this.isRunning = true;
            this.startId = 1;

        }
        else {


        }



        //toma los strings que se metieron como valores extas del intent




        return START_NOT_STICKY;
    }
    public void onDestroy(){
        Log.e("se destruyo el ","servicio");
        super.onDestroy();
        this.isRunning=false;

    }

    private void setNotification() {

//**add this line**
        int requestID = (int) System.currentTimeMillis();
        NotificationManager m = (NotificationManager) getApplication().getSystemService(Context.NOTIFICATION_SERVICE);

        Intent notificationIntent = new Intent(getApplicationContext(),PosponerAcivity.class);

//**add this line**
        notificationIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);

//**edit this line to put requestID as requestCode**
        PendingIntent contentIntent = PendingIntent.getActivity(this, requestID,notificationIntent, PendingIntent.FLAG_UPDATE_CURRENT);

        NotificationCompat.Builder mBuilder = (NotificationCompat.Builder) new NotificationCompat.Builder(getApplicationContext())
                .setSmallIcon(R.drawable.focoicon)
                .setContentTitle("Alarma")
                .setStyle(new NotificationCompat.BigTextStyle()
                        .bigText("Entrar"))
                .setContentText("Entrar")
                .setAutoCancel(true);
        mBuilder.setContentIntent(contentIntent);
        m.notify(0, mBuilder.build());

    }

}
