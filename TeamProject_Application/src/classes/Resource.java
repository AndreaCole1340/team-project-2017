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
public enum Resource {
    PLUMBER(120), CARPENTER(120), ELECTRICIAN(120), MASON(120), LABOURER(80), 
    PLASTERER(120), GLAZIER(120), PAINTER(120), PLANT(150), LANDSCAPER(120), APPRENTICE(60);
    private final int price;
    
    private Resource(int price){
        this.price = price;
    }
    
    public int getPrice(){
        return price;
    }
    
}
