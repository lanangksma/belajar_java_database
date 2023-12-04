package com.lanangksma.biodatadb.db;

import java.sql.Connection;
import java.sql.DriverManager;

public class MySqlConnection {
    private final String DB_URL = "jdbc:mysql://localhost:3306/kuliah_pp2";
    private final String DB_USER = "root";
    private final String DB_PASSWORD = "";

    private static MySqlConnection instance;

    public static MySqlConnection getInstance() {
        if (instance == null) {
            instance = new MySqlConnection();
        }
        return instance;
    }

    public Connection getConnection() {
        Connection connection = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return connection;
    }
}



