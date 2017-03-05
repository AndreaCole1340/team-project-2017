package daoCustomer;

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
    private static final String DB_URL = "jdbc:mysql://ec2-34-251-86-35.eu-west-1.compute.amazonaws.com:3306/project_planner";
    private static final String USER_NAME = "remoteuser";
    private static final String PASSWORD = "password";
    private Statement stmt;
    private Connection conn;

    public CustomerDBHandler() {
        try {
            Class.forName(JDBC_DRIVER);
            conn = DriverManager.getConnection(DB_URL, USER_NAME, PASSWORD);
            stmt = conn.createStatement();
            createCustomerTable();
        } catch (SQLException e) {
            System.out.println("Problem with SQL. " + e.getMessage());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public Customer read(int id) {
        String query = makeSelectCustomer(id);
        Customer c = null;
        try {
            ResultSet rs = stmt.executeQuery(query);
            Customer.setNextID(id);
            rs.next();
            c = new Customer(rs.getString("Name"), rs.getString("Type"), rs.getString("Address"), rs.getString("Contact"));
        } catch (SQLException e) {
            System.out.println("Problem with SQL.\n" + e.getMessage());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return c;
    }

    public void write(Customer c) {
        if (c != null) {
            try {
                String query = makeInsertQuery(c.getName(), c.getAddress(), c.getContactDetails(), c.getType());
                //stmt.executeQuery("USE ProjectPlanner;");
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
        String createTable = "CREATE TABLE IF NOT EXISTS customers "
                + "(CustomerID INTEGER auto_increment not NULL, "
                + " Name VARCHAR(255), "
                + " Address VARCHAR(255), "
                + " Contact VARCHAR(255), "
                + " Type VARCHAR(255), "
                + " PRIMARY KEY ( CustomerID ))";
        //create customer table
        try {
            stmt.executeUpdate(createTable);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
