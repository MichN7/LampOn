package com.greye.lampon;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.provider.MediaStore;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.TimePicker;
import android.widget.Toast;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.appindexing.Thing;
import com.google.android.gms.common.api.GoogleApiClient;

import java.util.Calendar;


public class HourActivity2 extends AppCompatActivity implements View.OnClickListener {


    int Aplazar = 0;

    AlarmManager alarm_Manager;
    TimePicker alarm_timepicker;
    Context context;
    PendingIntent pending_intent;
    Intent mi_intent;
    Calendar cal;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        DbHelper helper = new DbHelper(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hour2);
        final Calendar cal = Calendar.getInstance();
        this.context = this;

        Button Repetir = (Button) findViewById(R.id.btnRepetir);
        Repetir.setOnClickListener(this);

        Button Aplazar = (Button) findViewById(R.id.btnAplazar);
        Aplazar.setOnClickListener(this);

        Button Cancelar = (Button) findViewById(R.id.btnCancelar);
        Cancelar.setOnClickListener(this);

        Button btnGuardar = (Button) findViewById(R.id.btnGuardar);
        Button btnPararAlarma = (Button) findViewById(R.id.btnPararAlarma);

        alarm_Manager = (AlarmManager) getSystemService(ALARM_SERVICE);
        //se inicializa el timepiker
        alarm_timepicker = (TimePicker) findViewById(R.id.tpHora);

        final Intent mi_intent = new Intent(this.context, Alarm_Receiver.class);

        btnGuardar.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onClick(View v) {


                cal.set(Calendar.HOUR_OF_DAY, alarm_timepicker.getCurrentHour());
                cal.set(Calendar.MINUTE, alarm_timepicker.getCurrentMinute());

                pending_intent = PendingIntent.getBroadcast(HourActivity2.this, 0, mi_intent, PendingIntent.FLAG_UPDATE_CURRENT);
                alarm_Manager.set(AlarmManager.RTC_WAKEUP, cal.getTimeInMillis(), pending_intent);

                mi_intent.putExtra("extra","alarm_on");

            }
        });
/*
        btnPararAlarma.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    alarm_Manager.cancel(pending_intent);
                    mi_intent.putExtra("extra","alarm_off");
                    sendBroadcast(mi_intent);


            }
        });
        */

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }


    FragmentManager fm = getSupportFragmentManager();

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btnRepetir) {
            try {

                DFragment dFragment = new DFragment();
                // Show DialogFragment
                dFragment.show(fm, "Dialog Fragment");
            } catch (Exception e) {
                Log.e(null, "error");
            }
        }
        if (v.getId() == R.id.btnAplazar) {
            try {
                DFragmentDesplazar dFragmentDs = new DFragmentDesplazar();
                // Show DialogFragment
                dFragmentDs.show(fm, "Dialog Fragment");
            } catch (Exception e) {
                Log.e(null, "error");
            }
        }
        if (v.getId() == R.id.btnCancelar) {
            alarm_Manager.cancel(pending_intent);
            sendBroadcast(mi_intent);
            finish();
        }


        if (v.getId() == R.id.btnGuardarRepetir) {
            CheckBox cb1 = (CheckBox) findViewById(R.id.checkBox);
            CheckBox cb2 = (CheckBox) findViewById(R.id.checkBox2);
            CheckBox cb3 = (CheckBox) findViewById(R.id.checkBox3);
            CheckBox cb4 = (CheckBox) findViewById(R.id.checkBox4);
            CheckBox cb5 = (CheckBox) findViewById(R.id.checkBox5);
            CheckBox cb6 = (CheckBox) findViewById(R.id.checkBox6);
            CheckBox cb7 = (CheckBox) findViewById(R.id.checkBox7);


            if (cb1.isChecked()) {

                cal.set(Calendar.DAY_OF_WEEK, 2);
            } else if (cb2.isChecked()) {
                cal.set(Calendar.DAY_OF_WEEK, 3);
            } else if (cb3.isChecked()) {
                cal.set(Calendar.DAY_OF_WEEK, 4);
            } else if (cb4.isChecked()) {
                cal.set(Calendar.DAY_OF_WEEK, 5);
            } else if (cb5.isChecked()) {
                cal.set(Calendar.DAY_OF_WEEK, 6);
            } else if (cb6.isChecked()) {
                cal.set(Calendar.DAY_OF_WEEK, 7);
            } else if (cb7.isChecked()) {
                cal.set(Calendar.DAY_OF_WEEK, 8);
            }


            if (v.getId() == R.id.btnGuardarDezplazar) {
                RadioButton Rbtn = (RadioButton) findViewById(R.id.Rbtn);
                RadioButton Rbtn1 = (RadioButton) findViewById(R.id.Rbtn1);
                RadioButton Rbtn2 = (RadioButton) findViewById(R.id.Rbtn2);
                RadioButton Rbtn3 = (RadioButton) findViewById(R.id.Rbtn3);
                RadioButton Rbtn4 = (RadioButton) findViewById(R.id.Rbtn4);

                if (Rbtn.isChecked()) {
                    Aplazar = 3;
                } else if (Rbtn1.isChecked()) {
                    Aplazar = 5;
                } else if (Rbtn2.isChecked()) {
                    Aplazar = 10;
                } else if (Rbtn3.isChecked()) {
                    Aplazar = 15;
                } else if (Rbtn4.isChecked()) {
                    Aplazar = 30;
                }

            }

        }
    }


    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    public Action getIndexApiAction() {
        Thing object = new Thing.Builder()
                .setName("HourActivity2 Page") // TODO: Define a title for the content shown.
                // TODO: Make sure this auto-generated URL is correct.
                .setUrl(Uri.parse("http://[ENTER-YOUR-URL-HERE]"))
                .build();
        return new Action.Builder(Action.TYPE_VIEW)
                .setObject(object)
                .setActionStatus(Action.STATUS_TYPE_COMPLETED)
                .build();
    }

    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        AppIndex.AppIndexApi.start(client, getIndexApiAction());
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        AppIndex.AppIndexApi.end(client, getIndexApiAction());
        client.disconnect();
    }
}
