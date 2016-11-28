package com.greye.lampon;


import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import static android.content.Context.ALARM_SERVICE;

public class DFragmentDesplazar extends DialogFragment{
    RadioGroup radio;
    int Aplazar;
    AlarmManager alarm_Manager;
    PendingIntent pending_intent;
    Intent mi_intent;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_d, container,
                false);
        getDialog().setTitle("Minutos");

        alarm_Manager = (AlarmManager)getActivity().getSystemService(ALARM_SERVICE);
        final Intent mi_intent = new Intent(getActivity(), Alarm_Receiver.class);
        radio = (RadioGroup) rootView.findViewById(R.id.RadioGroup);
        Button Desplazar = (Button) rootView.findViewById(R.id.btnGuardarDezplazar);

        final RadioButton Rbtn0 = (RadioButton) rootView.findViewById(R.id.Rbtn0);
        final RadioButton Rbtn = (RadioButton) rootView.findViewById(R.id.Rbtn);
        final RadioButton Rbtn1 = (RadioButton) rootView.findViewById(R.id.Rbtn1);
        final RadioButton Rbtn2 = (RadioButton) rootView.findViewById(R.id.Rbtn2);
        final RadioButton Rbtn3 = (RadioButton) rootView.findViewById(R.id.Rbtn3);
        final RadioButton Rbtn4 = (RadioButton) rootView.findViewById(R.id.Rbtn4);
        Log.e("Entro a Clase Dfragment","2");

        radio.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override

            public void onCheckedChanged(RadioGroup group, int checkedId) {

                if (checkedId == R.id.Rbtn) {

                    Log.e("Entro a Clase Dfragment","presiono radio button 1");

                    alarm_Manager.cancel(pending_intent);
                    mi_intent.putExtra("extra", "alarm_off");
                    getActivity().sendBroadcast(mi_intent);

                    Aplazar = 1;
                    mi_intent.putExtra("extra", "alarm_on");

                    pending_intent = PendingIntent.getBroadcast(getActivity(), 0, mi_intent, PendingIntent.FLAG_UPDATE_CURRENT);

                    alarm_Manager.set(AlarmManager.RTC_WAKEUP, System.currentTimeMillis() + Aplazar * 10000, pending_intent);

                } else if (checkedId == R.id.Rbtn0) {
                    alarm_Manager.cancel(pending_intent);
                    mi_intent.putExtra("extra", "alarm_off");
                    getActivity().sendBroadcast(mi_intent);

                    Aplazar = 3;
                    mi_intent.putExtra("extra", "alarm_on");

                    pending_intent = PendingIntent.getBroadcast(getActivity(), 0, mi_intent, PendingIntent.FLAG_UPDATE_CURRENT);

                    alarm_Manager.set(AlarmManager.RTC_WAKEUP, System.currentTimeMillis() + Aplazar * 10000, pending_intent);


                } else if (checkedId == R.id.Rbtn1) {
                    alarm_Manager.cancel(pending_intent);
                    mi_intent.putExtra("extra", "alarm_off");
                    getActivity().sendBroadcast(mi_intent);

                    Aplazar = 5;
                    mi_intent.putExtra("extra", "alarm_on");

                    pending_intent = PendingIntent.getBroadcast(getActivity(), 0, mi_intent, PendingIntent.FLAG_UPDATE_CURRENT);

                    alarm_Manager.set(AlarmManager.RTC_WAKEUP, System.currentTimeMillis() + Aplazar * 10000, pending_intent);


                } else if (checkedId == R.id.Rbtn2) {
                    alarm_Manager.cancel(pending_intent);
                    mi_intent.putExtra("extra", "alarm_off");
                    getActivity().sendBroadcast(mi_intent);

                    Aplazar = 10;
                    mi_intent.putExtra("extra", "alarm_on");

                    pending_intent = PendingIntent.getBroadcast(getActivity(), 0, mi_intent, PendingIntent.FLAG_UPDATE_CURRENT);

                    alarm_Manager.set(AlarmManager.RTC_WAKEUP, System.currentTimeMillis() + Aplazar * 10000, pending_intent);


                } else if (checkedId == R.id.Rbtn3) {
                    alarm_Manager.cancel(pending_intent);
                    mi_intent.putExtra("extra", "alarm_off");
                    getActivity().sendBroadcast(mi_intent);

                    Aplazar = 15;
                    mi_intent.putExtra("extra", "alarm_on");

                    pending_intent = PendingIntent.getBroadcast(getActivity(), 0, mi_intent, PendingIntent.FLAG_UPDATE_CURRENT);

                    alarm_Manager.set(AlarmManager.RTC_WAKEUP, System.currentTimeMillis() + Aplazar * 10000, pending_intent);


                } else if (checkedId == R.id.Rbtn4) {
                    alarm_Manager.cancel(pending_intent);
                    mi_intent.putExtra("extra", "alarm_off");
                    getActivity().sendBroadcast(mi_intent);

                    Aplazar = 30;
                    mi_intent.putExtra("extra", "alarm_on");

                    pending_intent = PendingIntent.getBroadcast(getActivity(), 0, mi_intent, PendingIntent.FLAG_UPDATE_CURRENT);

                    alarm_Manager.set(AlarmManager.RTC_WAKEUP, System.currentTimeMillis() + Aplazar * 10000, pending_intent);


                }
            }
        });


        Desplazar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



            }

        });
        // Do something else
        return rootView;
    }

}