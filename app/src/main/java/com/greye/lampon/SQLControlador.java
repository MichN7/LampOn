package com.greye.lampon;

/**
 * Created by emmanuelgarcia on 27/11/16.
 */

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

public class SQLControlador {

    private DBhelper dbhelper;
    private Context ourcontext;
    private SQLiteDatabase database;

    public SQLControlador(Context c) {
        ourcontext = c;
    }

    public SQLControlador abrirBaseDeDatos() throws SQLException {
        dbhelper = new DBhelper(ourcontext);
        database = dbhelper.getWritableDatabase();
        return this;
    }

    public void cerrar() {
        dbhelper.close();
    }

    public void insertarDatos(String Titulo,String Hora,String Dia) {
        ContentValues cv = new ContentValues();
        cv.put(DBhelper.ALARM_TITULO, Titulo);
        cv.put(DBhelper.ALARM_HORA,Hora );
        cv.put(DBhelper.ALARM_DIA, Dia);
        database.insert(DBhelper.TABLE_ALARM, null, cv);
    }

    public Cursor leerDatos() {
        String[] todasLasColumnas = new String[] {
                DBhelper.ALARM_ID,
                DBhelper.ALARM_TITULO,
                DBhelper.ALARM_HORA,
                DBhelper.ALARM_DIA
        };
        Cursor c = database.query(DBhelper.TABLE_ALARM, todasLasColumnas, null,
                null, null, null, null);
        if (c != null) {
            c.moveToFirst();
        }
        return c;
    }
//Queda pendiente de Actualizar estos metodos para esta app
    public int actualizarDatos(long AlarmID, String Titulo) {
        ContentValues cvActualizar = new ContentValues();
        cvActualizar.put(DBhelper.ALARM_TITULO, Titulo);
        int i = database.update(DBhelper.TABLE_ALARM, cvActualizar,
                DBhelper.ALARM_ID + " = " + AlarmID, null);
        return i;
    }

    public void deleteData(long AlarmID) {
        database.delete(DBhelper.TABLE_ALARM, DBhelper.ALARM_ID + "="
                + AlarmID, null);
    }
}