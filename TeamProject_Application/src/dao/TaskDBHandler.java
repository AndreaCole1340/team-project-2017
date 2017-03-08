/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package dao;

import classes.Customer;
import classes.Date;
import classes.Resource;
import classes.Task;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author Marinus Toman
 * Date: 08-Mar-2017
 */
public class TaskDBHandler {
// JDBC Driver name and database URL
    private static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    // amazon address = ec2-34-251-86-35.eu-west-1.compute.amazonaws.com:3306
    private static final String DB_URL = "jdbc:mysql://ec2-34-251-86-35.eu-west-1.compute.amazonaws.com:3306"
            + "/project_planner?autoReconnect=true&useSSL=false";
    private static String username;
    private static String password;
    private Statement stmt;
    private Connection conn;

    public TaskDBHandler(String user, String password) {
        username = user;
        this.password = password;
    }

    public Task read(int id) {
        String query = makeSelectCustomer(id);
        Task t = null;
        try {
            openConnection();
            ResultSet rs = stmt.executeQuery(query);
            Task.setNextID(id);
            rs.next();
            java.sql.Date sqlDate = rs.getDate("startDate");
            Date temp = Date.valueOf(sqlDate.toString());
            t = new Task(rs.getInt("projectID"), rs.getString("Name"), rs.getString("Type"), temp, rs.getInt("duration"), Resource.APPRENTICE);
        } catch (SQLException e) {
            System.out.println("Problem with SQL.\n" + e.getMessage());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }finally{
            closeConnection();
        }
        return t;
    }

    public void write(Customer c) {
        openConnection();
        if (c != null) {
            try {
                String query = makeInsertQuery(c.getName(), c.getAddress(), c.getContactDetails(), c.getType());
                stmt.executeQuery("USE project_planner;");
                stmt.executeUpdate(query);
                System.out.println("success...written to db");
            } catch (NullPointerException npe) {
                System.out.println(npe.getMessage());
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        closeConnection();
    }

    private static String makeSelectCustomer(int id) {
        String select = "SELECT * FROM customers WHERE CustomerID = " + id + ";";

        return select;
    }

    private static String makeInsertQuery(String name, String address, String contact, String type) {
        String insert = "INSERT INTO customers (Name, Address, Contact, Type) VALUES ('" + name + "', '" + address + "', '" + contact + "', '" + type + "');";

        return insert;
    }

    private void createCustomerTable() {
        //create table
        String createTable = "CREATE TABLE IF NOT EXISTS task "
                + "(TaskID INTEGER auto_increment not NULL, "
                + " Name VARCHAR(255), "
                + " Type VARCHAR(255), "
                + " Start DATE, "
                + " Duration INTEGER, "
                + " ProjectID INTEGER, "
                + " PRIMARY KEY ( TaskID ), "
                + " CONSTRAINT FOREIGN KEY ( ProjectID ) "
                + " REFERENCES project( ProjectID ))";
        //create customer table
        try {
            stmt.executeUpdate(createTable);
        } catch (SQLException e) {
            System.out.println("Table not created. " + e.getMessage());
        }
    }
    
    private void openConnection(){
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
    
    private void closeConnection(){
        if(conn != null){
            try{
                conn.close();
            }catch(SQLException e){
                System.out.println("Connection not closed. " + e.getMessage());
            }
        }
    }
}
