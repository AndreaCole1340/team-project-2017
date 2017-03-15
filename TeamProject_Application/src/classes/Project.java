/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classes;

/**
 *
 * @author l0011
 */
import java.util.ArrayList;
import java.util.Scanner;

public class Project {

    // instance fields
    private int projectID;
    private static int nextProjectID = 1;
    private final int customerID;
    private String name;
    private Date startDate;
    private Date endDate;
    private double cost;
    private double duration;
    private ArrayList<Task> tasks = new ArrayList<>();

    public Project(int id) {
        projectID = nextProjectID++;
        customerID = id;
        name = "";
        startDate = new Date();
        cost = 0;
        duration = 0;
    }

    /**
     * Constructor for project
     *
     * @param customerID
     * @param name
     * @param startDate
     * @param task
     */
    public Project(int customerID, String name, Date startDate, Task task) {

        this.customerID = customerID;
        projectID = nextProjectID++;
        this.name = name;
        this.startDate = startDate;
        //endDate = new Date(startDate.getDay(), startDate.getMonth(), startDate.getYear());
        tasks.add(task);
        //noOfTasks = 1;
    }

    /**
     * Accessor method for name
     *
     * @return
     */
    public String getName() {
        return name;
    }

    /**
     * Mutator method for name
     *
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Accessor method for projectID
     *
     * @return
     */
    public int getProjectID() {
        return projectID;
    }

    /**
     * Accessor method for startDate
     *
     * @return
     */
    public Date getStartDate() {
        return startDate;
    }

    public double getDuration() {
        calculateDuration();
        return duration;
    }

    public Date getEndDate() {
        calcEndDate();
        return endDate;
    }

    /**
     * Accessor method for endDate
     *
     * @param task
     */
    public void assignTask(Task task) {
        tasks.add(task);
    }

    /**
     * Accessor method for number of tasks
     *
     * @return
     */
    public int getNoOfTasks() {
        return tasks.size();
    }

    /**
     * Accessor method for cost
     *
     * @return
     */
    public double getCost() {
        calculateCost();
        return cost;
    }

    /**
     * Method to calculate cost
     */
    private void calculateCost() {
        cost = 0;
        for (Task t : tasks) {
            // calculate price of each task in project
            cost += t.getCost();
        }
    }

    public void calculateDuration() {
        for (Task t : tasks) {
            //t.calculateDuration();
            duration += t.getCalculatedTime();
        }
    }

    private void calcEndDate() {
        // get end date of last task
        endDate = tasks.get(tasks.size() - 1).getEndDate();
    }

//    public Date calculateEndDate() {
//        //endDate = startDate;
//        for (int i = 0; i < duration; i++) {
//            endDate.addDay();
//        }
//        return endDate;
//    }
    public int getCustomerID() {
        return customerID;
    }

    @Override
    public String toString() {
        //calculateCost();
        return "Project{" + "projectID=" + projectID + ", customerID=" + customerID + ", name=" + name + ", startDate=" + startDate + ", endDate=" + endDate + ", cost=" + getCost() + ", duration=" + duration + '}';
    }

    public void printTasks() {
        for (Task t : tasks) {
            System.out.println(t.toString());
        }
    }

    public void read() {
        Scanner in = new Scanner(System.in);
        boolean valid = false;
        char sentinel;
        Task task;
        do {
            try {
                System.out.println("\tENTER PROJECT DETAILS");
                System.out.print("ENTER NAME: ");
                setName(in.nextLine());
                startDate.read();
                do {
                    // if task list is empty, 
                    // then create task with same start date as project
                    if (tasks.isEmpty()) {
                        task = new Task(startDate);
                    } // otherwise create task with start date
                    // same as end date of last task in list
                    else {
                        Date temp = tasks.get(tasks.size() - 1).getEndDate();

                        task = new Task(temp);
                    }
                    task.read();
                    tasks.add(task);
                    System.out.print("Do you want to add another task? [Y|N] ");
                    sentinel = in.next().charAt(0);
                } while (!(sentinel == 'N' || sentinel == 'n'));
                calculateDuration();
                calcEndDate();
                valid = true;
            } catch (IllegalArgumentException iae) {
                System.out.println(iae.getMessage());
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        } while (!valid);
    }
}
