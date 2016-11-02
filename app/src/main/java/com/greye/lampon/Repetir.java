package com.greye.lampon;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;

import java.util.ArrayList;

public class Repetir extends AppCompatActivity {

    ArrayList<String> Dias = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_repetir);

    }

    public void semana(View v){
        Boolean checked = ((CheckBox) v).isChecked();
        switch (v.getId()){

            case R.id.checkBox:
                if(checked) {
                    Dias.add("Domingo");
                }
                else{
                    Dias.remove("Domingo");
                }
                break;
            case R.id.checkBox2:
                if(checked) {
                    Dias.add("Lunes");
                }
                else{
                    Dias.remove("Lunes");
                }
                break;
            case R.id.checkBox3:
                if(checked) {
                    Dias.add("Martes");
                }
                else{
                    Dias.remove("Martes");
                }
                break;
            case R.id.checkBox4:
                if(checked) {
                    Dias.add("Miércoles");
                }
                else{
                    Dias.remove("Miércoles");
                }
                break;
            case R.id.checkBox5:
                if(checked) {
                    Dias.add("Jueves");
                }
                else{
                    Dias.remove("Jueves");
                }
                break;
            case R.id.checkBox6:
                if(checked) {
                    Dias.add("Viernes");
                }
                else{
                    Dias.remove("Viernes");
                }
                break;
            case R.id.checkBox7:
                if(checked) {
                    Dias.add("Sabado");
                }
                else{
                    Dias.remove("Sabado");
                }
                break;
        }
    }
    public void save(View v){
        String Guardar_ch = "";

        for(String G : Dias){
            Guardar_ch += Guardar_ch + G +"\n";
        } // solo falta mandar el array a la base de datos
    }
}
