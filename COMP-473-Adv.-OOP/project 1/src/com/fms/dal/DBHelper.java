package com.fms.dal;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBHelper {

    private static Connection connection = null;

    public static Connection getLocalConnection(){
        String driver = "com.mysql.jdbc.Driver";
        String url = "jdbc:mysql://127.0.0.1:3306/";
        String dbName = "fms";
        String userName = "root";
        String dbPassword = "root";

        try{
            Class.forName(driver);
        }catch (ClassNotFoundException e){
            System.out.println("DBHelper: Check if your jdbc Driver exists!");
            e.printStackTrace();
            return null;
        }

        try{
            connection = DriverManager.getConnection(url + dbName, userName, dbPassword);
        }catch (SQLException e){
            System.out.println("DBHelper: mysql Connection Failed.");
            e.printStackTrace();
            return null;
        }

        return connection;
    }

    public static void closeConnection(){
        if(connection != null){
            try{
                connection.close();
            }catch (SQLException e){
                System.out.println("DBHelper: Failed to close connection");
                e.printStackTrace();
            }
        }
    }
}
