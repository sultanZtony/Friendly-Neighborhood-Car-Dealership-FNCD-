import java.util.Random;

public class Buyer {
    private static Random rand = new Random();
    
    BuyingType buyingType;
    VehicleType desiredVehicleType;
    double buyingChances;

    public Buyer() {
        int randInt = rand.nextInt(3);
        if (randInt == 0) {
            this.buyingType = BuyingType.JUST_LOOKING;
            this.buyingChances = .10;
        } else if (randInt == 1) {
            this.buyingType = BuyingType.WANTS_ONE;
            this.buyingChances = .40;
        } else {
            this.buyingType = BuyingType.NEEDS_ONE;
            this.buyingChances = .70;
        }

        randInt = rand.nextInt(3);
        if (randInt == 0) {
            this.desiredVehicleType = VehicleType.PERFORMANCE_CAR;
        } else if (randInt == 1) {
            this.desiredVehicleType = VehicleType.CAR;
        } else {
            this.desiredVehicleType = VehicleType.PICKUP;
        }
    }

    public void addBuyingChances(double percent) {
        this.buyingChances += percent;
    }

    public double getBuyingChances() {
        return this.buyingChances;
    }

    public BuyingType getBuyingType() {
        return buyingType;
    }

    public VehicleType getDesiredVehicleType() {
        return desiredVehicleType;
    }
}

