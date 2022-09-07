package com.devawadh.sqlite;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

public class QuranList extends AppCompatActivity {
    ImageView selectedImage;
    private Integer[] mImageIds = {
            R.drawable.a,
            R.drawable.b,
            R.drawable.c,
            R.drawable.d,
            R.drawable.e,
            R.drawable.f,
            R.drawable.g,

    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quran_list);
        ListView gallery = (ListView) findViewById(R.id.gallery1);
        gallery.setAdapter(new GalleryImageAdapter(this,R.layout.img));
        gallery.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
                Intent intr1=new Intent(QuranList.this,Selectsura.class);
                intr1.putExtra("position",position);
                startActivity(intr1);


                //Toast.makeText(QuranList.this, "Your selected position = " + position, Toast.LENGTH_SHORT).show();

            }
        });
    }

    class GalleryImageAdapter extends BaseAdapter
    {
        private Context mContext;

        int layout ;

        public GalleryImageAdapter(Context context, int layout)
        {
            mContext = context;
            this.layout = layout;
        }

        public int getCount() {
            return mImageIds.length;
        }

        public Object getItem(int position) {
            return position;
        }

        public long getItemId(int position) {
            return position;
        }


        // Override this method according to your need
        public View getView(int index, View view, ViewGroup viewGroup)
        {
            // TODO Auto-generated method stub

            LayoutInflater inflater = (LayoutInflater) mContext
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            View rowView = inflater.inflate(layout, viewGroup, false);

            ImageView imageView = (ImageView) rowView.findViewById(R.id.img);
            imageView.setImageResource(mImageIds[index]);


            return rowView;

        }
    }
}