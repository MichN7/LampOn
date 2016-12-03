package com.greye.lampon;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

/**
 * Created by Adán on 03/12/2016.
 */

public class serviceBluetooth extends Service {
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    public void onCreate(){
        Log.d("xxx", "Servicio creado...");
    }

  //  public
}
