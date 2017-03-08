package testers;

import classes.Date;

/**
 * @author Marinus Toman Date: 08-Mar-2017
 */
public class DateTester {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
            Date d = Date.valueOf("207-01-12");
            System.out.println("Success==> " + d.toString());
        } catch (IllegalArgumentException iae) {
            System.out.println("Something wrong with ur arguments " + iae.getMessage());
        } catch (Exception e) {
            System.out.println("Something else wrong " + e.getMessage());
        }
    }

}
