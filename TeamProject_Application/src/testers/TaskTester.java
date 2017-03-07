/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testers;

import classes.Date;
import classes.Resource;
import classes.Task;

/**
 *
 * @author l0011
 */
public class TaskTester {

    public static void main(String[] args) {
        Date d = new Date(6, 3, 2017);
        Task t1 = new Task(1, "Elctrical", "Phase 1", new Date(6,2,2017), 10, Resource.ELECTRICIAN);
        Task t2 = new Task(1, "Plumbing", "Phase 1", d, 5, Resource.PLUMBER);
        Task t3 = new Task(d);
        
//        System.out.println(t1.getCost());
//        System.out.println(t1.getCalculatedTime());
//        System.out.println(t1.getEndDate().toString());
//        System.out.println(t1.toString());
//        
//        t2.assignResource(Resource.LABOURER);
//        System.out.println(t2.getCost());
//        System.out.println(t2.getCalculatedTime());
//        System.out.println(t2.getEndDate().toString());
//        System.out.println(t2.toString());

        t3.read();
        System.out.println(t3.toString());
    }
}
