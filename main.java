import java.util.ArrayList;
public class main {
    public static void main(String[] argc) {

        Salesperson salesperson = new Salesperson();
        salesperson.work(5, 100.0);
        System.out.println("Name: " + salesperson.getName());
        System.out.println("Total Earned: " + salesperson.getTotalEarned());
        System.out.println("Total Days Worked: " + salesperson.getTotalDaysWorked());


        Salesperson salesperson1 = new Salesperson();
        salesperson.work(5, 100.0);
        System.out.println("Name: " + salesperson.getName());
        System.out.println("Total Earned: " + salesperson.getTotalEarned());
        System.out.println("Total Days Worked: " + salesperson.getTotalDaysWorked());

    }
}