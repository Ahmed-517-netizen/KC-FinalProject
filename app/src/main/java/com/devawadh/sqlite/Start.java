package com.devawadh.sqlite;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Start extends AppCompatActivity {
    Button bsstudent,bteatcher,bback;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        bsstudent=findViewById(R.id.btnstudent);
        bsstudent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intr1=new Intent(Start.this,StudentChoose.class);
                startActivity(intr1);
            }
        });
        bteatcher=findViewById(R.id.btnteatcher);
        bteatcher.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intr=new Intent(Start.this,StudenMenu.class);
                startActivity(intr);
            }
        });
    }
}