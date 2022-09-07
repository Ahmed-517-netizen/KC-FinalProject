package com.devawadh.sqlite;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.GridView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AllEvaluations extends AppCompatActivity {
Button bhome;
    FloatingActionButton badd;
GridView grdview;
SimpleAdapter ADAhere;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_evaluations);
        grdview=(GridView)findViewById(R.id.simpleGridView);
      badd=findViewById(R.id.btnAddeval);
        bhome=findViewById(R.id.buttonhome);
        bhome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AllEvaluations.this,Start.class));
            }
        });

          badd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AllEvaluations.this,Myevaluation.class));
            }
        });
        AllEvaluations.ConnectMySql connectMySql = new AllEvaluations.ConnectMySql();
        connectMySql.execute("");
    }
    private class ConnectMySql extends AsyncTask<String, Void, String> {
        String res = "";

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            Toast.makeText(AllEvaluations.this, "Please wait...", Toast.LENGTH_SHORT)
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
                ResultSet rs = st.executeQuery("select distinct StudentName,attendecedate,AlSoura,degree from students_evalutions");
                ResultSetMetaData rsmd = rs.getMetaData();

                List<Map<String, String>> data = null;
                data = new ArrayList<Map<String, String>>();

                while (rs.next()) {
                    Map<String, String> datanum = new HashMap<String, String>();
                    datanum.put("StudentName",rs.getString(1).toString());
                    datanum.put("attendecedate", rs.getString(2).toString());
                    datanum.put("AlSoura", rs.getString(3).toString());
                    datanum.put("degree", rs.getString(4).toString());
//                    datanum.put("natio"," الجنسية"+ rs.getString(4).toString());
                    data.add(datanum);
                }

//                String[] fromwhere = { "A","Age", "natio"};
                String[] fromwhere = { "StudentName","attendecedate","AlSoura","degree"};
                //int[] viewswhere = { R.id.lblstudenname,R.id.lblstudenage,R.id.lblstudennation };
                int[] viewswhere = {R.id.txtstudentname ,R.id.txtattendecedate,R.id.txtatsoura,R.id.txtdegree };

                ADAhere = new SimpleAdapter(AllEvaluations.this, data,
                        R.layout.gridviewlayout, fromwhere, viewswhere);

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
            //txtData.setText(result);
            grdview.setAdapter(ADAhere);
        }
    }
}