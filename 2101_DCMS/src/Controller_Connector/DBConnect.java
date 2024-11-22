/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller_Connector;

import java.sql.*;
import javax.swing.JOptionPane;

public class DBConnect {
    
    private String url = "jdbc:mysql://localhost:3306/login_db";
    private String user = "root";
    private String password = "";
    
    private static Connection con;
    private static DBConnect dbCon;
    
    public DBConnect(){
        con = null;
        try {
            // Correct way to get a connection using DriverManager
            con = DriverManager.getConnection(url, user, password);
            JOptionPane.showMessageDialog(null, "Connected successfully", "Connecting to DB", 0);
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Connection Error: " + e.getMessage(), "Connecting to DB", 0);
        }
        
    }
    public static DBConnect getDBConnection(){
        if(dbCon == null){
            dbCon = new DBConnect();
        }
        return dbCon;
    }
    public static Connection getConnection(){
        
        return con;
    }
}
