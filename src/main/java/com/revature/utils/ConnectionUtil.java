package com.revature.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionUtil {
	
	
	public static Connection getConnection() throws SQLException {
        //register driver- makes app aware of what particular Driver class we are using
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        String url = "jdbc:postgresql://appian-210419.cp7bc7gpaqtp.us-east-2.rds.amazonaws.com:5432/project1";
        String username = "postgres"; //use environment variables to hide the raw values to protect this info
        String password = "password"; //System.getenv("keyName")

        return DriverManager.getConnection(url, username, password);
    }

}
