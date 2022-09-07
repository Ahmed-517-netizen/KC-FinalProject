package com.devawadh.sqlite;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

public class Selectsura extends AppCompatActivity {
    private Integer[] mImageIds = {
            R.drawable.fatiha,
            R.drawable.nas,
           R.drawable.falg,
           R.drawable.aglas,
          R.drawable.msd,
           R.drawable.nsr,
           R.drawable.kafron,

    };
    Button bplay,bstop;
    MediaPlayer mp;
    int position=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.activity_selectsura);
      ImageView imgview=findViewById(R.id.selectimg);
      Bundle bd=getIntent().getExtras();



        position=bd.getInt("position");
        imgview.setImageResource(mImageIds[position]);

        bplay=findViewById(R.id.button);
        bstop=findViewById(R.id.buttonstop);
        bstop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mp.stop();
            }
        });
        bplay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                try{
                    Toast.makeText(Selectsura.this, "Your selected position = " + position, Toast.LENGTH_SHORT).show();

if(position==0){
    mp= MediaPlayer.create(Selectsura.this, R.raw.fatha);
    mp.start();
}
else if(position==1){
    mp= MediaPlayer.create(Selectsura.this, R.raw.nas);
    mp.start();
}
else if(position==2){
    mp= MediaPlayer.create(Selectsura.this, R.raw.flg);
    mp.start();

}else if(position==3){
    mp= MediaPlayer.create(Selectsura.this, R.raw.aglas);
    mp.start();

}else if(position==4){
    mp= MediaPlayer.create(Selectsura.this, R.raw.msd);
    mp.start();

}else if(position==5){
    mp= MediaPlayer.create(Selectsura.this, R.raw.nsr);
    mp.start();

}else if(position==6){
    mp= MediaPlayer.create(Selectsura.this, R.raw.kafron);
    mp.start();

                    /*mp.setDataSource("/sdcard/Music/maine.mp3");//Write your location here
                    mp.prepare();
                    mp.start();*/
     }
 {

}

                }
  catch(Exception e){e.printStackTrace();}

            }
        });
    }
}