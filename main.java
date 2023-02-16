import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;

public class main {
    public static void main(String[] argc) {
        Dealership dealership = new Dealership();
        Open open = new Open(dealership);
        Wash wash = new Wash(dealership);
        Repairing repair = new Repairing(dealership);
        Sell sell = new Sell(dealership);
        End end = new End(dealership);
        

        // Run simulation for 30 calender days skipping sundays
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DAY_OF_MONTH, 1);

        for (int i = 0; i < 30; i++) {
            calendar.add(Calendar.DAY_OF_MONTH, 1);
            int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);

            if (dayOfWeek == Calendar.SUNDAY) {
                continue;
            }

            open.run();
            wash.run();
            repair.run();
            sell.run(dayOfWeek);
            end.run();
        }
        // System.out.println(dealership.getSoldVehicles());
    }
}