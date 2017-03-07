/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testers;

import classes.Project;

/**
 *
 * @author l0011
 */
public class ProjectTester2 {
    public static void main(String[] args){
        Project p1 = new Project(1);
        p1.read();
        System.out.println(p1);
        p1.printTasks();
    }
}
