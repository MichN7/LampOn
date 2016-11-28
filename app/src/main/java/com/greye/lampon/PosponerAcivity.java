package com.greye.lampon;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.TimePicker;

import java.util.Calendar;

public class PosponerAcivity extends AppCompatActivity implements View.OnClickListener{
    AlarmManager alarm_Manager;
    PendingIntent pending_intent;
    Intent mi_intent;
    Context context;
    TimePicker alarm_timepicker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        this.context = this;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_posponer_acivity);
        alarm_Manager = (AlarmManager) getSystemService(ALARM_SERVICE);
        final Intent mi_intent = new Intent(this.context, Alarm_Receiver.class);

        Button Posponer = (Button) findViewById(R.id.btnPosponer);
        Posponer.setOnClickListener(this);

        Button Parar = (Button) findViewById(R.id.btnParar);
        Parar.setOnClickListener(this);

        Parar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e("Entro a Clase Posponer","Entro a boton PARAR");

                alarm_Manager.cancel(pending_intent);
                mi_intent.putExtra("extra","alarm_off");
                sendBroadcast(mi_intent);
                finish();
                Intent intentAlarm = new Intent(getApplicationContext(), AlarmActivity.class);
                startActivity(intentAlarm);
            }
        });


        Posponer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if (v.getId() == R.id.btnPosponer) {

                    Log.e("Entro a Clase Posponer", "Entro a boton Posponer");
                    try {
                        DFragmentDesplazar dFragmentDs = new DFragmentDesplazar();
                        // Show DialogFragment
                        dFragmentDs.show(fm, "Dialog Fragment");
                    } catch (Exception e) {
                        Log.e(null, "error");
                    }
                }
            }
        });


    }

    FragmentManager fm = getSupportFragmentManager();

    @Override
    public void onClick(View v) {


        }




}
