package com.greye.lampon;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.*;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Set;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {
   int REQUEST_ENABLE_BT;
    ImageView socialImage;
    BluetoothAdapter adaptadorBluetooth = BluetoothAdapter.getDefaultAdapter();
    ArrayAdapter<String>ArrayAdapter= new ArrayAdapter<String>(this,0);
    @Override


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        socialImage = (ImageView) findViewById(R.id.imageView2);
        ImageView imageView = (ImageView) findViewById(R.id.imageView);
        ImageView imageView2 = (ImageView) findViewById(R.id.imageView2);
        bluetoothAdapter();
        imageView.setOnClickListener(this);
        imageView2.setOnClickListener(this);
        descubirDispositivos();

    }

        public void bluetoothAdapter(){

            if(adaptadorBluetooth==null){
                //conectividad bluetooth no es soportada, mandar toast
                Toast.makeText(getApplicationContext(),R.string.bluetooth_no_soportado, Toast.LENGTH_SHORT).show();
            }
            if(!adaptadorBluetooth.isEnabled()){
            Intent i = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
                startActivityForResult(i,REQUEST_ENABLE_BT);
            }


        }

    public void descubirDispositivos(){

        Set<BluetoothDevice> pairedDevices = adaptadorBluetooth.getBondedDevices();//arrayAdapter con dispositivos conocidos
        if (pairedDevices.size() > 0) {
            // revisar nombres de dispositivos
            for (BluetoothDevice device : pairedDevices) {
                // Add the name and address to an array adapter to show in a ListView
                ArrayAdapter.add(device.getName() + "\n" + device.getAddress());
            }
        }
    }

//broadcastReciver para recibir informacion de los dispositivos bluetooth descubiertos
    private final BroadcastReceiver dispositivoEncontrado = new BroadcastReceiver() {
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            // cuando encuentra el dispositivo
            if (BluetoothDevice.ACTION_FOUND.equals(action)) {
                BluetoothDevice device = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
                // añadir la informacion del dispositivo al array
                ArrayAdapter.add(device.getName() + "\n" + device.getAddress());
            }
        }
    };
    IntentFilter filter = new IntentFilter(BluetoothDevice.ACTION_FOUND);
   // registerReceiver(dispositivoEncontrado, filter);

    public class  broadCast extends BroadcastReceiver { //broadcast para verificar cambios de estado en el bluetooth
        @Override
        public void onReceive(Context context, Intent intent) {
            String accion = intent.getAction();
            registrarEventosBluetooth();

            if(BluetoothAdapter.ACTION_STATE_CHANGED.equals(accion)){
                //accede a informaccion del intent del broadcast
                int estado = intent.getIntExtra(BluetoothAdapter.EXTRA_STATE, BluetoothAdapter.ERROR);

                switch(estado){

                    case BluetoothAdapter.STATE_OFF:
                        Toast.makeText(getApplicationContext(),R.string.bluetooth_desconectado, Toast.LENGTH_SHORT).show();
                        Intent i = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
                        startActivityForResult(i,REQUEST_ENABLE_BT);

                        break;
                }
            }
        }
    };

    public void registrarEventosBluetooth(){ //registrar para eventos del broadcastReciver

        IntentFilter filtro = new IntentFilter(BluetoothAdapter.ACTION_STATE_CHANGED);
        this.registerReceiver(new broadCast(), filtro);
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.imageView:
                Intent intentAlarm = new Intent(getApplicationContext(), AlarmActivity.class);
                startActivity(intentAlarm);
                break;
            case R.id.imageView2:
                Intent intentSocial = new Intent(getApplicationContext(),SocialActivity.class);
                startActivity(intentSocial);
                break;
            case R.id.imageViewFoco:
                boolean bandera=false;
                if(!bandera){
                    //primera vez, esta apagado y pasará a encendido

                    bandera=true;
                }
                else{
                    //esta encendida, se busca apagar


                    bandera = false;
                }
        }
    }
}
