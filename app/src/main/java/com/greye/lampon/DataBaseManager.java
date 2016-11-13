package com.greye.lampon;

/**
 * Created by emmanuelgarcia on 08/11/16.
 */

public class DataBaseManager {
    public static final String TABLE_DIAS = "Dias";
    public static final String Dias_ID = "id_Dia";
    public static final String Dias_Dias = "Dia";

    public static final String TABLE_ALARMA = "id_Alarma";
    public static final String Alarma_Titulo = "Titulo";
    public static final String Alarma_Hora = "Hora";
    public static final String Alarma_Aplazamiento ="Aplazamiento";
    public static final String Alarma_IdDias = "IdDias";


    public static final String CREATE_TABLE_DIAS = "create table " +TABLE_DIAS+"("
            +Dias_ID+" integer primary key autoincrement, "
            +Dias_Dias+" integer not null);";
    public static final String CREATE_TABLE_ALARMA = "create table "+TABLE_ALARMA+"("
            +Alarma_Titulo+" VARCHAR(40) not null, "
            +Alarma_Hora+" DATE not null, "
            +Alarma_Aplazamiento+" DATE not null, "
            +Alarma_IdDias+" integer not null, " +
            "FOREIGN KEY ("+Alarma_IdDias+") REFERENCES " +TABLE_DIAS+" ("+Dias_ID+")); ";

}
