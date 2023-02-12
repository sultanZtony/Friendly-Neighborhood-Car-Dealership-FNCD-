import java.util.Random;

public abstract class Vehicle {
    private static Random rand = new Random();

    private String name;
    private String type;
    private double cost;
    private String cleanliness;
    private String condition;
    private double salesPrice;

    public abstract double washBonus();
    public abstract double repairBonus();
    public abstract double salesBonus();

    public Vehicle(String vehicleType, int id, double initialCost) {
        this.name = vehicleType + "_" + id;
        this.type = vehicleType;
        this.cost = initialCost;
        this.cleanliness = genRandCleanliness();
        this.condition = genRandCondition();

        if (this.condition == "Used") {
            this.cost *= .80;
        } else if (this.condition == "Broken") {
            this.cost *= .50;
        }

        this.salesPrice = this.cost*2;
    }

    public double getCost() {
        return this.cost;
    }

    public String getCondition() {
        return this.condition;
    }

    public String getCleanliness() {
        return this.cleanliness;
    }

    private static String genRandCleanliness() {
        int randClean = rand.nextInt(100);

        if (randClean < 5) {
            return "sparkling";
        } else if (randClean < 40) {
            return "clean";
        } else {
            return "dirty";
        }
    }

    private static String genRandCondition() {
        int randCond = rand.nextInt(3);
        if (randCond == 0) {
            return "Used";
        } else if (randCond == 1) {
            return "Broken";
        } else {
            return "Like New";
        }
    }
}

class PerformanceCar extends Vehicle {
    private static int id = 1;
    
    public PerformanceCar() {
        super("Performance Car", id++, new Random().nextDouble(20000, 40000));
    }

    @Override
    public double washBonus() {
        return 70.0;
    }

    @Override
    public double repairBonus() {
        return 80.0;
    }

    @Override
    public double salesBonus() {
        return 90.0;
    }
}

class Car extends Vehicle {
    private static int id = 1;
    
    public Car() {
        super("Car", id++, new Random().nextDouble(10000, 20000));
    }

    @Override
    public double washBonus() {
        return 10.0;
    }

    @Override
    public double repairBonus() {
        return 20.0;
    }

    @Override
    public double salesBonus() {
        return 30.0;
    }
}

class Pickup extends Vehicle {
    private static int id = 1;
    
    public Pickup() {
        super("Pickup", id++, new Random().nextDouble(10000, 40000));
    }

    @Override
    public double washBonus() {
        return 40.0;
    }

    @Override
    public double repairBonus() {
        return 50.0;
    }

    @Override
    public double salesBonus() {
        return 60.0;
    }
}
