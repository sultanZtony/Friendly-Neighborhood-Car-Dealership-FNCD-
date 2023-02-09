import java.lang.reflect.GenericDeclaration;
import java.util.ArrayList;
import java.util.Arrays;

public class main {
    public static void main(String[] argc) {
        ArrayList<Staff> staff = new ArrayList<>(Arrays.asList(new Salesperson(), new Intern(), new Intern(), new Mechanic(), new Mechanic(), new Mechanic()));
        System.out.println(getInterns(staff).get(1).getName());
    }

    static ArrayList<Staff> getInterns(ArrayList<Staff> staff) {
        ArrayList<Staff> interns = new ArrayList<>();
        for (Staff obj : staff) {
            if (obj instanceof Intern) {
                interns.add(obj);
            }
        }
        return interns;
    }
}