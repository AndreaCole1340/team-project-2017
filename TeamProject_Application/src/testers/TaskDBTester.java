package testers;

import classes.Date;
import classes.Resource;
import classes.Task;
import dao.TaskDBHandler;

/**
 * @author Marinus Toman
 *  Date: 09-Mar-2017
 */
public class TaskDBTester {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Task t1 = new Task(1, "Painting", "Decor", new Date(16, 5, 2016), 3, Resource.PAINTER);
        Task t2 = new Task(1, "Building", "Masonry", new Date(17, 5, 2017), 3, Resource.MASON);
        TaskDBHandler db = new TaskDBHandler("remoteuser", "password");
        db.write(t1);
        t2.assignResource(Resource.PAINTER);
        db.write(t2);
        System.out.println("Written to DB");

        Task t3 = db.read(2);
        System.out.println("Reading from DB");
        if (t3 != null) {
            System.out.println(t3.toString());
        }
    }

}
