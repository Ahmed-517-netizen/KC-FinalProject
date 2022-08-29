package com.example.save;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {
    Button btn1;
    ImageView image;
    private final int images = 1000;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn1=findViewById(R.id.butgo);
        Button btnselectimg=findViewById(R.id.btngetimage);
        image=findViewById(R.id.imageView);
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent goo = new Intent(MainActivity.this, Sign.class);
                startActivity(goo);
            }
        });

        btnselectimg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent iimage= new Intent(Intent.ACTION_PICK);
                iimage.setData(MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(iimage, images);
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode==RESULT_OK){

            if (requestCode==images){

                image.setImageURI(data.getData());
            }
        }
    }
}