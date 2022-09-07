package com.devawadh.sqlite;

import androidx.appcompat.app.AppCompatActivity;
import java.sql.Connection;
import java.sql.DriverManager;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ListStudent extends AppCompatActivity {
    SimpleAdapter ADAhere;
    ListView lstData;
    TextView txtData;
    private static final int ADD_Student_REQUEST = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_student);
        lstData=findViewById(R.id.lstData);
        txtData=findViewById(R.id.txtData);

        FloatingActionButton fab = findViewById(R.id.btnAdd);
        lstData.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(ListStudent.this, "hooo", Toast.LENGTH_LONG)
                        .show();
                /*TextView textView = (TextView) lstData.findViewById(R.id.lblstudenname);
                String text = textView.getText().toString();
                Toast.makeText(ListStudent.this, "hooo", Toast.LENGTH_SHORT)
                        .show();*/
            }
        });
        //adding on click listner for floating action button.
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //starting a new activity for adding a new course and passing a constant value in it.
                Intent intent = new Intent(ListStudent.this, RegisterStudent.class);
                startActivityForResult(intent, ADD_Student_REQUEST);
            }
        });
        ConnectMySql connectMySql = new ConnectMySql();
        connectMySql.execute("");

    }
    private class ConnectMySql extends AsyncTask<String, Void, String> {
        String res = "";

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            Toast.makeText(ListStudent.this, "Please wait...", Toast.LENGTH_SHORT)
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
                ResultSet rs = st.executeQuery("select distinct studentid,StudentName,Age,Class,Nationlity from students_ahmed");
                ResultSetMetaData rsmd = rs.getMetaData();

                List<Map<String, String>> data = null;
                data = new ArrayList<Map<String, String>>();

                while (rs.next()) {
                    Map<String, String> datanum = new HashMap<String, String>();
                    datanum.put("id",rs.getString(1).toString());
                    datanum.put("A", " -- "+rs.getString(2).toString());
                    datanum.put("Age"," العمر"+ rs.getString(3).toString());
//                    datanum.put("natio"," الجنسية"+ rs.getString(4).toString());
                    data.add(datanum);
                }

//                String[] fromwhere = { "A","Age", "natio"};
                String[] fromwhere = { "id","A","Age"};
                //int[] viewswhere = { R.id.lblstudenname,R.id.lblstudenage,R.id.lblstudennation };
                int[] viewswhere = {R.id.lblid ,R.id.lblstudenname,R.id.lblstudenage };

                ADAhere = new SimpleAdapter(ListStudent.this, data,
                        R.layout.student, fromwhere, viewswhere);

                while (rs.next()) {
                    result += rs.getString(1).toString() +rs.getString(2).toString() + "\n";
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
            txtData.setText(result);
            lstData.setAdapter(ADAhere);
        }
    }

}