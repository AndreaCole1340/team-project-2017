package classes;

public class Customer {
    //instance fields

    private static int nextCustID = 1;
    private int customerID;
    private String name;
    private String type;
    private String address;
    private String contactDetails;

    public Customer(String name, String type, String address, String contact) {
        customerID = nextCustID++;
        this.name = name;
        this.type = type;
        this.address = address;
        this.contactDetails = contact;
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

    public String getContactDetails() {
        return contactDetails;
    }

    public void setContactDetails(String contactDetails) {
        this.contactDetails = contactDetails;
    }

    public int getCustomerID() {
        return customerID;
    }

    public String getAddress() {
        return address;
    }

    public static void setNextID(int id) {
        nextCustID = id;
    }

    @Override
    public String toString() {
        return "Customer [customerID=" + customerID + ", name=" + name + ", type=" + type + ", address=" + address
                + ", contactDetails=" + contactDetails + "]";
    }

}
