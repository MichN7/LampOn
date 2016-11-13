package com.greye.lampon;

import android.bluetooth.BluetoothSocket;
import android.os.Handler;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Created by Adán on 13/11/2016.
 */

public class hiloConexion extends Thread{
    Handler bluetoothIn;
    final int handlerState = 0;
    private InputStream mmInStream=null;
    private OutputStream mmOutStream=null;
    public hiloConexion(BluetoothSocket bSocket){
        InputStream tmpIn = null;
        OutputStream tmpOut = null;
        try {
            tmpIn = bSocket.getInputStream();
            tmpOut = bSocket.getOutputStream();
        } catch (IOException e) { }

        mmInStream = tmpIn;
        mmOutStream = tmpOut;
    }
    public void run() {
        byte[] buffer = new byte[256];
        int bytes;

        // espera mensajes
        while (true) {
            try {
                bytes = mmInStream.read(buffer);
                String readMessage = new String(buffer, 0, bytes);
                // envia lo obtenido mediante un handler
                bluetoothIn.obtainMessage(handlerState, bytes, -1, readMessage).sendToTarget();
            } catch (IOException e) {
                break;
            }
        }
    }
    public void write(String input) {
        byte[] msgBuffer = input.getBytes();           //converts entered String into bytes
        try {
            mmOutStream.write(msgBuffer);                //write bytes over BT connection via outstream
        } catch (IOException e) {



        }
    }

}
