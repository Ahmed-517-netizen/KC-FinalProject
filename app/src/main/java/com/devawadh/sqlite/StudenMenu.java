package com.devawadh.sqlite;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class StudenMenu extends AppCompatActivity {
    Button bregistersstudent,bteatcher,bback;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_studen_menu);
        bregistersstudent=findViewById(R.id.btnnewstudent);

        bregistersstudent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intr=new Intent(StudenMenu.this,RegisterStudent.class);
                startActivity(intr);
            }
        });
    }
}