public abstract class Vehicle {
    private String name;
    public Vehicle(String vehicleType, int id) {
        name = vehicleType + "_" + id;
    }

    public String getName() {
        return name;
    }
}

class PerformanceCar extends Vehicle {
    private static int id = 1;
    
    public PerformanceCar() {
        super("Perfomance Car", id++);
    } 
}

class Car extends Vehicle {
    private static int id = 1;

    public Car() {
        super("Car", id++);
    }
}

class Pickup extends Vehicle {
    private static int id = 1;

    public Pickup() {
        super("Pickup", id++);
    }
}
