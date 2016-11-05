package com.greye.lampon;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.view.View;
import android.widget.Button;

public class HourActivity2 extends AppCompatActivity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hour2);

        Button Repetir = (Button) findViewById(R.id.btnRepetir);
        Repetir.setOnClickListener(this);

        Button Aplazar = (Button) findViewById(R.id.btnAplazar);
        Aplazar.setOnClickListener(this);

        Button Cancelar = (Button) findViewById(R.id.btnCancelar);
        Cancelar.setOnClickListener(this);

        Button Guardar = (Button) findViewById(R.id.btnGuardar);
        Guardar.setOnClickListener(this);
    }
    FragmentManager fm = getSupportFragmentManager();
    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btnRepetir){
            try {

                DFragment dFragment = new DFragment();
                // Show DialogFragment
                dFragment.show(fm, "Dialog Fragment");
            }
            catch (Exception e){
                Log.e(null,"error");
            }
        }
        if (v.getId() == R.id.btnAplazar){
            try {
                DFragmentDesplazar dFragmentDs = new DFragmentDesplazar();
                // Show DialogFragment
                dFragmentDs.show(fm, "Dialog Fragment");
            }
            catch (Exception e){
                Log.e(null,"error");
            }
        }
        if (v.getId() == R.id.btnCancelar){
            finish();
        }
        if (v.getId() == R.id.btnGuardar){
            //aqui se llama a la base de datos
        }
    }
}
