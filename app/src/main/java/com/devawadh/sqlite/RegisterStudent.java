package com.devawadh.sqlite;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class RegisterStudent extends AppCompatActivity {
    private Spinner spinner,spinner1;
    private static final String[] Studentclass = {"الصف الاول", "الصف الثاني", "الصف الثالث", "الصف الرابع", "الصف الخامس", "الصف السادس", "الصف السابع", "الصف الثامن", "الصف التاسع", "الصف العاشر", "الصف الحادي عشر", "الصف الثاني عشر"};
    private static final String[] Studentnational = {"كويتي", "هندي", "مصري", "يمني", "اردني", "غير محدد الجنسية", "سوداني", "غير موجود"};
    ProgressDialog progressDialog;
    Button bsave,btnconnect;
     ConnectionClass connectionClass;
     TextView errorText,text;
     EditText studenname,studenage,studenclass,studennation;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_student);
        studenname=findViewById(R.id.TxtStudentName);
        studenage=findViewById(R.id.TxtAge);
        bsave=findViewById(R.id.btnsave);
        spinner = (Spinner)findViewById(R.id.spinner);
        spinner1 = (Spinner)findViewById(R.id.spinnernatio);
        btnconnect=findViewById(R.id.btnback);
        errorText=findViewById(R.id.txterro);
        text=findViewById(R.id.txtdata);
        connectionClass=new ConnectionClass();
        progressDialog=new ProgressDialog(this);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(RegisterStudent.this,
                android.R.layout.simple_spinner_item,Studentclass);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(RegisterStudent.this,
                android.R.layout.simple_spinner_item,Studentnational);
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner1.setAdapter(adapter1);
        btnconnect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Async().execute();
            }
        });
        bsave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Doregister doregister = new Doregister();
                doregister.execute("");
//                Toast.makeText(RegisterStudent.this, valToSet, Toast.LENGTH_SHORT).show();
            }
        });
//        spinner.setOnItemSelectedListener(this);

    }
    class Async extends AsyncTask<Void, Void, Void> {



        String records = "",error="";

        @Override

        protected Void doInBackground(Void... voids) {

            try

            {

                Class.forName("com.mysql.jdbc.Driver");
                String url2 = "jdbc:mysql://MYSQL5034.site4now.net/db_a440be_mixcust?user=a440be_mixcust&password=A@340036db";
//                Connection connection = DriverManager.getConnection("jdbc:mysql:MYSQL5034.site4now.net/db_a440be_mixcust", "a440be_mixcust", "A@340036db");
                Connection connection = DriverManager.getConnection(url2);
                Statement statement = connection.createStatement();

                ResultSet resultSet = statement.executeQuery("SELECT * FROM students_ahmed");

                while(resultSet.next()) {

                    records += resultSet.getString(1) + " " + resultSet.getString(2) + "\n";

                }

            }

            catch(Exception e)

            {

                error = e.toString();

            }

            return null;

        }



        @Override

        protected void onPostExecute(Void aVoid) {

            text.setText(records);

            if(error != "")

                errorText.setText(error);

            super.onPostExecute(aVoid);

        }





    }
    public class Doregister extends AsyncTask<String,String,String>
    {


        String valclass = spinner.getSelectedItem().toString();
        String valnation = spinner1.getSelectedItem().toString();
        String stdname=studenname.getText().toString();
        String stdage=studenage.getText().toString();
        String z="";
        boolean isSuccess=false;

        @Override
        protected void onPreExecute() {
            progressDialog.setMessage("Loading...");
            progressDialog.show();
        }

        @Override
        protected String doInBackground(String... params) {

            if(valclass.trim().equals("")|| valnation.trim().equals("") ||stdname.trim().equals("")||stdage.trim().equals(""))
                z = "الرجاء مل كافة البيانات";
            else
            {
                try {
                    Connection con = connectionClass.CONN();
                    if (con == null) {
                        z = "Please check your internet connection";
                    } else {

                        String query="insert into students_ahmed(StudentName,Age,Class,Nationlity) VALUES('"+stdname+"',"+stdage+",'"+valclass+"','"+valnation+"')";

                        Statement stmt = con.createStatement();
                        stmt.executeUpdate(query);

                        z = "Register successfull";
                        isSuccess=true;


                    }
                }
                catch (Exception ex)
                {
                    isSuccess = false;
                    z = "Exceptions"+ex;

                }
            }
            return z;
        }

        @Override
        protected void onPostExecute(String s) {

            Toast.makeText(getBaseContext(),""+z,Toast.LENGTH_LONG).show();


            if(isSuccess) {
                startActivity(new Intent(RegisterStudent.this,ListStudent.class));

            }


            progressDialog.hide();
        }
    }
}