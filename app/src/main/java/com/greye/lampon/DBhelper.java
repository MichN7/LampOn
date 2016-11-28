package com.greye.lampon;

/**
 * Created by emmanuelgarcia on 27/11/16.
 */

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBhelper extends SQLiteOpenHelper {

    // Información de la tabla
    public static final String TABLE_ALARM = "TB_Alarma";
    public static final String ALARM_ID = "_id";
    public static final String ALARM_TITULO = "Titulo";
    public static final String ALARM_HORA = "Hora";
    public static final String ALARM_DIA = "Dia";



    // información del a base de datos
    static final String DB_NAME = "DBALARM2";
    static final int DB_VERSION = 1;

    // Información de la base de datos
    private static final String CREATE_TABLE = "create table "
            + TABLE_ALARM + "(" + ALARM_ID
            + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + ALARM_TITULO + " TEXT NOT NULL,  "
            + ALARM_HORA + " TEXT NOT NULL,  "
            + ALARM_DIA + " TEXT NOT NULL);";

    public DBhelper(Context context) {
        super(context, DB_NAME, null,DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // TODO Auto-generated method stub
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_ALARM);
        onCreate(db);
    }
}