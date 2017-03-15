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
 * @author Marinus Toman Date: 08-Mar-2017
 */
public class TaskDBHandler extends DBHandler {

    public TaskDBHandler(String user, String password) {
        super(user, password);
    }

    public Task read(int id) {
        String query = makeSelectTask(id);
        Task t = null;
        try {
            openConnection();
            ResultSet rs = stmt.executeQuery(query);
            Task.setNextID(id);
            rs.next();
            java.sql.Date sqlDate = rs.getDate("Start");
            Date temp = Date.valueOf(sqlDate.toString());
            t = new Task(rs.getInt("projectID"), rs.getString("Name"), rs.getString("Type"), temp, rs.getInt("duration"), Resource.APPRENTICE);
        } catch (SQLException e) {
            System.out.println("Problem with SQL.\n" + e.getMessage());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            closeConnection();
        }
        return t;
    }

    public void write(Task task) {
        openConnection();
        createTaskTable();
        if (task != null) {
            try {
                String startDate = task.getStartDate().toString();
                String endDate = task.getEndDate().toString();
                String query = makeInsertQuery(task.getName(), task.getTYPE(), startDate, endDate, task.getCalculatedTime(), task.getProjectID(), task.getResources());
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
        //closeConnection();
    }

    private static String makeSelectTask(int id) {
        String select = "SELECT * FROM task WHERE TaskID = " + id + ";";

        return select;
    }

    private static String makeInsertQuery(String name, String type, String start, String end, double duration, int projectID, int res) {
        String insert = "INSERT INTO task (Name, Type, Start, End, Duration, ProjectID, Resources) "
                + "VALUES ('" + name + "', '" + type + "', '" + start + "', '"
                + end + "', + " + duration + ", " + projectID + ", " + res + " );";

        return insert;
    }

    private void createTaskTable() {
        //create table
        String createTable = "CREATE TABLE IF NOT EXISTS task "
                + "(TaskID INTEGER auto_increment not NULL, "
                + " Name VARCHAR(255), "
                + " Type VARCHAR(255), "
                + " Start DATE, End DATE, "
                + " Duration DOUBLE, "
                + " ProjectID INTEGER, "
                + " Resources INTEGER, "
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
}
