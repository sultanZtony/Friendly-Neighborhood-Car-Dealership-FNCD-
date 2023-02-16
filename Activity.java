import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Random;
import java.util.concurrent.DelayQueue;
import java.io.FileWriter;

abstract class Activity {
}

class Open extends Activity {
    private Dealership dealership;

    public Open(Dealership dealership) {
        this.dealership = dealership;
    }

    void run() {
        // System.out.println("Running Open activity...");

        // If there are less than 3 interns on opening, hire until intern count is
        // reached.
        while (dealership.getInterns().size() < 3) {
            dealership.addStaff(StaffType.INTERN);
        }

        if(dealership.getBudget() < 0){
            dealership.addBudget(250000);
        }
        // Check if there are less than 4 of each type of vehicle in the inventory. If
        // so, add them.
        dealership.checkVehicles(VehicleType.CAR);
        dealership.checkVehicles(VehicleType.PERFORMANCE_CAR);
        dealership.checkVehicles(VehicleType.PICKUP);

    }
}

class Wash extends Activity {
    private Dealership dealership;

    public Wash(Dealership dealership) {
        this.dealership = dealership;
    }

    public void washVehicles(ArrayList<Vehicle> dirtyVehicles, ArrayList<Vehicle> cleanVehicles,
            ArrayList<Intern> arrayList) {
        Random rand = new Random();

        // Each intern can wash up to two vehicles per day
        for (Intern intern : arrayList) {
            int washedVehicles = 0;

            // Start w/ randomly selected Dirty Vehicles to wash
            while (washedVehicles < 2 && !dirtyVehicles.isEmpty()) {
                int randIndex = rand.nextInt(dirtyVehicles.size());
                Vehicle vehicle = dirtyVehicles.get(randIndex);
                double washBonus = intern.wash(vehicle);
                intern.addBonus(washBonus);
                dealership.subtractBudget(washBonus);

                if (vehicle.getCleanliness() == "Clean") {
                    cleanVehicles.add(vehicle);
                } else if (vehicle.getCleanliness() == "Sparkling") {
                    dirtyVehicles.remove(randIndex);
                }

                washedVehicles++;
            }

            // Move on to randomly selected Clean Vehicles to wash
            while (washedVehicles < 2 && !cleanVehicles.isEmpty()) {
                int randIndex = rand.nextInt(cleanVehicles.size());
                Vehicle vehicle = cleanVehicles.get(randIndex);
                double washBonus = intern.wash(vehicle);
                intern.addBonus(washBonus);
                dealership.subtractBudget(washBonus);

                if (vehicle.getCleanliness() == "Dirty") {
                    dirtyVehicles.add(vehicle);
                } else if (vehicle.getCleanliness() == "Sparkling") {
                    cleanVehicles.remove(randIndex);
                }

                washedVehicles++;
            }
        }
    }

    void run() {
         System.out.println("Running Washing activity...");

        washVehicles(dealership.getDirtyVehicles(), dealership.getCleanVehicles(), dealership.getInterns());
    }
}

class Repairing extends Activity {
    private Dealership dealership;

    public Repairing(Dealership dealership) {
        this.dealership = dealership;
    }

    public void repairVehicles(ArrayList<Vehicle> vehicles, ArrayList<Mechanic> Mechanics) {
        Random rand = new Random();

        // Each Mechanic can wash up to two vehicles per day
        for (Mechanic mechanic : Mechanics) {
            int repairedVehicles = 0;
            for (Vehicle vehicle : vehicles) {
                if (repairedVehicles <= 2) {
                    double repairBonus = mechanic.repair(vehicle);
                    mechanic.addBonus(repairBonus);
                    dealership.subtractBudget(repairBonus);
                    repairedVehicles++;
                }   
            }
        }
    }

    void run() {
     System.out.println("Running Repair activity...");

    repairVehicles(dealership.getVehicles(), dealership.getMechanics());

}
}


class Sell extends Activity {
    private static Random rand = new Random();
    private Dealership dealership;

    public Sell(Dealership dealership) {
        this.dealership = dealership;
    }

    public ArrayList<Buyer> genBuyers(int dayOfWeek) {
        ArrayList<Buyer> newBuyers = new ArrayList<>();
        if (dayOfWeek == Calendar.FRIDAY || dayOfWeek == Calendar.SATURDAY) {
            int randomInt = rand.nextInt(7) + 2;
            for (int i = 0; i < randomInt; i++) {
                newBuyers.add(new Buyer());
            }
        } else {
            int randomInt = rand.nextInt(6);
            for (int i = 0; i < randomInt; i++) {
                newBuyers.add(new Buyer());
            }
        }
        return newBuyers;
    }

    // Calendar.SUNDAY = 1 ... Calendar.SATURDAY = 7
    void run(int dayOfWeek) {
        System.out.println("Running Sell Activity in the Dealership...");

        ArrayList<Buyer> buyers = genBuyers(dayOfWeek);
        ArrayList<Salesperson> salespeople = dealership.getSalespeople();

        for (Buyer buyer : buyers) {
            int randIndex = rand.nextInt(salespeople.size());
            Salesperson salesperson = salespeople.get(randIndex);

            double randDouble = rand.nextDouble();
            if (salesperson.findVehicle(buyer, dealership) == null) {
                System.out.println("No Vehicles in inventory4");
                break;
            }
            else {
            Vehicle vehicleToSell = salesperson.findVehicle(buyer, dealership);

            if (buyer.getBuyingChances() >= randDouble) {
                dealership.addBudget(vehicleToSell.getSalesPrice());
                salesperson.addBonus(vehicleToSell.salesBonus());
                dealership.removeVehicle(vehicleToSell);
            }
        }
        }
    }
}

class End extends Activity {
    private Dealership dealership;

    public End(Dealership dealership) {
        this.dealership = dealership;
    }

    public void repairVehicles(ArrayList<Vehicle> brokenVehicles, ArrayList<Vehicle> usedVehicles,ArrayList<Mechanic> Mechanics) {

    }

    void run(int dayOfWeek) {
     System.out.println("Running Ending activity...");

     ArrayList<Salesperson> salespeople = dealership.getSalespeople();
     ArrayList<Mechanic> mechanics = dealership.getMechanics();
     ArrayList<Intern> interns = dealership.getInterns();
     ArrayList<Staff> staff = dealership.getStaff(); 
     

     ArrayList<Vehicle> vehicles = dealership.getVehicles();
     ArrayList<Vehicle> sold = dealership.getSoldVehicles();
    dealership.quit(staff,salespeople, mechanics, interns);
   // Produce a readable, tabular report of Staff members – with total days worked, total normal pay, total bonus pay, working or quit the FNCD

           // NEED to check whether the staff is working or has quit.




           try {
            // Creates a FileWriter
            FileWriter output
                = new FileWriter("Test.txt");
  
            // Writes the string to the file
            System.out.println("Day " + dayOfWeek);
            System.out.println("Staff: " + "\n");


            // Staff Report
            for(Staff obj1: staff){
                obj1.addDayWorked();
                output.write("Name: " + obj1.getName() + " Total days worked: " + obj1.getTotalDaysWorked() + " Total salay earned: " +obj1.getSalaryEarned() + " Total bonus earned: " + obj1.getBonusEarned() + " Status: " +obj1.getStatus() + "\n");
            }
            System.out.println("Cars: " + "\n");


            // Vehicles Report
            for (Vehicle obj : vehicles) {
                output.write("Name: " + obj.getName() + " Cost: " + obj.getCost() + " Sale Price: " + obj.getSalesPrice() + " Condition: " + obj.getCondition() + " Cleanliness " + obj.getCleanliness() + " Status: Stock" + "\n");
            }
            for (Vehicle obj : sold) {
                output.write("Name: " + obj.getName() + " Cost: " + obj.getCost() + " Sale Price: " + obj.getSalesPrice() + " Condition: " + obj.getCondition() + " Cleanliness " + obj.getCleanliness() + " Status: Sold" + "\n");
            }
            // Budget Report
            output.write("Operating budget" + dealership.getBudget() + " total sales $ for day"  + dealership.getTotalSalesDay());
  
            // Closes the writer
            output.close();
        }
  
        catch (Exception e) {
            e.getStackTrace();
        }   

}
}