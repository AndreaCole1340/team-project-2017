package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import classes.Customer;

public class CustomerDBHandler extends DBHandler {

    public CustomerDBHandler(String user, String password) {
        super(user, password);
    }

    public Customer read(int id) {
        String query = makeSelectCustomer(id);
        Customer c = null;
        try {
            openConnection();
            ResultSet rs = stmt.executeQuery(query);
            Customer.setNextID(id);
            rs.next();
            c = new Customer(rs.getString("Name"), rs.getString("Type"), rs.getString("Address"), rs.getString("Contact"));
        } catch (SQLException e) {
            System.out.println("Problem with SQL.\n" + e.getMessage());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            closeConnection();
        }
        return c;
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
            System.out.println("Table not created. " + e.getMessage());
        }
    }
}
