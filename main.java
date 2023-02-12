import java.util.ArrayList;
import java.util.Arrays;

public class main {
    public static void main(String[] argc) {
        Dealership dealership = new Dealership();
        Activity openActivity = new Opening(dealership);

        // Run simulation for 30 calender days skipping sundays
        openActivity.run();
    }
}