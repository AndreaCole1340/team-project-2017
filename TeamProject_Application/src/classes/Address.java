/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classes;

/**
 *
 * @author l0011_000
 */
public class Address {
    
    private String number;
    private String street;
    private String town;
    private String county;
    
    
    public Address(){
        
    }
    public Address(String no, String st, String town, String co){
        this.number = no;
        this.street = st;
        this.town = town;
        this.county = co;
    }
    
    public String getNumber(){
        return number;
    }
    
    public void setNumber(String no){
        this.number = no;
    }
    
   public String getStreet(){
        return street;
    }
   
    public void setStreet(String st){
        this.street = st;
    }
    
    public String getTown(){
        return town;
    }
    
    public void setTown(String town){
        this.town = town;
    }
    
     public String getCounty(){
        return county;
    }
     
     public void setCounty(String co){
        this.county = co;
    }
    
     public String toString(){
         String str;
         str = "Number : "+number+" Street : "+street+" Town : "+town+" County : "+county;
         return str;
     }
}
