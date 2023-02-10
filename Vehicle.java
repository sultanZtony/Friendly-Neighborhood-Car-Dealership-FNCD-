import java.util.Random;
public abstract class Vehicle {
    private String name;
    // private String Condtion;
    // private Double CondtionDiscount;  
    // private Double Cost;

    public Vehicle(String vehicleType, int id) {
        name = vehicleType + "_" + id;

    }

    public String getName() {
        return name;
    }

    public String getCondtion() {
    Random Condtion = new Random();
    int random_result = Condtion.nextInt(3);

    if (random_result == 0) {
        return "Used";
    }
    else if (random_result == 1) {
        return "Broken";
    }
    else {
        return "New";
    }
}

    public Double getCondtionDiscount(String Condtion1) {
        if (Condtion1 == "Used") {
            return .2;
        }
        if (Condtion1 == "Broken") {
            return .5;
        }
        else  {
            return 0.0;
        }
    }
    public Double getCost(double orignalCost,double discount) {
        
        return orignalCost - discount*orignalCost;
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
