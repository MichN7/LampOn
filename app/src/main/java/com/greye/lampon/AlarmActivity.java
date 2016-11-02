package com.greye.lampon;

import android.app.Activity;
import android.os.Bundle;
import android.content.Intent;
import android.widget.Button;
import android.widget.ImageView;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class AlarmActivity extends Activity implements View.OnClickListener {


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alarm);

        FloatingActionButton mas = (FloatingActionButton) findViewById(R.id.floatingActionButton);
        mas.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v){
                Hour();
            }
        });

    }

    @Override
    public void onClick(View v) {
    }

    public void Hour(){
        Intent hour = new Intent(AlarmActivity.this, HourActivity2.class);
        startActivity(hour);
    }
}
