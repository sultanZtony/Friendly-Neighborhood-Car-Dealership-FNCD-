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
    private void washVehicles(ArrayList<Vehicle> vehicles, int count) {
        for(Vehicle obj: vehicles) {
            double random = Math.random();
    
            // A Dirty Vehicle that is washed has an 80% chance of becoming Clean  
            if (obj.getCleanliness() == "Dirty" && count > 0 && random < 0.8) {
                obj.setCleanliness("Clean");
                count --;
            }
    
            // a 10% chance of becoming Sparkling. 
            if (obj.getCleanliness() == "Dirty" && count > 0 && random > 0.8 && random < 0.9) {
                obj.setCleanliness("Sparkling");
                count --;
            }
    
            // A Clean Vehicle has a 5% chance of becoming Dirty
            if (obj.getCleanliness() == "Clean" && random < 0.05) {
                obj.setCleanliness("Diry");
            }
            // a 30% chance of becoming Sparkling.
            if (obj.getCleanliness() == "Clean" && random > 0.05 && random < 0.35) {
                obj.setCleanliness("Sparkling");
            }
        }
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

        washVehicles(PerformanceCar, count);
        washVehicles(Car, count);
        washVehicles(Pickup, count);
    }
}



class Repairing extends Activity {
    private Dealership dealership;

    public Repairing(Dealership dealership) {
        this.dealership = dealership;
    }
    private void repairVehicles(ArrayList<Staff> Mechanics ,ArrayList<Vehicle> vehicles, int count) {
        for(Vehicle obj: vehicles) {
            double random = Math.random();
    
            //  Each Mechanic has an 80% chance of fixing any Vehicle worked on
            if ( count > 0 && random < 0.8) {
                if(obj.getCondition() == "Broken" ){
                obj.setCondtion("Used");
                //A Vehicle that becomes Used has its sales price increased 50%.
                obj.setsalesPrice(obj.getsalesPrice() * 1.5);
                count --;
                }
                if (obj.getCondition() == "Used" ) {
                    obj.setCondtion("Like New");
                    // A Vehicle that becomes  Like New has its sales price increased 25%
                    obj.setsalesPrice(obj.getsalesPrice() * 1.25);
                    count --;
                }

            }
            // Whether fixed or not, any Vehicle worked on will go down one class of cleanliness 

             else{
                if(obj.getCondition() == "Clean"){
                    obj.setCleanliness("Sparkling");
                }

                if(obj.getCondition() == "Sparkling"){
                    obj.setCleanliness("Dirty");
                }
            }
            


            // NEED TO DO THIS: Mechanics receive a bonus from each successful repair by Vehicle type.
            // for(Staff mm: Mechanics) {
                
            // }

            
        }
    }
    @Override
    void run() {
        System.out.println("Running Washing activity...");

         ArrayList<Vehicle> Pickup = dealership.getVehicles(VehicleType.PICKUP); 
         ArrayList<Vehicle> Car =  dealership.getVehicles(VehicleType.CAR);
         ArrayList<Vehicle> PerformanceCar = dealership.getVehicles(VehicleType.PERFORMANCE_CAR);


        ArrayList<Staff> Allmechanic = dealership.getStaff(StaffType.MECHANIC);
        int sizeMechanic = Allmechanic.size();
        int count = 2 * sizeMechanic;
        for(Staff obj: Allmechanic) {

        }
        repairVehicles(Allmechanic ,PerformanceCar, count);
        repairVehicles(Allmechanic ,Car, count);
        repairVehicles(Allmechanic ,Pickup, count);
    }
}