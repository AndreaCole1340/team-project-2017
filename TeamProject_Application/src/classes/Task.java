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

public class Task {

    private int projectID;
    private int taskID;
    private static int nextTaskID = 1;
    private final Date startDate;
    private Date endDate;
    private String name;
    private final String TYPE;
    private double cost;
    private double totalDuration;
    private double calculatedTime;
    private ArrayList<Resource> resourcesAssigned;
    //pointer to previous task
    //private Task previous;

    /**
     * @param projectID
     * @param name
     * @param type
     * @param startDate
     * @param duration
     * @param resource
     * 
     */
    public Task(int projectID, String name, String type, Date startDate, int duration, Resource resource) {
        this.projectID = projectID;
        this.name = name;
        this.TYPE = type;
        this.startDate = startDate;
        //endDate = new Date(startDate.getDay(), startDate.getMonth(), startDate.getYear());
        this.totalDuration = duration;
        resourcesAssigned = new ArrayList<>();
        resourcesAssigned.add(resource);
        taskID = nextTaskID++;
        //this.previous = previous;
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
     * Accessor method for duration
     *
     * @return
     */
    public double getDuration() {
        return totalDuration;
    }

    public Date getEndDate() {
        calcTime();
        endDate = new Date(startDate.getDay(), startDate.getMonth(), startDate.getYear());
        for (int i = 1; i < calculatedTime; i++) {
            endDate.addDay();
        }

        return endDate;
    }

    public double getCalculatedTime() {
        calcTime();
        return calculatedTime;
    }

    /**
     * Mutator method for employee list
     *
     * @param resourcesAssigned
     * 
     */
    public void setResourcesAssigned(ArrayList<Resource> resourcesAssigned) {
        this.resourcesAssigned = resourcesAssigned;
    }
    /**
     * Accessor method for resource list
     *
     * @return resourcesAssigned
     */
    public ArrayList<Resource> getResourcesAssigned() {
        return this.resourcesAssigned;
    }

    /**
     * Accessor method for task ID
     *
     * @return
     */
    public int getTaskID() {
        return taskID;
    }

    /**
     * Accessor method for project ID
     *
     * @return
     */
    public int getProjectID() {
        return projectID;
    }

    /**
     * Accessor method for task type
     *
     * @return
     */
    public String getTYPE() {
        return TYPE;
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
     * Method to assign resource to task
     *
     * @param resource Resource to assign
     */
    public void assignResource(Resource resource) {
        resourcesAssigned.add(resource);
    }

    /**
     * Method to calculate total cost of task
     */
    private void calculateCost() {
        for (Resource r : resourcesAssigned) {
            // calculate price of each resource per day
            cost += r.getPrice();
        }
        // calculate total cost of whole task
        cost = cost * getCalculatedTime();
    }

    private void calcTime(){
        calculatedTime = totalDuration / resourcesAssigned.size();
    }
    /**
     * toString method
     *
     * @return
     */
    @Override
    public String toString() {
        calculateCost();
        return "Task{" + "projectID=" + projectID + ", taskID=" + taskID + ", startDate=" + startDate + ", endDate=" + getEndDate() + ", name=" + name + ", TYPE=" + TYPE + ", cost=" + cost + ", totalDuration=" + totalDuration + ", calculatedTime=" + calculatedTime + ", resourcesAssigned=" + resourcesAssigned + '}';
    }

//    public boolean hasPrevious(){
//        if(previous != null)
//            return true;
//        return false;
//    }
}
