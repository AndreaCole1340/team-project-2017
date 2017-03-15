package classes;

public class Customer {
    //instance fields

    private static int nextCustID = 1;
    private int customerID;
    private String name;
    private String type;
    private String number;
    private String street;
    private String town;
    private String county;
    private String phoneNo;
    private String email;

    public Customer(String name, String type, String number, String street, String town, String county, String phoneNo, String email) {
        customerID = nextCustID++;
        this.name = name;
        this.type = type;
        this.number = number;
        this.street = street;
        this.town = town;
        this.county = county;
        this.phoneNo = phoneNo;
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    public int getCustomerID() {
        return customerID;
    }

    public String getNumber() {
        return this.number;
    }
    
    public String getTown(){
        return this.town;
    }
    
    public String getStreet(){
        return this.street;
    }
    
    public String getCounty(){
        return this.county;
    }
    
    public String getEmail(){
        return email;
    }
    
    public void setEmail(String email){
        this.email = email;
    }

    public static void setNextID(int id) {
        nextCustID = id;
    }
    
    @Override
    public String toString() {
        return "Customer [customerID=" + customerID + ", name=" + name + ", type=" + type + ", number=" + number + ", street"
                + street+", town "+ town+ ", county " +county+ ", phoneNo=" + phoneNo +", Email="+ email+ "]";
    }
}