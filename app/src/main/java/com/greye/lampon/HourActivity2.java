package com.greye.lampon;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.provider.MediaStore;
import android.support.annotation.RequiresApi;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.TaskStackBuilder;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TimePicker;
import android.widget.Toast;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.appindexing.Thing;
import com.google.android.gms.common.api.GoogleApiClient;

import java.util.Calendar;

import static com.facebook.FacebookSdk.getApplicationContext;


@RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
public class HourActivity2 extends AppCompatActivity implements View.OnClickListener {


    int Aplazar = 0;

    AlarmManager alarm_Manager;
    TimePicker alarm_timepicker;
    Context context;
    PendingIntent pending_intent;
    Intent mi_intent;
    Calendar cal;
    boolean ban;
    CheckBox cb1;
    CheckBox cb2;
    CheckBox cb3;
    CheckBox cb4;
    CheckBox cb5;
    CheckBox cb6;
    CheckBox cb7;
    EditText et;
    SQLControlador dbconeccion;

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        if(Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP){
            setContentView(R.layout.activity_versiones_viejas);
        }else{
            setContentView(R.layout.activity_hour2);
        }
        final Calendar cal = Calendar.getInstance();
        this.context = this;
        ban = false;
        final String Dias;



        Button Cancelar = (Button) findViewById(R.id.btnCancelar);
        Cancelar.setOnClickListener(this);

        Button btnGuardar = (Button) findViewById(R.id.btnGuardar);
        cb1  = (CheckBox) findViewById(R.id.ChBLunes);
        cb2  = (CheckBox) findViewById(R.id.ChBMartes);
        cb3  = (CheckBox) findViewById(R.id.ChBMiercoles);
        cb4  = (CheckBox) findViewById(R.id.ChBJueves);
        cb5  = (CheckBox) findViewById(R.id.ChBViernes);
        cb6  = (CheckBox) findViewById(R.id.ChBSabado);
        cb7  = (CheckBox) findViewById(R.id.ChBDomingo);
        et = (EditText) findViewById(R.id.editText);
        dbconeccion = new SQLControlador(this);
        dbconeccion.abrirBaseDeDatos();

        alarm_Manager = (AlarmManager) getSystemService(ALARM_SERVICE);
        //se inicializa el timepiker
        alarm_timepicker = (TimePicker) findViewById(R.id.tpHora);

        final Intent mi_intent = new Intent(this.context, Alarm_Receiver.class);


          btnGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!ban) {
                    String Dias= "";
                    Log.e("dentro del OnClick", "que entro al btG ");

                    boolean ban2 = true;

                    if (cb1.isChecked()) {
                        if(ban2) {
                            Dias = "Lunes";
                            cal.set(java.util.Calendar.DAY_OF_WEEK, 2);
                            ban2=false;
                            Log.e("String es : ","Lunes es el primero");

                        }
                        else {
                            Dias += ", Lunes";

                        }


                    } if (cb2.isChecked()) {

                        if(ban2) {
                            Dias = "Martes";
                            cal.set(java.util.Calendar.DAY_OF_WEEK, 3);
                            ban2=false;

                        }
                        else {
                            Dias += ", Martes";
                            Log.e("String es : ","martes es el segundo");

                        }
                    } if (cb3.isChecked()) {
                        if(ban2) {
                            Dias = "Miercoles";
                            cal.set(java.util.Calendar.DAY_OF_WEEK, 4);
                            ban2=false;
                        }
                        else {
                            Dias += ", Miercoles";

                        }
                    }  if (cb4.isChecked()) {
                        if(ban2) {
                            Dias = "Jueves";
                            cal.set(java.util.Calendar.DAY_OF_WEEK, 5);
                            ban2=false;
                        }
                        else {
                            Dias += ", Jueves";
                        }
                    } if (cb5.isChecked()) {
                        if(ban2) {
                            Dias = "Viernes";
                            cal.set(java.util.Calendar.DAY_OF_WEEK,6);
                            ban2=false;
                        }
                        else {
                            Dias += ", Viernes ";

                        }

                    } if (cb6.isChecked()) {
                        if(ban2) {
                            Dias = "Sabado";
                            cal.set(java.util.Calendar.DAY_OF_WEEK, 7);
                            ban2=false;
                        }
                        else {
                            Dias += ", Sabado";


                        }
                    } if (cb7.isChecked()) {
                        if(ban2) {
                            Dias = "Domingo";
                            cal.set(java.util.Calendar.DAY_OF_WEEK, 8);
                            ban2=false;
                        }
                        else {
                            Dias += ", Domingo";
                        }
                    }
                    Log.e("String es : ",Dias);




                    cal.set(Calendar.HOUR_OF_DAY, alarm_timepicker.getCurrentHour());
                    cal.set(Calendar.MINUTE, alarm_timepicker.getCurrentMinute());

                    int hora = alarm_timepicker.getCurrentHour();
                    int minutos = alarm_timepicker.getCurrentMinute();
                    AlertDialog.Builder builder = new AlertDialog.Builder(HourActivity2.this);
                    builder.setTitle("Modify Customer Details");

                    String hora_string = String.valueOf(hora+" : "+minutos);
                   // String minutos_string = String.valueOf(minutos);
                    Toast.makeText(getApplicationContext(),"La alamar se activara a las "+hora_string,Toast.LENGTH_SHORT).show();

                    mi_intent.putExtra("extra","alarm_on");

                    pending_intent = PendingIntent.getBroadcast(HourActivity2.this, 0, mi_intent, PendingIntent.FLAG_UPDATE_CURRENT);
                    alarm_Manager.set(AlarmManager.RTC_WAKEUP, cal.getTimeInMillis(), pending_intent);
                    ban = true;

                    //se mandan los valores a la DB
                    String titulo = et.getText().toString();
                    dbconeccion.insertarDatos(titulo,hora_string,Dias);
                    Intent main = new Intent(HourActivity2.this, AlarmActivity.class)
                            .setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(main);

                }


            }

        });


        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }



    FragmentManager fm = getSupportFragmentManager();

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void onClick(View v) {



        if (v.getId() == R.id.btnCancelar) {
            finish();

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

    public void setAlarm_sun(int dayOfWeek) {
        cal.set(Calendar.DAY_OF_WEEK, dayOfWeek);
        Toast.makeText(getApplicationContext(), "sun "+cal.get(Calendar.DAY_OF_WEEK),Toast.LENGTH_SHORT).show();

        Toast.makeText(getApplicationContext(), "Finsh", Toast.LENGTH_SHORT).show();

        final Intent intent = new Intent(this, Alarm_Receiver.class);

        PendingIntent pendingIntent0 = PendingIntent.getBroadcast(this, 0,
                intent, 0);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 12345,
                intent, PendingIntent.FLAG_UPDATE_CURRENT);

        Long alarmTime = cal.getTimeInMillis();

        // am.setRepeating(AlarmManager.RTC_WAKEUP, alarmTime,7* 24 * 60 * 60 * 1000 , pendingIntent);
        alarm_Manager.setRepeating(AlarmManager.RTC_WAKEUP, alarmTime,7* 24 * 60 * 60 * 1000 , pendingIntent);

    }


}
