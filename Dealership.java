import java.util.ArrayList;

public class Dealership {
    private double operatingBudget;

    private ArrayList<Staff> salespeople;
    private ArrayList<Staff> mechanics;
    private ArrayList<Staff> interns;

    private ArrayList<Vehicle> vehicles;

    public Dealership () {
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

    // public void checkVehicles(VehicleType type) {
    //     switch (type) {
    //         case PERFORMANCE_CAR:
    //         default:
    //         throw new IllegalArgumentException("Invalid staff type: " + type);
    //     }
    //     int count = 0;

    //     // Count number of vehicles of `type` currently in the vehicles inventory
    //     for (Vehicle vehicle : vehicles) {
    //         if (vehicle. == type) {     // Unfinished
    //             count++;
    //         }
    //     }

    //     // Bring the count of vehicles `type` to 4 in the vehicles inventory
    //     while (count < 4) {
    //         Vehicle vehicle;
    //         switch (type) {
    //             case CAR:
    //                 vehicle = new Car();
    //                 break;
    //             case PERFORMANCE_CAR:
    //                 vehicle = new PerformanceCar();
    //                 break;
    //             case PICKUP:
    //                 vehicle = new Pickup();
    //                 break;
    //             default:
    //                 throw new IllegalArgumentException("Invalid vehicle type: " + type);
    //         }
    //         this.vehicles.add(vehicle);
    //         this.operatingBudget -= vehicle.getCost();
    //         count++;
    //     }
    // }
}

