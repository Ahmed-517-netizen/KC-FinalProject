package com.devawadh.sqlite;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;

public class Selectsura extends AppCompatActivity {
    private Integer[] mImageIds = {
            R.drawable.fatiha,
            R.drawable.falg,
            R.drawable.nas,

    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selectsura);
      ImageView imgview=findViewById(R.id.selectimg);
      Bundle bd=getIntent().getExtras();

      int position=0;
        position=bd.getInt("position");
        imgview.setImageResource(mImageIds[position]);
    }
}