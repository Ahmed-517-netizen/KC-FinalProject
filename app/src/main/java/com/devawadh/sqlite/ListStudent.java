package com.devawadh.sqlite;

import androidx.appcompat.app.AppCompatActivity;
import java.sql.Connection;
import java.sql.DriverManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;
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
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_student);
        lstData=findViewById(R.id.lstData);
        txtData=findViewById(R.id.txtData);
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
                ResultSet rs = st.executeQuery("select distinct StudentName from students_ahmed");
                ResultSetMetaData rsmd = rs.getMetaData();

                List<Map<String, String>> data = null;
                data = new ArrayList<Map<String, String>>();

                while (rs.next()) {
                    Map<String, String> datanum = new HashMap<String, String>();
                    datanum.put("A", rs.getString(1).toString());
                    data.add(datanum);
                }

                String[] fromwhere = { "A" };
                int[] viewswhere = { R.id.lblstudenname };
                ADAhere = new SimpleAdapter(ListStudent.this, data,
                        R.layout.student, fromwhere, viewswhere);

                while (rs.next()) {
                    result += rs.getString(1).toString() + "\n";
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