public abstract class Staff {
    private String name;
    private String status;
    private double dailyRate;
    private double salaryEarned;
    private double bonusEarned;
    private int totalDaysWorked;

    public Staff(String staffType, int id, double dailyRate, String status) {
        name = staffType + "_" + id;
        this.dailyRate = dailyRate;
        salaryEarned = 0;
        bonusEarned = 0;
        totalDaysWorked = 0;
        this.status = status;
    }

    public void setDailyRate(double dailyRate) {
        this.dailyRate = dailyRate;
    }

    public String getName() {
        return name;
    }

    public double getDailyRate() {
        return dailyRate;
    }

    public double getSalaryEarned() {
        salaryEarned = dailyRate * totalDaysWorked;
        return salaryEarned;
    }

    public double getBonusEarned() {
        return bonusEarned;
    }
    public void addBonus(double bonus){
        this.bonusEarned += bonus;
    }
    public int getTotalDaysWorked() {
        return totalDaysWorked;
    }
    public void addDayWorked(){
        this.totalDaysWorked++;
    }
    public void setStatus(String status) {
        this.status = status;
    }
    public String getStatus() {
        return status;
    }
}

class Salesperson extends Staff {
    private static int id = 1;

    public Salesperson() {
        super("Salesperson", id++, 100, "Working");
    }

    public Vehicle findVehicle(Buyer buyer, Dealership dealership) {
        Vehicle mostExpensive = null;
        double maxPrice = 0;

        switch (buyer.getDesiredVehicleType()) {
            case PERFORMANCE_CAR:
            for (Vehicle perfCar : dealership.getPerformanceCars()) {
                if (perfCar.getCondition() != "Broken" && perfCar.getCost() > maxPrice) {
                    mostExpensive = perfCar;
                    maxPrice = perfCar.getCost();
                }
            }
            if (mostExpensive != null) {
                if (mostExpensive.getCondition() == "Like New") {
                    buyer.addBuyingChances(.10);
                }
                if (mostExpensive.getCleanliness() == "Sparkling") {
                    buyer.addBuyingChances(.10);
                }
                return mostExpensive;
            }
            break;
            case CAR:
            for (Vehicle car : dealership.getCars()) {
                if (car.getCondition() != "Broken" && car.getCost() > maxPrice) {
                    mostExpensive = car;
                    maxPrice = car.getCost();
                }
            }
            if (mostExpensive != null) {
                if (mostExpensive.getCondition() == "Like New") {
                    buyer.addBuyingChances(.10);
                }
                if (mostExpensive.getCleanliness() == "Sparkling") {
                    buyer.addBuyingChances(.10);
                }
                return mostExpensive;
            }
            break;
            case PICKUP:
            for (Vehicle pickup : dealership.getPickups()) {
                if (pickup.getCondition() != "Broken" && pickup.getCost() > maxPrice) {
                    mostExpensive = pickup;
                    maxPrice = pickup.getCost();
                }
            }
            if (mostExpensive != null) {
                if (mostExpensive.getCondition() == "Like New") {
                    buyer.addBuyingChances(.10);
                }
                if (mostExpensive.getCleanliness() == "Sparkling") {
                    buyer.addBuyingChances(.10);
                }
                return mostExpensive;
            }
            break;
            default:
                throw new IllegalArgumentException("Invalid type: " + buyer.buyingType);
        }
        // Sell the most expensive vehicle left in the inventory
        maxPrice = 0;
        for (Vehicle vehicle : dealership.getVehicles()) {
            if (vehicle.getCondition() != "Broken" && vehicle.getCost() > maxPrice) {
                mostExpensive = vehicle;
                maxPrice = vehicle.getCost();
            }
        }
        if (mostExpensive != null) {
            buyer.addBuyingChances(-.20);
            return mostExpensive;
        }
        return null;
    }
}

// public PerformanceCar getMostExpensivePerformance() {
//     PerformanceCar mostExpensive = null;
//     double maxPrice = 0;
//     for (PerformanceCar perfCar : performanceCars) {
//         if (perfCar.getCondition() != "Broken" && perfCar.getCost() > maxPrice) {
//             if (perfCar.getCost() > maxPrice) {
//                 mostExpensive = perfCar;
//             }
//         }
//     }
//     return mostExpensive;
// }

class Mechanic extends Staff {
    private static int id = 1;

    public Mechanic() {
        super("Mechanic", id++, 50, "Working");
    }
    
    public double repair(Vehicle vehicle) {
 
            double random = Math.random();
            double repairBonus = 0;

            // Each Mechanic has an 80% chance of fixing any Vehicle worked on
            if (random < 0.8) {
                if (vehicle.getCondition() == "Broken") {
                    vehicle.setCondtion("Used");
                    // A Vehicle that becomes Used has its sales price increased 50%.
                    vehicle.setsalesPrice(vehicle.getsalesPrice() * 1.5);
                }
                if (vehicle.getCondition() == "Used") {
                    vehicle.setCondtion("Like New");
                    // A Vehicle that becomes Like New has its sales price increased 25%
                    vehicle.setsalesPrice(vehicle.getsalesPrice() * 1.25);
                }
                repairBonus = vehicle.repairBonus();
            }
            // Whether fixed or not, any Vehicle worked on will go down one class of
            // cleanliness


    return repairBonus;
}
}

class Intern extends Staff {
    private static int id = 1;

    public Intern() {
        super("Intern", id++, 25, "Working");
    }

    public double wash(Vehicle vehicle) {
        double random = Math.random();
        double washBonus = 0;

        // A Dirty Vehicle that is washed has an 80% chance of becoming Clean and 10% chance of becoming Sparkling 
        if (vehicle.getCleanliness() == "Dirty" && random <= 0.8) {
            vehicle.setCleanliness("Clean");
        } else if (vehicle.getCleanliness() == "Dirty" && random <= 0.1) {
            vehicle.setCleanliness("Sparkling");
             washBonus = vehicle.washBonus();
        } else {
            vehicle.setCleanliness("Dirty");
        }

        // A Clean Vehicle that is washed has a 5% chance of becoming Dirty and a 30% chance of becoming Sparkling
        if (vehicle.getCleanliness() == "Clean" && random <= 0.05) {
            vehicle.setCleanliness("Dirty");
        } else if (vehicle.getCleanliness() == "Dirty" && random <= 0.3) {
            vehicle.setCleanliness("Sparkling");
            washBonus = vehicle.washBonus();
        } else {
            vehicle.setCleanliness("Clean");
        }

        return washBonus;
    }
}
// class DepartedStaff extends Staff {
//     private static int id = 1;

//     public DepartedStaff() {
//         super("Departed", id++, 0, "Departed");
//     }

// }
