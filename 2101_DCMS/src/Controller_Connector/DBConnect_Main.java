/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller_Connector;
import java.sql.*;
import javax.swing.JOptionPane;

/**
 *
 * @author Nikko
 */
public class DBConnect_Main {
    
    private String url = "jdbc:mysql://localhost:3306/dcms_db";
    private String user = "root";
    private String password = "";
    
    private static Connection con_m;
    private static DBConnect_Main dbCon_m;
    
    public DBConnect_Main(){
        con_m = null;
        try {
            // Correct way to get a connection using DriverManager
            con_m = DriverManager.getConnection(url, user, password);
            JOptionPane.showMessageDialog(null, "Connected successfully", "Connecting to DB", 0);
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Connection Error: " + e.getMessage(), "Connecting to DB", 0);
        }
        
    }
    public static DBConnect_Main getDBConnection(){
        if(dbCon_m == null){
            dbCon_m = new DBConnect_Main();
        }
        return dbCon_m;
    }
    public static Connection getConnection(){
        
        return con_m;
    }
    
}
