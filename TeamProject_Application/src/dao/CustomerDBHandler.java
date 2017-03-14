package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import classes.Customer;

public class CustomerDBHandler {

    // JDBC Driver name and database URL
    private static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    // amazon address = ec2-34-251-86-35.eu-west-1.compute.amazonaws.com:3306
    // private vm = NAT: 192.168.20.137  VMNet1: 192.168.10.130
    private static final String DB_URL = "jdbc:mysql://ec2-34-250-81-167.eu-west-1.compute.amazonaws.com:3306"
            + "/project_planner?autoReconnect=true&useSSL=false";
    private static String username;
    private static String password;
    private Statement stmt;
    private Connection conn;

    public CustomerDBHandler(String user, String password) {
        username = user;
        CustomerDBHandler.password = password;
    }

    public Customer read(int id) {
        String query = makeSelectCustomer(id);
        Customer c = null;
        try {
            openConnection();
            ResultSet rs = stmt.executeQuery(query);
            Customer.setNextID(id);
            rs.next();
            c = new Customer(rs.getString("Name"), rs.getString("Type"), rs.getString("Number"), rs.getString("Street"), 
                    rs.getString("Town"), rs.getString("County"), rs.getString("PhoneNo"), rs.getString("Email"));
        } catch (SQLException e) {
            System.out.println("Problem with SQL.\n" + e.getMessage());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }finally{
            closeConnection();
        }
        return c;
    }

    public void write(Customer c) {
        openConnection();
        if (c != null) {
            try {
                String query = makeInsertQuery(c.getName(), c.getType(), c.getNumber(), c.getStreet(), c.getTown(), c.getCounty(),
                c.getPhoneNo(), c.getEmail());
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

    private static String makeInsertQuery(String name, String type, String number, String street, 
            String town, String county, String phoneNo, String email) {
        String insert = "INSERT INTO customers (Name, Type, Number, Street, Town, County, PhoneNo, Email) VALUES ('" + name + "', '" + type + "', '" 
                + number + "', '" +street + "', '" +town+"', '"+county+"', '"+phoneNo+"', '"+ email+");";

        return insert;
    }

    private void createCustomerTable() {
        //create table
        String createTable = "CREATE TABLE IF NOT EXISTS customers "
                + "(CustomerID INTEGER auto_increment not NULL, "
                + " Name VARCHAR(255), "
                + " Type VARCHAR(255), "
                + " Number VARCHAR(255), "
                + " Street VARCHAR(255), "
                + " Town VARCHAR(255), "
                + " County VARCHAR(255), "
                + " PhoneNo VARCHAR(255), "
                + " Email VARCHAR(255), "
                + " PRIMARY KEY ( CustomerID ))";
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