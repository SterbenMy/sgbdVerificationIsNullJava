package proiect;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionManager {
            private static  String url1 ="jdbc:sqlserver://localhost:1433;databaseName=Ex1";
            private static  String url2 ="jdbc:sqlserver://localhost:1433;databaseName=Ex2";
            private static  String driverName ="com.microsoft.sqlserver.jdbc.SQLServerDriver";
            private static  String username ="admin";
            private static  String password ="12345";
            private static Connection con;
            
            
            public static Connection getConnectionFirst() {
        try {
            Class.forName(driverName);
            try {
                con = DriverManager.getConnection(url1, username, password);
            } catch (SQLException ex) {
                // log an exception. fro example:
                System.out.println("Failed to create the database connection."); 
            }
        } catch (ClassNotFoundException ex) {
            // log an exception. for example:
            System.out.println("Driver not found."); 
        }
        return con;
    }
            
        public static Connection getConnectionSecond() {
        try {
            Class.forName(driverName);
            try {
                con = DriverManager.getConnection(url2, username, password);
            } catch (SQLException ex) {
                // log an exception. fro example:
                System.out.println("Failed to create the database connection."); 
            }
        } catch (ClassNotFoundException ex) {
            // log an exception. for example:
            System.out.println("Driver not found."); 
        }
        return con;
    }    

}
