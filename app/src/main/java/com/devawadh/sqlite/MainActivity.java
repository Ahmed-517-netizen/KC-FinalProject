package com.devawadh.sqlite;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
   TextView txtname,txtemail,txtphone;
   Button bsave,bview;
   DBHelper db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txtname=findViewById(R.id.edtname);
        txtemail=findViewById(R.id.email);
        txtphone=findViewById(R.id.phone);
        bsave=findViewById(R.id.btnsave);
        bview=findViewById(R.id.btnview);
        db=new DBHelper(this);
        bsave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean insertstatus=db.insertdata(txtname.getText().toString(),txtemail.getText().toString(),txtphone.getText().toString());
                if(insertstatus==true){
                    Toast.makeText(MainActivity.this, "تم حفظ البيانات", Toast.LENGTH_SHORT).show();
                }else{

                    Toast.makeText(MainActivity.this, "خطاء فنى", Toast.LENGTH_SHORT).show();

                }
            }
        });
        bview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cursor res=db.getdata();
                if(res.getCount()==0){

                    Toast.makeText(MainActivity.this, "لا توجد بيانات", Toast.LENGTH_SHORT).show();
                return;
                }
                StringBuffer buffer=new StringBuffer();
                while (res.moveToNext()){
                    buffer.append("Name :"+res.getString(0)+"\n");
                    buffer.append("Email :"+res.getString(1)+"\n");
                    buffer.append("Phone :"+res.getString(2)+"\n\n");

                }
                AlertDialog.Builder builder=new AlertDialog.Builder(MainActivity.this);
                builder.setCancelable(true);
                builder.setTitle("بيانات المستخدمين");
                builder.setMessage(buffer.toString());
                builder.show();
            }
        });
    }
}