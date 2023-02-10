import java.util.ArrayList;

public class Dealership {
    private double operatingBudget;
    private ArrayList<Staff> staff;
    private ArrayList<Vehicle> vehicles;
    // if ran out of money add 25,000 and announce the evant and keep track of added money
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

    public ArrayList<Staff> addstaff(String StaffType) {

        if (StaffType == "Intern") {
            staff.add(new Intern());
        }
        else if (StaffType == "Sales") {
            staff.add(new Salesperson());
        }
        else {
            staff.add(new Mechanic());
        }


        return staff;
    }

    public ArrayList<Vehicle> addVehicles(String vehicle) {

        if (vehicle == "PerformanceCar") {
            PerformanceCar newpreformance = new PerformanceCar()
            vehicles.add(newpreformance);
            operatingBudget = operatingBudget -newpreformance.getCost(20000, newpreformance.getCondtionDiscount(newpreformance.getCondtion()));
        } 2
        else if (vehicle == "Car") {
            vehicles.add(new Car());
            operatingBudget = operatingBudget - 10000;

        }
        else {
            vehicles.add(new Pickup());
            operatingBudget = operatingBudget - 10000;

        }


        return vehicles;
    }



    public ArrayList<Vehicle> getPerformanceCar() {
        ArrayList<Vehicle> PerformanceCar = new ArrayList<>();
        for (Vehicle obj : vehicles) {
            if (obj instanceof PerformanceCar) {
                PerformanceCar.add(obj);
            }
        }
        return PerformanceCar;
    }

    public ArrayList<Vehicle> getCar() {
        ArrayList<Vehicle> Car = new ArrayList<>();
        for (Vehicle obj : vehicles) {
            if (obj instanceof PerformanceCar) {
                Car.add(obj);
            }
        }
        return Car;
    }

    public ArrayList<Vehicle> getPickup() {
        ArrayList<Vehicle> Pickup = new ArrayList<>();
        for (Vehicle obj : vehicles) {
            if (obj instanceof Pickup) {
                Pickup.add(obj);
            }
        }
        return Pickup;
    }

    // getOperatingBudget()
}
