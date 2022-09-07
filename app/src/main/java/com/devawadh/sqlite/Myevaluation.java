package com.devawadh.sqlite;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Myevaluation extends AppCompatActivity {
    ArrayList<String> StudentList = new ArrayList<>();
    ArrayAdapter<String> StudentAdapter;
    private Spinner spinner,splistSoura,listdegree;
    TextView dateTimeDisplay;
    private Calendar calendar;
    Button bsave;
    CalendarView calendarView;
    ProgressDialog progressDialog;
    private SimpleDateFormat dateFormat;
    private String date;
    private static final String[] Souralist = {"الفاتحة", "الناس", "الفلق", "الاخلاص", "المسد", "النصر", "الكافرون"};
    private static final String[] gradelist = {"مقبول","جيد", "جيد جدا", "ممتاز"};
EditText txtayafrom,txtayato;
    ConnectionClass connectionClass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_myevaluation);
        spinner = (Spinner)findViewById(R.id.spinnerstudent);
        txtayafrom=findViewById(R.id.editayafrom);
        txtayato=findViewById(R.id.editayato);
        splistSoura = (Spinner)findViewById(R.id.txtSouralist);
        bsave=findViewById(R.id.buttonsave);
        connectionClass=new ConnectionClass();
        progressDialog=new ProgressDialog(this);
        listdegree=(Spinner)findViewById(R.id.listgrade);
        ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(Myevaluation.this,
                R.layout.spinner_list,gradelist);
        adapter1.setDropDownViewResource(R.layout.spinner_list);
        listdegree.setAdapter(adapter1);



        ArrayAdapter<String> adapter = new ArrayAdapter<String>(Myevaluation.this,
                 R.layout.spinner_list,Souralist);
        adapter.setDropDownViewResource(R.layout.spinner_list);
        splistSoura.setAdapter(adapter);
        dateTimeDisplay = (TextView)findViewById(R.id.txtdate);
        calendarView=(CalendarView)findViewById(R.id.calendarView2);
        calendarView.setVisibility(View.GONE);
        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
                final SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
                Calendar calendar = Calendar.getInstance();
                calendar.set(year, month, dayOfMonth);
                String sDate = sdf.format(calendar.getTime());
                dateTimeDisplay.setText(sDate);
              //  dateTimeDisplay.setText(String.valueOf(year)+"/"+String.valueOf(month+1)+"/"+String.valueOf(dayOfMonth));
                calendarView.setVisibility(View.GONE);
            }
        });
        dateTimeDisplay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calendarView.setVisibility(calendarView.isShown()?View.GONE:View.VISIBLE);
            }
        });

        bsave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String strsudent = spinner.getSelectedItem().toString();
                String stdnamedate=dateTimeDisplay.getText().toString();
                String valSoura = splistSoura.getSelectedItem().toString();


                String strayafrom=txtayafrom.getText().toString();
                String strayato=txtayato.getText().toString();
                String strdegree = listdegree.getSelectedItem().toString();

                try {
                    if(strsudent==""||stdnamedate==""||valSoura==""||strayafrom==""||strayafrom=="0"||strayato==""||strayato=="0"||strdegree==""){

                        Toast.makeText(Myevaluation.this,"الرجاء إدخال البيانات", Toast.LENGTH_LONG)
                                .show();
                        return;
                    }
                    if(Integer.parseInt(strayafrom)>Integer.parseInt(strayato)){

                        Toast.makeText(Myevaluation.this,"الاية من اكبر من الاية الى", Toast.LENGTH_LONG)
                                .show();
                        return;
                    }

                    Myevaluation.Doregister doregister = new Myevaluation.Doregister();
                    doregister.execute("");
                }
                catch (Exception e){

                    Toast.makeText(Myevaluation.this,"e: "+e.getMessage(), Toast.LENGTH_LONG)
                            .show();
                }


            }
        });
        /*calendar = Calendar.getInstance();
        dateFormat = new SimpleDateFormat("MM/dd/yyyy");
        date = dateFormat.format(calendar.getTime());
        dateTimeDisplay.setText(date);*/
        Myevaluation.LoadStudent connectMySql = new Myevaluation.LoadStudent();
        connectMySql.execute("");



    }


    public class Doregister extends AsyncTask<String,String,String>
    {


        String strsudent = spinner.getSelectedItem().toString();
        String stdnamedate=dateTimeDisplay.getText().toString();
        String valSoura = splistSoura.getSelectedItem().toString();


        String strayafrom=txtayafrom.getText().toString();
        String strayato=txtayato.getText().toString();
        String strdegree = listdegree.getSelectedItem().toString();
        String z="";
        boolean isSuccess=false;

        @Override
        protected void onPreExecute() {
            progressDialog.setMessage("Loading...");
            progressDialog.show();
        }

        @Override
        protected String doInBackground(String... params) {


                try {
                    Connection con = connectionClass.CONN();
                    if (con == null) {
                        z = "Please check your internet connection";
                    } else {

                        String query="insert into students_evalutions(StudentName,attendecedate,degree,fromAya,ToAya,AlSoura) VALUES('"+strsudent+"','"+stdnamedate+"','"+strdegree+"','"+strayafrom+"','"+strayato+"','"+valSoura+"')";

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

            return z;
        }

        @Override
        protected void onPostExecute(String s) {

            Toast.makeText(getBaseContext(),""+z,Toast.LENGTH_LONG).show();


            if(isSuccess) {
                startActivity(new Intent(Myevaluation.this,AllEvaluations.class));

            }


            progressDialog.hide();
        }
    }
    private class LoadStudent extends AsyncTask<String, Void, String> {
        String res = "";

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            Toast.makeText(Myevaluation.this, "جاري التحميل....", Toast.LENGTH_SHORT)
                    .show();

        }

        @Override
        protected String doInBackground(String... params) {
            try {
                Class.forName("com.mysql.jdbc.Driver");
                String url2 = "jdbc:mysql://MYSQL5034.site4now.net/db_a440be_mixcust?user=a440be_mixcust&password=A@340036db";


                Connection con = DriverManager.getConnection(url2);
                System.out.println("Databaseection success");

                String result = "Database Connection Successful\n";
                Statement st = con.createStatement();
                ResultSet rs = st.executeQuery("select distinct StudentName from students_ahmed");
                ResultSetMetaData rsmd = rs.getMetaData();



                while (rs.next()) {
                    StudentList.add(rs.getString(1).toString());
                    result += rs.getString(1).toString();
                    }


                res = result;
            } catch (Exception e) {
                e.printStackTrace();
                res = e.toString();
            }
            return res;
        }

        @Override
        protected void onPostExecute(String result) {
            StudentAdapter = new ArrayAdapter<>(Myevaluation.this,
                    R.layout.spinner_list, StudentList);
            StudentAdapter.setDropDownViewResource(R.layout.spinner_list);
            spinner.setAdapter(StudentAdapter);
        }
    }
}