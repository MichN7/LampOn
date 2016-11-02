package com.greye.lampon;
import android.bluetooth.BluetoothSocket;
import android.os.Message;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.logging.Handler;
import java.util.logging.LogRecord;

/**
 * Created by Adán on 29/10/2016.
 */
public class bluetooth {
    public static final int ESTADO_NINGUNO = 0;
    public static final int ESTADO_CONECTADO = 1;
    public static final int ESTADO_REALIZANDO_CONEXION = 2;
    public static final int ESTADO_ATENDIENDO_PETICIONES = 3;
    public static final int MSG_LEER = 11;
    public static final int MSG_ESCRIBIR = 12;

    private BluetoothSocket socket;
    int estado;

    private class hiloConexion extends Thread { //conexiones de manera asincrona con interface gráfica
        private final BluetoothSocket socket;         // Socket
        private final InputStream inputStream = null;    // lecturas
        private final OutputStream outputStream = null;   // escrituras
        private Handler handler = new Handler() {
            @Override
            public void close() {
            }

            @Override
            public void flush() {
            }

            @Override
            public void publish(LogRecord record) {
            }
            public void obtainMessage(Message msg){

            }
        };

        public hiloConexion(BluetoothSocket socket) {

            this.socket = socket;
            setName(socket.getRemoteDevice().getName() + " [" + socket.getRemoteDevice().getAddress() + "]");

            InputStream tmpInputStream = null;
            OutputStream tmpOutputStream = null;
            try {
                tmpInputStream = socket.getInputStream();
                tmpOutputStream = socket.getOutputStream();
            } catch (IOException e) {
            }
        }

        public void run() {
            byte[] buffer = new byte[1024];
            int bytes;
            estado = ESTADO_CONECTADO;
            // Mientras se mantenga la conexion el hilo se mantiene en espera ocupada
            // leyendo del flujo de entrada
            while (true) {
                try {
                    // Leemos del flujo de entrada del socket
                    bytes = inputStream.read(buffer);

                    // Enviamos la informacion a la actividad a traves del handler.
                    // El metodo handleMessage sera el encargado de recibir el mensaje
                    // y mostrar los datos recibidos en el TextView

                  //  handler.obtainMessage(MSG_LEER, bytes, -1, buffer).sendToTarget();
                } catch (IOException e) {

                }
            }

        }
    }
}
