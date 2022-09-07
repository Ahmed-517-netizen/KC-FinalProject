package com.devawadh.sqlite;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class StudentChoose extends AppCompatActivity {
Button bquran,beval;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_choose);
        bquran=findViewById(R.id.btnQuran);
        bquran.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intr1=new Intent(StudentChoose.this,QuranList.class);
                startActivity(intr1);
            }
        });
        /*beval.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {*/
               /* Intent intr1=new Intent(StudentChoose.this,Myevaluation.class);
                startActivity(intr1);*/

            }}
      /*  });
    }
}*/