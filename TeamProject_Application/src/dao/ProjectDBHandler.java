/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import classes.Project;
import java.sql.SQLException;

/**
 *
 * @author Marinus Toman Date: 08-Mar-2017
 */
public class ProjectDBHandler extends DBHandler {
    // Instance Fields

    // Constructors
    public ProjectDBHandler(String username, String password) {
        super(username, password);
    }

    // Methods
    public void write(Project project) {
        this.openConnection();
        //createProjectTable();
        if (project != null) {
            try {
                String startDate = project.getStartDate().toString();
                String endDate = project.getEndDate().toString();
                String query = makeInsertQuery(project.getName(), startDate, endDate, project.getDuration(), project.getCost(), project.getNoOfTasks());
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
        String select = "SELECT * FROM project WHERE ProjectID = " + id + ";";

        return select;
    }

    private static String makeInsertQuery(String name, String start, String end, double duration, double cost, int tasks) {
        String insert = "INSERT INTO project (Name, Start_Date, End_Date, Duration, Cost, Num_Tasks) "
                + "VALUES ('" + name + "', '" + start + "', '" + end
                + "', + " + duration + ", " + cost + ", " + tasks + " );";

        return insert;
    }
}
