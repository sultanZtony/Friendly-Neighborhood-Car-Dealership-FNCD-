import java.util.ArrayList;
import java.util.Arrays;
import java.util.EnumSet;
import java.util.List;
import java.util.Random;

import Enums.VehicleType;

// import javax.xml.catalog.GroupEntry.PreferType;

public abstract class Vehicle {
    private static final String VehicleType = null;
    String name;
    Enums.VehicleType type;
    Enums.Condition condition;
    Enums.Cleanliness cleanliness;
    double cost;
    double price;
    double repair_bonus;
    double wash_bonus;
    double sale_bonus;
    Vehicle () {
        // all vehicles have the same cleanliness arrival chance
        double chance = Utility.rnd();
        if (chance <= .05) cleanliness = Enums.Cleanliness.Sparkling;
        else if (chance>.05 && chance<=.4) cleanliness = Enums.Cleanliness.Clean;
        else cleanliness = Enums.Cleanliness.Dirty;
        // all vehicles have the same condition arrival chance (even chance of any)
        condition = Utility.randomEnum(Enums.Condition.class);
    }

    // utility for getting adjusted cost by condition
    double getCost(int low,int high) {
        double cost = Utility.rndFromRange(low, high);
        if (condition== Enums.Condition.Used) cost = cost*.8;
        if (condition== Enums.Condition.Broken) cost = cost*.5;
        return cost;
    }

    // utility for getting Vehicles by Type
    // You could do this with getClass instead of Type, but I use the enum
    // because it's clearer to me (less Java-y)
    static ArrayList<Vehicle> getVehiclesByType(ArrayList<Vehicle> vehicleList, Enums.VehicleType t) {
        ArrayList<Vehicle> subclassInstances = new ArrayList<>();
        for (Vehicle v : vehicleList) {
            if (v.type == t) subclassInstances.add(v);
        }
        return subclassInstances;
    }

    // utility for returning a set of vehicles of a random type that's eligible for racing.
    static ArrayList<Vehicle> getRaceVehicles(ArrayList<Vehicle> vehicleList) {
        ArrayList<Vehicle> selectedVehicles = new ArrayList<>();
        Random rand = new Random();
        Enums.VehicleType[] types = {Enums.VehicleType.Pickup, Enums.VehicleType.PerfCar, Enums.VehicleType.Motorcycle, Enums.VehicleType.MonsterTruck};
        Enums.VehicleType selectedType = types[rand.nextInt(types.length)];
        int vehicleCount = 0;
        for (Vehicle v : vehicleList) {
            if (v.type == selectedType && v.condition != Enums.Condition.Broken && vehicleCount < 3) {
                selectedVehicles.add(v);
                vehicleCount += 1;
            }
        }
        return selectedVehicles;
    }


    // Utility for finding out how many of a Vehicle there are
    static int howManyVehiclesByType(ArrayList<Vehicle> vehicleList, Enums.VehicleType t) {
        int n = 0;
        for (Vehicle v: vehicleList) {
            if (v.type == t) n++;
        }
        return n;
    }


    // Define a price function to be used in Decorator.
    public abstract double getPrice();
}

class Car extends Vehicle {
    // could make the name list longer to avoid as many duplicates if you like...
    static List<String> names = Arrays.asList("Probe","Escort","Taurus","Fiesta");
    static Namer namer = new Namer(names);
    Car() {
        super();
        type = Enums.VehicleType.Car;
        name = namer.getNext();  // every new car gets a new name
        cost = getCost(10000,20000);
        price = cost * 2;
        repair_bonus = 100;
        wash_bonus = 20;
        sale_bonus = 500;
    }

    @Override
    public double getPrice() {
        return price;
    }
}

class PerfCar extends Vehicle {
    static List<String> names = Arrays.asList("Europa","Cayman","Corvette","Mustang");
    static Namer namer = new Namer(names);
    PerfCar() {
        super();
        type = Enums.VehicleType.PerfCar;
        name = namer.getNext();  // every new perf car gets a unique new name
        cost = getCost(20000,40000);
        price = cost * 2;
        repair_bonus = 300;
        wash_bonus = 100;
        sale_bonus = 1000;
    }

    @Override
    public double getPrice() {
        return price;
    }
}

class Pickup extends Vehicle {
    static List<String> names = Arrays.asList("Ranger","F-250","Colorado","Tundra");
    static Namer namer = new Namer(names);
    Pickup() {
        super();
        type = Enums.VehicleType.Pickup;
        name = namer.getNext();  // every new truck gets a unique new name
        cost = getCost(10000,40000);
        price = cost * 2;
        repair_bonus = 200;
        wash_bonus = 75;
        sale_bonus = 750;
    }

    @Override
    public double getPrice() {
        return price;
    }
}

class ElectricCar extends Vehicle {
    static List<String> names = Arrays.asList("Tesla", "Mercedes", "BMW", "Leaf");
    static Namer namer = new Namer(names);
    private double initialRange;
    private double range;

    ElectricCar() {
        super();
        type = Enums.VehicleType.ElectricCar;
        name = namer.getNext();
        cost = getCost(20000, 40000);
        price = cost * 2;
        repair_bonus = 300;
        wash_bonus = 100;
        sale_bonus = 1000;
        // Electric Cars have a unique Range attribute, initially set randomly from 60
        // to 400 miles
        initialRange = Utility.rndFromRange(60, 400);
        setRange();
    }

    public void setRange() {
        if (condition == Enums.Condition.LikeNew) {
            range = initialRange + 100;
        } else {
            range = initialRange;
        }
    }

    @Override
    public double getPrice() {
        return price;
    }
}


class MonsterTruck extends Vehicle {
    static List<String> names = Arrays.asList("Avenger","Bigfoot","Madusa","Zombie");
    static Namer namer = new Namer(names);
    MonsterTruck() {
        super();
        type = Enums.VehicleType.MonsterTruck;
        name = namer.getNext();  // every new truck gets a unique new name
        cost = getCost(10000,40000);
        price = cost * 2;
        repair_bonus = 200;
        wash_bonus = 75;
        sale_bonus = 750;
    }

    @Override
    public double getPrice() {
        return price;
    }
}

// Unfinished, add engine size and logic
class Motorcycle extends Vehicle {
    static List<String> names = Arrays.asList("Harley","Honda","Yamaha","Ducati");
    static Namer namer = new Namer(names);
    private int engineSize;
    Motorcycle() {
        super();
        type = Enums.VehicleType.Motorcycle;
        name = namer.getNext();
        cost = getCost(10000,40000);
        price = cost * 2;
        repair_bonus = 200;
        wash_bonus = 75;
        sale_bonus = 750;
        engineSize = randomEngineSize();
    }

    public int randomEngineSize() {
        Random rand = new Random();
        double std = 300;
        double mean = 700;
        double minVal = 50;

        int engineSize;
        do {
            engineSize = (int) Math.round(rand.nextGaussian() * std + mean);

        } while (engineSize < minVal);
        return engineSize;
    }

    @Override
    public double getPrice() {
        return price;
    }
}
