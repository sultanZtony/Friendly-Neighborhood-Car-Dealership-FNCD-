import java.util.Random;
public abstract class Vehicle {
     private String name;
     private String Condtion;
     private String Cleanliness;
     private Double CondtionDiscount;  
     private Double Cost;

    public Vehicle(String vehicleType, int id) {
        this.name = vehicleType + "_" + id;
        Random Condtion = new Random();
        int random_result = Condtion.nextInt(3);
        String temp;
        Double tempDiscount;
        if (random_result == 0) {
            temp = "Used";
            tempDiscount = 0.2;
        }
        else if (random_result == 1) {
            temp = "Broken";
            tempDiscount = 0.5;

        }
        else {
            temp =  "New";
            tempDiscount = 0.0;

        }

        int randomClean = new Random().nextInt(100);

        if(randomClean < 5){
            this.Cleanliness = "sparkling";
        }
        else if(randomClean < 40){
            this.Cleanliness = "clean";
        }
        else{
            this.Cleanliness = "dirty";
        }


        Random price = new Random();

        if(vehicleType == "Perfomance Car"){
            Double cost = price.nextDouble(20000,40000);
            this.Cost = cost;
        }
        if(vehicleType == "Car"){
            Double cost = price.nextDouble(10000,20000);
            this.Cost = cost;
        }
        if(vehicleType == "Pickup Car"){
            Double cost = price.nextDouble(10000,40000);
            this.Cost = cost;
        }


        this.Condtion = temp;
        this.CondtionDiscount = tempDiscount;
    }

    public String getName() {
        return name;
    }

    public String getCondtion() {
        return this.Condtion;
}

    public Double getCondtionDiscount() {
        return this.CondtionDiscount;
    }

    public Double getCost(double orignalCost) {
        
        return orignalCost - this.CondtionDiscount*orignalCost;
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
