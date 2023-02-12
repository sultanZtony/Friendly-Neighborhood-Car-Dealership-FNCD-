import java.util.ArrayList;
import java.util.Random;
 
abstract class Activity {
    abstract void run();
}

class Opening extends Activity {
    private Dealership dealership;

    public Opening(Dealership dealership) {
        this.dealership = dealership;
    }

    @Override
    void run() {
        System.out.println("Running Open activity...");

        // If there are less than 3 interns on opening, hire until intern count is reached.
        while (dealership.getStaff(StaffType.INTERN).size() < 3) {
            dealership.addStaff(StaffType.INTERN);
        }

        dealership.checkVehicles(VehicleType.CAR);
        dealership.checkVehicles(VehicleType.PERFORMANCE_CAR);
        dealership.checkVehicles(VehicleType.PICKUP);

        System.out.print(dealership.getBudget());
        // Check if there are less than 4 of each type of vehicle in the inventory. If so, add them.
    }
}

class Washing extends Activity {
    private Dealership dealership;

    public Washing(Dealership dealership) {
        this.dealership = dealership;
    }

    @Override
    void run() {
        System.out.println("Running Washing activity...");
         ArrayList<Vehicle> Pickup = dealership.getVehicles(VehicleType.PICKUP); 
         ArrayList<Vehicle> Car =  dealership.getVehicles(VehicleType.CAR);
         ArrayList<Vehicle> PerformanceCar = dealership.getVehicles(VehicleType.PERFORMANCE_CAR);


        ArrayList<Staff> AllInterns = dealership.getStaff(StaffType.INTERN);
        int sizeInterns = AllInterns.size();
        int count = 2 * sizeInterns;

        for(Vehicle obj: Pickup){
            double random = Math.random();
            if (obj.getCondition() == "Dirty" && count > 0 && random < 0.8) {

                obj.setCondtion("Clean");
                count --;
            }
        }

        for(Vehicle obj: Car){
            double random = Math.random();
            if (obj.getCondition() == "Dirty" &&  random < 0.8) {
                obj.setCondtion("Clean");
                count --;
            }
        }

        for(Vehicle obj: PerformanceCar){
            double random = Math.random();

            if (obj.getCondition() == "Dirty" && random < 0.8) {
                obj.setCondtion("Clean");
                count --;
            }
        }

    }
}
