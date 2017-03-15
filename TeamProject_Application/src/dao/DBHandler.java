package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * @author Marinus Toman
 * Date: 14-Mar-2017
 */
public abstract class DBHandler {
    // Instance Fields
    protected Statement stmt;
    protected Connection conn;
    private static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    private static final String DB_URL = "jdbc:mysql://"
        + "ec2-34-251-133-58.eu-west-1.compute.amazonaws.com:3306" 
        + "/project_planner?autoReconnect=true&useSSL=false";
    private static String username;
    private static String password;
    
    // Constructors
    public DBHandler(String username, String password){
        DBHandler.username = username;
        DBHandler.password = password;
    }
    // Methods
    protected void openConnection(){
        try {
            Class.forName(JDBC_DRIVER);
            conn = DriverManager.getConnection(DB_URL, username, password);
            stmt = conn.createStatement();
        } catch (SQLException e) {
            System.out.println("Problem with SQL. " + e.getMessage());
        } catch (ClassNotFoundException e) {
            System.out.println("Problem with driver. " + e.getMessage());
        }
    }
    
    protected void closeConnection(){
        if(conn != null){
            try{
                conn.close();
            }catch(SQLException e){
                System.out.println("Connection not closed. " + e.getMessage());
            }
        }
    }
}
