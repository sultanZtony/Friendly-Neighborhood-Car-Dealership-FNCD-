import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Random;

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
        // System.out.println("Running Washing activity...");

        washVehicles(dealership.getDirtyVehicles(), dealership.getCleanVehicles(), dealership.getInterns());
    }
}

class Repairing extends Activity {
    private Dealership dealership;

    public Repairing(Dealership dealership) {
        this.dealership = dealership;
    }

    private void repairVehicles(ArrayList<Mechanic> Mechanics, ArrayList<Vehicle> vehicles, int count) {
        for (Vehicle obj : vehicles) {
            double random = Math.random();

            // Each Mechanic has an 80% chance of fixing any Vehicle worked on
            if (count > 0 && random < 0.8) {
                if (obj.getCondition() == "Broken") {
                    obj.setCondtion("Used");
                    // A Vehicle that becomes Used has its sales price increased 50%.
                    obj.setsalesPrice(obj.getsalesPrice() * 1.5);
                    count--;
                }
                if (obj.getCondition() == "Used") {
                    obj.setCondtion("Like New");
                    // A Vehicle that becomes Like New has its sales price increased 25%
                    obj.setsalesPrice(obj.getsalesPrice() * 1.25);
                    count--;
                }

            }
            // Whether fixed or not, any Vehicle worked on will go down one class of
            // cleanliness

            else {
                if (obj.getCondition() == "Clean") {
                    obj.setCleanliness("Sparkling");
                }

                if (obj.getCondition() == "Sparkling") {
                    obj.setCleanliness("Dirty");
                }
            }

            // NEED TO DO THIS: Mechanics receive a bonus from each successful repair by
            // Vehicle type.
            // for(Staff mm: Mechanics) {

            // }
        }
    }

    void run() {
        // System.out.println("Running Repair activity...");

        ArrayList<Mechanic> Allmechanic = dealership.getMechanics();
        int sizeMechanic = Allmechanic.size();
        int count = 2 * sizeMechanic;
        for (Staff obj : Allmechanic) {

        }
        repairVehicles(Allmechanic, dealership.getVehicles(), count);

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
            Vehicle vehicleToSell = salesperson.findVehicle(buyer, dealership);
            double randDouble = rand.nextDouble();
            if (buyer.getBuyingChances() >= randDouble) {
                dealership.addBudget(vehicleToSell.getSalesPrice());
                salesperson.addBonus(vehicleToSell.salesBonus());
                dealership.removeVehicle(vehicleToSell);
            }
        }
    }
}
