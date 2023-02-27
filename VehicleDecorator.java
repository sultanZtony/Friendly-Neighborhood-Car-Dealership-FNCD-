// Decorator Pattern for add-on purchases for Vehicles sold to Buyers
public abstract class VehicleDecorator extends Vehicle {
}

class ExtendedWarranty extends VehicleDecorator {
    private Vehicle vehicle;
    private double originalPrice;

    public ExtendedWarranty(Vehicle vehicle, double originalPrice) {
        this.vehicle = vehicle;
        this.originalPrice = originalPrice;
        }

    @Override
    public double getPrice() {
        return 0.2*originalPrice + vehicle.getPrice();
    }

}

class Undercoating extends VehicleDecorator {
    private Vehicle vehicle;
    private double originalPrice;

    public Undercoating(Vehicle vehicle, double originalPrice) {
        this.vehicle = vehicle;
        this.originalPrice = originalPrice;
        }

    @Override
    public double getPrice() {
        return 0.05*originalPrice + vehicle.getPrice();
    }

}

class RoadRescue extends VehicleDecorator {
    private Vehicle vehicle;
    private double originalPrice;

    public RoadRescue(Vehicle vehicle, double originalPrice) {
        this.vehicle = vehicle;
        this.originalPrice = originalPrice;
        }

    @Override
    public double getPrice() {
        return 0.02*originalPrice + vehicle.getPrice();
    }

}

class SatRadio extends VehicleDecorator {
    private Vehicle vehicle;
    private double originalPrice;

    public SatRadio(Vehicle vehicle, double originalPrice) {
        this.vehicle = vehicle;
        this.originalPrice = originalPrice;
        }

    @Override
    public double getPrice() {
        return 0.05*originalPrice + vehicle.getPrice();
    }

}


