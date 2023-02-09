public abstract class Staff {
    private double dailyRate;
    private double salaryEarned;
    private double bonusEarned;
    private int totalDaysWorked;
    private String name;

    public Staff(String staffType, int id, double dailyRate) {
        this.dailyRate = dailyRate;
        name = staffType + "_" + id;
        salaryEarned = 0;
        bonusEarned = 0;
        totalDaysWorked = 0;
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

    public double bonusEarned() {
        return bonusEarned;
    }

    public int getTotalDaysWorked() {
        return totalDaysWorked;
    }

}

class Salesperson extends Staff {
    private static int id = 1;

    public Salesperson() {
        super("Salesperson", id++, 100.0);

    }
}

class Mechanic extends Staff {
    private static int id = 1;

    public Mechanic() {
        super("Mechanic", id++, 50.0);
    }
}

class Intern extends Staff {
    private static int id = 1;

    public Intern() {
        super("Intern", id++, 20.0);
    }
}