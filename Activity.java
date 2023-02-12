abstract class Activity {
    abstract void run();
}

class Opening extends Activity {
    private Dealership dealership;

    public Opening(Dealership dealership) {
        this.dealership = dealership;
    }

    @Override
    void run() {
        System.out.println("Running Open activity...");

        // If there are less than 3 interns on opening, hire until intern count is reached.
        while (dealership.getStaff(StaffType.INTERN).size() < 3) {
            dealership.addStaff(StaffType.INTERN);
        }

        dealership.checkVehicles(VehicleType.CAR);
        dealership.checkVehicles(VehicleType.PERFORMANCE_CAR);
        dealership.checkVehicles(VehicleType.PICKUP);

        System.out.print(dealership.getBudget());
        // Check if there are less than 4 of each type of vehicle in the inventory. If so, add them.
    }
}
