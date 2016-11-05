package com.greye.lampon;


import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
        import android.view.ViewGroup;

public class DFragment extends DialogFragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_repetir, container,
                false);
        getDialog().setTitle("Dia de la Semana");
        // Do something else
        return rootView;
    }
}