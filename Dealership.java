import java.util.ArrayList;

public class Dealership {
    private double operatingBudget;
    private ArrayList<Staff> staff;
    private ArrayList<Vehicle> vehicles;

    public Dealership() {
        operatingBudget = 500000;
        staff = new ArrayList<>();
        vehicles = new ArrayList<>();
    }

    public ArrayList<Staff> getInterns() {
        ArrayList<Staff> interns = new ArrayList<>();
        for (Staff obj : staff) {
            if (obj instanceof Intern) {
                interns.add(obj);
            }
        }
        return interns;
    }

    public ArrayList<Staff> getMechanics() {
        ArrayList<Staff> mechanics = new ArrayList<>();
        for (Staff obj : staff) {
            if (obj instanceof Mechanic) {
                mechanics.add(obj);
            }
        }
        return mechanics;
    }

    public ArrayList<Staff> getSalespeople() {
        ArrayList<Staff> salespeople = new ArrayList<>();
        for (Staff obj : staff) {
            if (obj instanceof Salesperson) {
                salespeople.add(obj);
            }
        }
        return salespeople;
    }

    // getOperatingBudget()
}
