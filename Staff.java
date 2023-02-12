public abstract class Staff {
    private String name;
    private double dailyRate;
    private double salaryEarned;
    private double bonusEarned;
    private int totalDaysWorked;

    public Staff(String staffType, int id, double dailyRate) {
        name = staffType + "_" + id;
        this.dailyRate = dailyRate;
        salaryEarned = 0;
        bonusEarned = 0;
        totalDaysWorked = 0;
    }

    public void setDailyRate(double dailyRate) {
        this.dailyRate = dailyRate;
    }

    public String getName() {
        return name;
    }

    public double getDailyRate() {
        return dailyRate;
    }

    public double getSalaryEarned() {
        salaryEarned = dailyRate * totalDaysWorked;
        return salaryEarned;
    }

    public double getBonusEarned() {
        return bonusEarned;
    }

    public int getTotalDaysWorked() {
        return totalDaysWorked;
    }

}

class Salesperson extends Staff {
    private static int id = 1;

    public Salesperson() {
        super("Salesperson", id++, 100);
    }
}

class Mechanic extends Staff {
    private static int id = 1;

    public Mechanic() {
        super("Mechanic", id++, 50);
    }
}

class Intern extends Staff {
    private static int id = 1;

    public Intern() {
        super("Intern", id++, 25);
    }
}
