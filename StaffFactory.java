// The code below implements the Factory pattern for the creation of Staff.
public abstract class StaffFactory {
    public abstract Staff createStaff();
}

class InternFactory extends StaffFactory {
    @Override
    public Staff createStaff() {
        return new Intern();
    }
}

class MechanicFactory extends StaffFactory {
    @Override
    public Staff createStaff() {
        return new Mechanic();
    }
}

class SalespersonFactory extends StaffFactory {
    @Override
    public Staff createStaff() {
        return new Salesperson();
    }
}

class DriverFactory extends StaffFactory {
    @Override
    public Staff createStaff() {
        return new Driver();
    }
}

class StaffCreator {
    public static Staff createStaff(Enums.StaffType type) {
        StaffFactory factory;
        switch (type) {
            case Intern:
                factory = new InternFactory();
                break;
            case Mechanic:
                factory = new MechanicFactory();
                break;
            case Salesperson:
                factory = new SalespersonFactory();
                break;
            case Driver:
                factory = new DriverFactory();
                break;
            default:
                throw new IllegalArgumentException("Invalid staff type: " + type);
        }
        return factory.createStaff();
    }
}