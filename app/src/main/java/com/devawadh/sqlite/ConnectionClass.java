package com.devawadh.sqlite;

import android.annotation.SuppressLint;
import android.os.StrictMode;
import android.util.Log;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionClass {
    String classs = "com.mysql.jdbc.Driver";

    String url = "jdbc:mysql:MYSQL5034.site4now.net/db_a440be_mixcust";
    String un = "a440be_mixcust";
    String password = "A@340036db";
    @SuppressLint("NewApi")
    public Connection CONN() {
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
                .permitAll().build();
        StrictMode.setThreadPolicy(policy);
        Connection conn = null;
        String ConnURL = null;
        try {

//            Class.forName(classs);
            Class.forName("com.mysql.jdbc.Driver");
            String url2 = "jdbc:mysql://MYSQL5034.site4now.net/db_a440be_mixcust?user=a440be_mixcust&password=A@340036db";
//                Connection connection = DriverManager.getConnection("jdbc:mysql:MYSQL5034.site4now.net/db_a440be_mixcust", "a440be_mixcust", "A@340036db");

            //Connection connection = DriverManager.getConnection(url2);
            conn = DriverManager.getConnection(url2);


            //conn = DriverManager.getConnection(ConnURL);
        } catch (SQLException se) {
            Log.e("ERRO", se.getMessage());
        } catch (ClassNotFoundException e) {
            Log.e("ERRO", e.getMessage());
        } catch (Exception e) {
            Log.e("ERRO", e.getMessage());
        }
        return conn;
    }

}
