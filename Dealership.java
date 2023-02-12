import java.util.ArrayList;

public class Dealership {
    private double operatingBudget;

    private ArrayList<Staff> salespeople;
    private ArrayList<Staff> mechanics;
    private ArrayList<Staff> interns;

     private ArrayList<Vehicle> Pickup; 
     private ArrayList<Vehicle> Car;
     private ArrayList<Vehicle> PerformanceCar;




    public Dealership () {
        this.operatingBudget = 500000;
        salespeople = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            salespeople.add(new Salesperson());
        }

        mechanics = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            mechanics.add(new Mechanic());
        }

        interns = new ArrayList<>();
        for(int i = 0; i < 3; i++) {
            interns.add(new Intern());
        }


        PerformanceCar = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            PerformanceCar.add(new PerformanceCar());
        }

        Car = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            Car.add(new Car());
        }

        Pickup = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            Pickup.add(new Pickup());
        }
    }
    public double getBudget() {
        return this.operatingBudget;
    }
    public ArrayList<Staff> getStaff(StaffType type) {
        switch (type) {
            case SALESPERSON:
                return salespeople;
            case MECHANIC:
                return mechanics;
            case INTERN:
                return interns;
            default:
                throw new IllegalArgumentException("Invalid staff type: " + type);
        }
    }

    public void addStaff(StaffType type) {
        switch (type) {
            case SALESPERSON:
                if (salespeople.size() < 3) {
                    interns.add(new Salesperson());
                } else {
                    System.out.println("Cannot hire more than 3 salespeople");
                }
                break;
            case MECHANIC:
                if (mechanics.size() < 3) {
                    mechanics.add(new Mechanic());
                } else {
                    System.out.println("Cannot hire more than 3 mechanics");
                }
                break;
            case INTERN:
                if (interns.size() < 3) {
                    interns.add(new Intern());
                } else {
                    System.out.println("Cannot hire more than 3 interns");
                }
                break;
            default:
                throw new IllegalArgumentException("Invalid staff type: " + type);
        }
    }

    public ArrayList<Vehicle> getVehicles(VehicleType type) {
        switch (type) {
            case CAR:
                return Car;
            case PERFORMANCE_CAR:
                return PerformanceCar;
            case PICKUP:
                return Pickup;
            default:
                throw new IllegalArgumentException("Invalid vehicle type: " + type);
        }
    }

    public void addVehicles(VehicleType vehicle) {

        switch (vehicle) {
            case PERFORMANCE_CAR:
            PerformanceCar newcar = new PerformanceCar();
            PerformanceCar.add(newcar);
            operatingBudget = operatingBudget -newcar.getCost();

            case CAR:
            Car newcar1 = new Car();
            Car.add(newcar1);
            operatingBudget = operatingBudget - newcar1.getCost();

            case PICKUP: 
            Pickup newcar2 = new Pickup();
            Pickup.add(newcar2);
            operatingBudget = operatingBudget - newcar2.getCost();
        }


    }

    public void checkVehicles(VehicleType type) {
        switch (type) {

            case PERFORMANCE_CAR:
            while (PerformanceCar.size()<4) {
                addVehicles(VehicleType.PERFORMANCE_CAR);
            }
            case CAR:
            while (PerformanceCar.size()<4) {
                addVehicles(VehicleType.CAR);
            }
            case PICKUP: 
            while (PerformanceCar.size()<4) {
                addVehicles(VehicleType.PICKUP);
            }
        }
    }
}

