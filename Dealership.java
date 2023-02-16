import java.lang.reflect.Array;
import java.util.ArrayList;

public class Dealership {
    private double operatingBudget = 500000;

    private ArrayList<Salesperson> salespeople = new ArrayList<Salesperson>();
    private ArrayList<Mechanic> mechanics = new ArrayList<Mechanic>();
    private ArrayList<Intern> interns = new ArrayList<Intern>();
    private ArrayList<Staff> departedStaff = new ArrayList<Staff>();
    private ArrayList<Staff> staff = new ArrayList<Staff>();


    private ArrayList<PerformanceCar> performanceCars = new ArrayList<PerformanceCar>();
    private ArrayList<Car> cars = new ArrayList<Car>();
    private ArrayList<Pickup> pickups = new ArrayList<Pickup>();

    private ArrayList<Vehicle> soldVehicles = new ArrayList<Vehicle>();

    public Dealership() {
        // Initialize Staff prior to Day 1
        for (int i = 0; i < 3; i++) {
            addStaff(StaffType.SALESPERSON);
        }

        for (int i = 0; i < 3; i++) {
            addStaff(StaffType.MECHANIC);
        }

        for (int i = 0; i < 3; i++) {
            addStaff(StaffType.INTERN);
        }
    }

    public ArrayList<Vehicle> getSoldVehicles() {
        return soldVehicles;
    }
    public Double getTotalSalesDay() {
        double salesToday = 0;
        for (Vehicle sold : soldVehicles) {
            salesToday += sold.getSalesPrice();
        }
        return salesToday;
    }
    public void removeVehicle(Vehicle vehicle) {
        if (vehicle instanceof PerformanceCar) {
            performanceCars.remove(vehicle);
        }
        if (vehicle instanceof Car) {
            cars.remove(vehicle);
        }
        if (vehicle instanceof Pickup) {
            pickups.remove(vehicle);
        }
        soldVehicles.add(vehicle);
    }

    public void addBudget(double amt) {
        this.operatingBudget += amt;
    }

    public Vehicle getMostExpensiveByType(String type) {
        Vehicle mostExpensive = null;
        double maxPrice = 0;
        for (Vehicle vehicle : getVehicles()) {
            if (vehicle.getType() == type && vehicle.getCost() > maxPrice) {
                mostExpensive = vehicle;
                maxPrice = vehicle.getCost();
            }
        }
        return mostExpensive;
    }

    public PerformanceCar getMostExpensivePerformance() {
        PerformanceCar mostExpensive = null;
        double maxPrice = 0;
        for (PerformanceCar perfCar : performanceCars) {
            if (perfCar.getCondition() != "Broken" && perfCar.getCost() > maxPrice) {
                if (perfCar.getCost() > maxPrice) {
                    mostExpensive = perfCar;
                }
            }
        }
        return mostExpensive;
    }

    public ArrayList<Vehicle> getVehicles() {
        ArrayList<Vehicle> vehicles = new ArrayList<Vehicle>();
        vehicles.addAll(performanceCars);
        vehicles.addAll(cars);
        vehicles.addAll(pickups);

       return vehicles;
    }

    public ArrayList<Staff> getStaff() {
        ArrayList<Staff> staff = new ArrayList<Staff>();
        staff.addAll(salespeople);
        staff.addAll(mechanics);
        staff.addAll(interns);
        staff.addAll(departedStaff);

       return staff;
    }
    public ArrayList<Staff> getDepartedStaff() {
  
        return departedStaff;
    }
    public ArrayList<Intern> getInterns() {
        return interns;
    }

    public ArrayList<Mechanic> getMechanics() {
        return mechanics;
    }

    public ArrayList<Salesperson> getSalespeople() {
        return salespeople;
    }

    public ArrayList<PerformanceCar> getPerformanceCars() {
        return performanceCars;
    }

    public ArrayList<Car> getCars() {
        return cars;
    }

    public ArrayList<Pickup> getPickups() {
        return pickups;
    }

    public void subtractBudget(double expense) {
        this.operatingBudget -= expense;
    }

    public void departStaff(String Staff)
    {
        switch (Staff) {
            case "sales":
            salespeople.get(0).setStatus("Departed");
            departedStaff.add(salespeople.get(0));
            salespeople.remove(0);
                
                break;
            case "mechanic":
            mechanics.get(0).setStatus("Departed");
            departedStaff.add(mechanics.get(0));
            mechanics.remove(0);
                break;

            case "intern":
            interns.get(0).setStatus("Departed");
            departedStaff.add(interns.get(0));
            interns.remove(0);
                break;
            default:
                break;
        }
    }
    public void quit(ArrayList<Staff> staff ,ArrayList<Salesperson> sales,ArrayList<Mechanic> mechanics, ArrayList<Intern> interns ){
        double random1 = Math.random();
        double random2 = Math.random();
        double random3 = Math.random();

        if (random1 <= 0.1) {
            departStaff("sales");
            if (interns.size() > 0) {
                departStaff("intern");
                addStaff(StaffType.SALESPERSON);
            }
        }
        if (random2 <= 0.1) {
            departStaff("mechanic");
            if (interns.size() > 0) {
                departStaff("intern");
                addStaff(StaffType.MECHANIC);
            }

        }
        if (random3 <= 0.1) {
            departStaff("intern");
        }
    }
    public ArrayList<Vehicle> getDirtyVehicles() {
        ArrayList<Vehicle> dirtyVehicles = new ArrayList<Vehicle>();
        for (Vehicle perfCar : performanceCars) {
            if (perfCar.getCleanliness() == "Dirty") {
                dirtyVehicles.add(perfCar);
            }
        }
        for (Vehicle car : cars) {
            if (car.getCleanliness() == "Dirty") {
                dirtyVehicles.add(car);
            }
        }
        for (Vehicle pickup : pickups) {
            if (pickup.getCleanliness() == "Dirty") {
                dirtyVehicles.add(pickup);
            }
        }
        return dirtyVehicles;
    }

    public ArrayList<Vehicle> getCleanVehicles() {
        ArrayList<Vehicle> cleanVehicles = new ArrayList<Vehicle>();
        for (Vehicle perfCar : performanceCars) {
            if (perfCar.getCleanliness() == "Clean") {
                cleanVehicles.add(perfCar);
            }
        }
        for (Vehicle car : cars) {
            if (car.getCleanliness() == "Clean") {
                cleanVehicles.add(car);
            }
        }
        for (Vehicle pickup : pickups) {
            if (pickup.getCleanliness() == "Clean") {
                cleanVehicles.add(pickup);
            }
        }
        return cleanVehicles;
    }


    public ArrayList<Vehicle> getBrokenVehicles() {
        ArrayList<Vehicle> BrokenVehicles = new ArrayList<Vehicle>();
        for (Vehicle perfCar : performanceCars) {
            if (perfCar.getCondition() == "Broken") {
                BrokenVehicles.add(perfCar);
            }
        }
        for (Vehicle car : cars) {
            if (car.getCondition() == "Broken") {
                BrokenVehicles.add(car);
            }
        }
        for (Vehicle pickup : pickups) {
            if (pickup.getCondition() == "Broken") {
                BrokenVehicles.add(pickup);
            }
        }
        return BrokenVehicles;
    }

    public ArrayList<Vehicle> getUsedVehicles() {
        ArrayList<Vehicle> UsedVehicles = new ArrayList<Vehicle>();
        for (Vehicle perfCar : performanceCars) {
            if (perfCar.getCondition() == "Used") {
                UsedVehicles.add(perfCar);
            }
        }
        for (Vehicle car : cars) {
            if (car.getCondition() == "Used") {
                UsedVehicles.add(car);
            }
        }
        for (Vehicle pickup : pickups) {
            if (pickup.getCondition() == "Used") {
                UsedVehicles.add(pickup);
            }
        }
        return UsedVehicles;
    }

    public ArrayList<Vehicle> getNewVehicles() {
        ArrayList<Vehicle> newVehicles = new ArrayList<Vehicle>();
        for (Vehicle perfCar : performanceCars) {
            if (perfCar.getCondition() == "Like new") {
                newVehicles.add(perfCar);
            }
        }
        for (Vehicle car : cars) {
            if (car.getCondition() == "Like new") {
                newVehicles.add(car);
            }
        }
        for (Vehicle pickup : pickups) {
            if (pickup.getCondition() == "Like new") {
                newVehicles.add(pickup);
            }
        }
        return newVehicles;
    }

    public double getBudget() {
        return this.operatingBudget;
    }

    // This code will only return an ArrayList<Staff>, it will not resolve to a list
    // of a particular Type

    // public ArrayList<Staff> getStaff(StaffType type) {
    // switch (type) {
    // case SALESPERSON:
    // return salespeople;
    // case MECHANIC:
    // return mechanics;
    // case INTERN:
    // return interns;
    // default:
    // throw new IllegalArgumentException("Invalid staff type: " + type);
    // }
    // }

    public void addStaff(StaffType type) {
        switch (type) {
            case SALESPERSON:
                if (salespeople.size() < 3) {
                    salespeople.add(new Salesperson());
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

    // This code will only return an ArrayList<Vehicle>, it will not resolve to a
    // list of a particular Type

    // public ArrayList<Vehicle> getVehicles(VehicleType type) {
    // switch (type) {
    // case PERFORMANCE_CAR:
    // return performanceCars;
    // case CAR:
    // return cars;
    // case PICKUP:
    // return pickups;
    // default:
    // throw new IllegalArgumentException("Invalid vehicle type: " + type);
    // }
    // }

    public void addVehicle(VehicleType type) {
        switch (type) {
            case PERFORMANCE_CAR:
                PerformanceCar newPerfCar = new PerformanceCar();
                performanceCars.add(new PerformanceCar());
                operatingBudget = operatingBudget - newPerfCar.getCost();
                break;
            case CAR:
                Car newCar = new Car();
                cars.add(newCar);
                operatingBudget = operatingBudget - newCar.getCost();
                break;
            case PICKUP:
                Pickup pickup = new Pickup();
                pickups.add(pickup);
                operatingBudget = operatingBudget - pickup.getCost();
                break;
            default:
                throw new IllegalArgumentException("Invalid vehicle type: " + type);
        }
    }

    public void checkVehicles(VehicleType type) {
        switch (type) {
            case CAR:
                while (cars.size() < 4) {
                    addVehicle(VehicleType.CAR);
                }
                break;
            case PERFORMANCE_CAR:
                while (performanceCars.size() < 4) {
                    addVehicle(VehicleType.PERFORMANCE_CAR);
                }
                break;
            case PICKUP:
                while (pickups.size() < 4) {
                    addVehicle(VehicleType.PICKUP);
                }
                break;
            default:
                throw new IllegalArgumentException("Invalid vehicle type: " + type);
        }
    }
}
