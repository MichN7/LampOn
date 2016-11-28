package com.greye.lampon;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;

public class AplazarActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aplazar);

        RadioButton rbtn = (RadioButton) findViewById(R.id.Rbtn);
        rbtn.setOnClickListener(this);

        RadioButton rbtn1 = (RadioButton) findViewById(R.id.Rbtn1);
        rbtn1.setOnClickListener(this);

        RadioButton rbtn2 = (RadioButton) findViewById(R.id.Rbtn2);
        rbtn2.setOnClickListener(this);

        RadioButton rbtn3 = (RadioButton) findViewById(R.id.Rbtn3);
        rbtn3.setOnClickListener(this);

        RadioButton rbtn4 = (RadioButton) findViewById(R.id.Rbtn4);
        rbtn4.setOnClickListener(this);

    }
    @Override
    public void onClick(View v){
        switch (v.getId()){
            case R.id.Rbtn:
                // aqui se van a guardar 3 min
                break;
            case R.id.Rbtn1:
                // aqui 5 min.
                break;
            case R.id.Rbtn2:
                // 10 min.
                break;
            case R.id.Rbtn3:
                // 15 min.
                break;
            case R.id.Rbtn4:
                // 30 min.
                break;
        }
    }
    public  void save(View v){
        //se manda a la base de datos
    }
}
