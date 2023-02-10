import java.util.ArrayList;

public class Activity {
    private Dealership dealership;
    // Close in Sunday
    public Activity(Dealership dealership) {
        this.dealership = dealership;
        dealership.addstaff("Intern");
        dealership.addstaff("Intern");
        dealership.addstaff("Intern");
    
        dealership.addstaff("Sales");
        dealership.addstaff("Sales");
        dealership.addstaff("Sales");
    
        dealership.addstaff("Mechanic");
        dealership.addstaff("Mechanic");
        dealership.addstaff("Mechanic");



        // dealership.addVehicles("PerformanceCar");
        // dealership.addVehicles("PerformanceCar");
        // dealership.addVehicles("PerformanceCar");
        // dealership.addVehicles("PerformanceCar");

        // dealership.addVehicles("Car");
        // dealership.addVehicles("Car");
        // dealership.addVehicles("Car");
        // dealership.addVehicles("Car");


        // dealership.addVehicles("Pickup");
        // dealership.addVehicles("Pickup");
        // dealership.addVehicles("Pickup");
        // dealership.addVehicles("Pickup");

    }
    public void open() {
    
        ArrayList<Staff> interns = dealership.getInterns();
        ArrayList<Staff> sales = dealership.getSalespeople();
        ArrayList<Staff> mechanic = dealership.getMechanics();


        ArrayList<Vehicle> PerformanceCar = dealership.getPerformanceCar();
        ArrayList<Vehicle> Car = dealership.getCar();
        ArrayList<Vehicle> Pickup = dealership.getPickup();
        while (interns.size() < 3) {
            dealership.addstaff("Intern");
        }

        while(true){

            if(PerformanceCar.size() == 4 && Car.size() == 4  && Pickup.size() == 4){
                break;
            }
            else if (PerformanceCar.size() < 4) {
                dealership.addVehicles("PerformanceCar");
            }
            else if (Car.size() < 4) {
                dealership.addVehicles("Car");
            }
            else if (Pickup.size() < 4) {
                dealership.addVehicles("Pickup");
            }

        }


    }

    public void wash() {

    }

    public void repair() {

    }

    public void sell() {

    }

    public void end() {

    }
}
