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

public class Project {

    // instance fields
    private int projectID;
    private static int nextProjectID = 1;
    private final int customerID;
    private String name;
    private Date startDate;
    private Date endDate;
    //private int noOfTasks = 0;
    private double cost;
    //private int noOfEmployees = 0;
    private double duration;
    private ArrayList<Task> tasks = new ArrayList<>();
    //private ArrayList<Integer> idList = new ArrayList<Integer>();

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

    public Date getEndDate() {
        // get end date of last task
        endDate = tasks.get(tasks.size()-1).getEndDate();
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
        for (Task t : tasks) {
            // calculate price of each task in project
            //t.calculateCost();
            cost += t.getCost();
        }
    }

    public void calculateDuration() {
        for (Task t : tasks) {
            //t.calculateDuration();
            duration += t.getCalculatedTime();
        }
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
        return "Project{" + "projectID=" + projectID + ", customerID=" + customerID + ", name=" + name + ", startDate=" + startDate + ", endDate=" + endDate + ", cost=" + cost + ", duration=" + duration + '}';
    }

    public void printTasks(){
        for(Task t: tasks)
            System.out.println(t.toString());
    }
}
