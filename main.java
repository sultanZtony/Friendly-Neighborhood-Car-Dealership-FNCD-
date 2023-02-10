import java.util.ArrayList;
import java.util.Arrays;

public class main {
    public static void main(String[] argc) {
        Dealership dealership = new Dealership();
        Activity activity = new Activity(dealership);

  
        // Run simulation in a 30 day loop
        activity.open();
        activity.wash();
        activity.repair();
        activity.sell();
        activity.end();
    }
}