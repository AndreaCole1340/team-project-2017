package testers;

import classes.Customer;
import daoCustomer.CustomerDBHandler;

public class DBTester {

	public static void main(String[] args) {
		Customer c1 = new Customer("Harry", "Personal", "12 Main Street", "harry@email.com");
		Customer c3 = new Customer("Tom", "Professional", "13 Main Street", "tommy@email.com");
		CustomerDBHandler db = new CustomerDBHandler();
		db.write(c1);
		db.write(c3);
		System.out.println("Written to DB");
		
		Customer c2 = db.read(4);
		System.out.println("Reading from DB");
		if(c2 != null)
			System.out.println(c2.toString());
	}

}