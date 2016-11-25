package com.greye.lampon;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Handler;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.IOException;
import java.io.OutputStream;
import java.util.Set;
import java.util.UUID;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {


    boolean bandera=false;
    ImageView socialImage;
    ImageView foco;
    private static final String TAG = "Bluetooth";
    private static final int REQUEST_ENABLE_BT = 1;
    private BluetoothAdapter btAdapter = null;
    private BluetoothSocket btSocket = null;
    private OutputStream outStream = null;
    private static final UUID MY_UUID =
            UUID.fromString("00001101-0000-1000-8000-00805F9B34FB");
    private static String address = "98:D3:31:90:8F:3B";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        BluetoothAdapter mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        LocalBroadcastManager.getInstance(this).registerReceiver(onNotice, new IntentFilter("Msg"));
        socialImage = (ImageView) findViewById(R.id.imageView2);
        ImageView imageView = (ImageView) findViewById(R.id.imageView);
        ImageView imageView2 = (ImageView) findViewById(R.id.imageView2);
        foco=(ImageView)findViewById(R.id.imageViewFoco);

        imageView.setOnClickListener(this);
        imageView2.setOnClickListener(this);
        foco.setOnClickListener(this);

        btAdapter = BluetoothAdapter.getDefaultAdapter();
        checkBTState();

    }




    public void onResume(){
        super.onResume();
        Log.d(TAG, "...In onResume ...");
        BluetoothDevice device = btAdapter.getRemoteDevice(address);
        try {
            btSocket = device.createRfcommSocketToServiceRecord(MY_UUID);
        }   catch (IOException e) {
            errorExit("Fatal Error", "In onResume() falló creacion del socket " + e.getMessage() + ".");
        }
        btAdapter.cancelDiscovery();

        Log.d(TAG, "...Connecting to Remote...");
        try{
            btSocket.connect();
            Log.d(TAG, "...concetando socket..");
        } catch (IOException e) {
            try{
                btSocket.close();
            } catch (IOException e2) {
                errorExit("Fatal Error", " onResume() fallo conexion de sokcet" + e2.getMessage() + ".");
            }
        }
        Log.d(TAG, "...Creating Socket...");

        try {
            outStream = btSocket.getOutputStream();
        } catch (IOException e) {
            errorExit("Fatal Error", "In onResume() fallo en la ceracion de outputStream: " + e.getMessage() + ".");
        }

    }


    public void onPause()
    {
        super.onPause();

        Log.d(TAG, "...In onPause()...");

        if (outStream != null) {
            try{
                outStream.flush();
            } catch (IOException e) {
                errorExit("Fatal Error", "In onPause() falló actualizando outStream " + e.getMessage() + ".");
            }
        }

        try{
            btSocket.close();
        } catch (IOException e2) {
            errorExit("Fatal Error","In onPause() fallo al cerrar el socket." + e2.getMessage() + ".");
        }

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
                if(!bandera){
                    //primera vez, esta apagado y pasará a encendido
                    sendData("1");
                    Toast.makeText(getBaseContext(), "Encender el LED", Toast.LENGTH_SHORT).show();
                    bandera=true;
                }
                else{
                    //esta encendida, se busca apagar
                    sendData("0");
                    Toast.makeText(getBaseContext(), "Apagar el LED", Toast.LENGTH_SHORT).show();
                    bandera = false;
                }
        }
    }
    private void checkBTState() {
        //check for Bluetooth support and then check to make sure it is turned on

        //Emulator doesn't support Bluetooth and will return null
        if(btAdapter==null) {
            errorExit("Fatal Error", "Bluetooth no soportado. Aborting.");
        } else {
            if (btAdapter.isEnabled()) {
                Log.d(TAG, "...Bluetooth esta habilitado...");
            } else {
                //Prompt user to turn on Bluetooth
                Intent enableBtIntent = new Intent(btAdapter.ACTION_REQUEST_ENABLE);
                startActivityForResult(enableBtIntent, REQUEST_ENABLE_BT);
            }
        }
    }

    private void errorExit(String title, String message) {
        Toast msg = Toast.makeText(getBaseContext(),
                title + " - " + message, Toast.LENGTH_SHORT);
        msg.show();
        finish();
    }

    public void sendData(String message) {
        byte[] msgBuffer = message.getBytes();

        Log.d(TAG, "...enviando dato: " + message + "...");

        try {
            outStream.write(msgBuffer);
            Log.d(TAG, "se envio con exito: " + message + "...");
        } catch (IOException e) {
            String msg = "In onResume() ocurrio una excepcion al escribir dato " + e.getMessage();
            if (address.equals("00:00:00:00:00:00"))
                msg = msg + ".\n\nfallo direccion del dispositivo esclavo";
            msg = msg + ".\n\nCheck that the SPP UUID: " + MY_UUID.toString() + "exists on server.\n\n";

            errorExit("Fatal Error", msg);
        }
    }

    private BroadcastReceiver onNotice= new BroadcastReceiver() {

        @Override
        public void onReceive(Context context, Intent intent) {
            String pack = intent.getStringExtra("package");
            sendData("2");
            Toast.makeText(getBaseContext(), "llego al onNotice :package = " + pack, Toast.LENGTH_SHORT).show();

        }
    };
}