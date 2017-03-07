/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testers;

import classes.Resource;

/**
 *
 * @author l0011
 */
public class ResourceTester {

    public static void main(String[] args) {
        Resource[] array = {Resource.PLUMBER, Resource.APPRENTICE, Resource.LABOURER};
        for (Resource r : array) {
            System.out.println("The resource " + r + " has a price of " + r.getPrice());
        }
        for (Resource r : Resource.values()) {
            System.out.println("The resource " + r + " has a price of " + r.getPrice());
        }
    }
}
