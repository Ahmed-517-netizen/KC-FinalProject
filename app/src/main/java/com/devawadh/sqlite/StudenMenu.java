package com.devawadh.sqlite;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class StudenMenu extends AppCompatActivity {
    Button bregistersstudent,beval,bback;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_studen_menu);
        bregistersstudent=findViewById(R.id.btnnewstudent);
        beval=findViewById(R.id.btnevalstudent);

        bregistersstudent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intr=new Intent(StudenMenu.this,ListStudent.class);
                startActivity(intr);
            }
        });
        beval.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intr=new Intent(StudenMenu.this,AllEvaluations.class);
                startActivity(intr);
            }
        });
    }
}