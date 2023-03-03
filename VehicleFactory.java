public abstract class VehicleFactory {
    public abstract Vehicle createVehicle();
}

class CarFactory extends VehicleFactory {
    @Override
    public Vehicle createVehicle() {
        return new Car();
    }
}

class PerfCarFactory extends VehicleFactory {
    @Override
    public Vehicle createVehicle() {
        return new PerfCar();
    }
}

class PickupFactory extends VehicleFactory {
    @Override
    public Vehicle createVehicle() {
        return new Pickup();
    }
}

class MonsterTruckFactory extends VehicleFactory {
    @Override
    public Vehicle createVehicle() {
        return new MonsterTruck();
    }
}

class ElectricCarFactory extends VehicleFactory {
    @Override
    public Vehicle createVehicle() {
        return new ElectricCar();
    }
}

class MotorcycleFactory extends VehicleFactory {
    @Override
    public Vehicle createVehicle() {
        return new Motorcycle();
    }
}

class VehicleCreator {
    public static Vehicle createVehicle(Enums.VehicleType type) {
        VehicleFactory factory;
        switch (type) {
            case Car:
                factory = new CarFactory();
                break;
            case PerfCar:
                factory = new PerfCarFactory();
                break;
            case Pickup:
                factory = new PickupFactory();
                break;
            case MonsterTruck:
                factory = new MonsterTruckFactory();
                break;
            case ElectricCar:
                factory = new ElectricCarFactory();
                break;
            case Motorcycle:
                factory = new MotorcycleFactory();
                break;
            default:
                throw new IllegalArgumentException("Invalid vehicle type: " + type);
        }
        return factory.createVehicle();
    }
}
