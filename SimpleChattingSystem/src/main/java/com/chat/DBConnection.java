package com.chat;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnection {

    private static final String URL =
            "jdbc:mysql://db01.dbhost.dev:5051/db_454kcydm3";

    private static final String USER =
            "user_454kcydm3";

    private static final String PASSWORD =
            "p454kcydm3";

    public static Connection getConnection() throws Exception {
        Class.forName("com.mysql.cj.jdbc.Driver");
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}