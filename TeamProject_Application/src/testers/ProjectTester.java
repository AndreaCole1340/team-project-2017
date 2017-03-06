/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testers;

import classes.Date;
import classes.Project;
import classes.Resource;
import classes.Task;
import java.util.Scanner;

/**
 *
 * @author l0011
 */
public class ProjectTester {
    public static void main(String[] args){
        Scanner in = new Scanner(System.in);
        int option = 0;
        String name = "", type = "", res = "";
        int duration = 0;
        Resource resource = null;
        Date start = new Date(6, 3, 2017);
        Task task = new Task(1, "Plumbing", "Phase 1", start, 5, Resource.PLUMBER);
        Project pro = new Project(1, "House", start, task);
        do{
            System.out.println("Do you want to create another task in the project? [0 to quit]");
            option = in.nextInt();
            if(option == 0)
                System.out.println("Bye");
            else{
                System.out.print("Enter name: ");
                name = in.next();
                System.out.print("Enter type: ");
                type = in.next();
                in.next();
                System.out.print("Enter duration: ");
                duration = in.nextInt();
                System.out.print("Enter resource: ");
                res = in.next();
                
                switch(res.toLowerCase()){
                    case "plumber": resource = Resource.PLUMBER;
                    break;
                    case "electrician": resource = Resource.ELECTRICIAN;
                    break;
                    case "carpenter": resource = Resource.CARPENTER;
                    break;
                    case "mason": resource = Resource.MASON;
                    break;
                    case "landscaper": resource = Resource.LANDSCAPER;
                    break;
                    case "glazier": resource = Resource.GLAZIER;
                    break;
                    case "labourer": resource = Resource.LABOURER;
                    break;
                    case "painter": resource = Resource.PAINTER;
                    break;
                    default: resource = Resource.APPRENTICE;
                }
                start = pro.getEndDate();
                
                Task t2 = new Task(pro.getProjectID(), name, type, start, duration, resource);
                pro.assignTask(t2);
            }
        }while(option != 0);
        
        System.out.println(pro.toString());
        pro.printTasks();
    }
}
