package testers;

import classes.Date;
import classes.Project;
import classes.Resource;
import dao.ProjectDBHandler;

/**
 * @author Marinus Toman
 *  Date: 15-Mar-2017
 */
public class ProjectDBTester {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Project t1 = new Project(1);
        t1.read();
        Project t2 = new Project(2);
        t2.read();
        ProjectDBHandler db = new ProjectDBHandler("remoteuser", "password");
        db.write(t1);
        db.write(t2);
        System.out.println("Written to DB");

    }

}
