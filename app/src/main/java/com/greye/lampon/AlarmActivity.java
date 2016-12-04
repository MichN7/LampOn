package com.greye.lampon;


import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

public class AlarmActivity extends ActionBarActivity {

    Button btnAgregarMiembro;
    ListView lista;
    SQLControlador dbconeccion;
    TextView tv_miemID, tv_miemNombre;
    long member_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alarm);

        dbconeccion = new SQLControlador(this);
        dbconeccion.abrirBaseDeDatos();
        btnAgregarMiembro = (Button) findViewById(R.id.btnAgregarMiembro);
        lista = (ListView) findViewById(R.id.listViewMiembros);

        //acción del boton agregar miembro
        btnAgregarMiembro.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                // Check if we're running on Android 5.0 or higher
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {

                } else {
                    Intent iagregar = new Intent(AlarmActivity.this, HourActivity2.class);
                    startActivity(iagregar);
                }

            }
        });


        // Tomar los datos desde la base de datos para poner en el curso y después en el adapter
        Cursor cursor = dbconeccion.leerDatos();

        String[] from = new String[] {
                DBhelper.ALARM_ID,
                DBhelper.ALARM_TITULO,
                DBhelper.ALARM_HORA,
                DBhelper.ALARM_DIA
        };
        int[] to = new int[] {
                R.id.txtID,
                R.id.txtTitulo,
                R.id.txtHora,
                R.id.txtDia
        };

        SimpleCursorAdapter adapter = new SimpleCursorAdapter(
               AlarmActivity.this, R.layout.item_list, cursor, from, to);

        adapter.notifyDataSetChanged();
        lista.setAdapter(adapter);

        // acción cuando hacemos click en item para poder modificarlo o eliminarlo
        lista.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView parent, View view, int position, long id) {
                tv_miemID = (TextView) view.findViewById(R.id.txtID);
                String aux_miembroId = tv_miemID.getText().toString();
                member_id = Long.parseLong(aux_miembroId);



                LayoutInflater layoutInflater = LayoutInflater.from(AlarmActivity.this);

                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(AlarmActivity.this);
                alertDialogBuilder
                        .setTitle("Alarma")
                        .setMessage("Borrar Alarma?")
                        .setCancelable(false)
                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dbconeccion.deleteData(member_id);

                                Intent i = new Intent(AlarmActivity.this, AlarmActivity.class );
                                startActivity(i);
                            }
                        })
                        .setNegativeButton("Cancel",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {
                                        dialog.cancel();
                                    }
                                });

                AlertDialog alertD = alertDialogBuilder.create();
                alertD.show();
            }

        });
    }



} //

