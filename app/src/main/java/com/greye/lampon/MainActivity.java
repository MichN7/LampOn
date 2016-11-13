package com.greye.lampon;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.IOException;
import java.util.UUID;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {


    int REQUEST_ENABLE_BT;
    ImageView socialImage;
    private BluetoothAdapter btAdapter = null;
    private BluetoothSocket btSocket = null;
    String address=null;
    hiloConexion miConexion;
    private static final UUID BTMODULEUUID = UUID.fromString("00001101-0000-1000-8000-00805F9B34FB");
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        BluetoothAdapter mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();

        socialImage = (ImageView) findViewById(R.id.imageView2);
        ImageView imageView = (ImageView) findViewById(R.id.imageView);
        ImageView imageView2 = (ImageView) findViewById(R.id.imageView2);

        imageView.setOnClickListener(this);
        imageView2.setOnClickListener(this);
        btAdapter = BluetoothAdapter.getDefaultAdapter();

        //revisamos si esta cactivado bluetooth
        if (mBluetoothAdapter == null) {
            // no soporta bluetooth
        }
        if (!mBluetoothAdapter.isEnabled()) {
            Intent enableBtIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
            startActivityForResult(enableBtIntent, REQUEST_ENABLE_BT);
        }


    }
    private BluetoothSocket createBluetoothSocket(BluetoothDevice device) throws IOException {

        return  device.createRfcommSocketToServiceRecord(BTMODULEUUID);
        //creates secure outgoing connecetion with BT device using UUID
    }


    public void onResume(){
        super.onResume();
        address="98:D3:31:90:8F:3B";
        BluetoothDevice device = btAdapter.getRemoteDevice(address);
        try {
            btSocket = createBluetoothSocket(device);
        } catch (IOException e) {
            Toast.makeText(getBaseContext(), "La creacción del Socket fallo", Toast.LENGTH_LONG).show();
        }
        try
        {
            btSocket.connect();
        } catch (IOException e) {
            try
            {
                btSocket.close();
            }
            catch (IOException e2)
            {
            }
        }
        miConexion= new hiloConexion(btSocket);
        miConexion.start();


    }

    public void onPause()
    {
        super.onPause();
        try
        {
            btSocket.close();
        } catch (IOException e2) {
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
                boolean bandera=false;
                if(!bandera){
                    //primera vez, esta apagado y pasará a encendido
                    miConexion.write("1");
                    Toast.makeText(getBaseContext(), "Encender el LED", Toast.LENGTH_SHORT).show();
                    bandera=true;
                }
                else{
                    //esta encendida, se busca apagar
                    miConexion.write("2");
                    Toast.makeText(getBaseContext(), "Apagar el LED", Toast.LENGTH_SHORT).show();
                    bandera=true;

                    bandera = false;
                }
        }
    }
}