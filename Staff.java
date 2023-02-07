import java.util.ArrayList;
public class Staff {
    
private static int count = 0; 
private int ID;
private double DailyRate;
private double totalEarned;
private int totalDaysWorked;
private String name;


public Staff(double dailyRate) {
    this.ID = count++;
    this.DailyRate = dailyRate;
    this.name = "Staff_" + ID;
    this.totalEarned = 0;
    this.totalDaysWorked = 0;
}


public void work(int days, double bonus) {
    this.totalDaysWorked += days;
    this.totalEarned += (days * this.DailyRate) + bonus;
}

public int getId() {
    return this.ID;
}

public String getName() {
    return this.name;
}

public double getDailyRate() {
    return this.DailyRate;
}

public double getTotalEarned() {
    return this.totalEarned;
}

public int getTotalDaysWorked() {
    return this.totalDaysWorked;
}

}




class Salesperson extends Staff {
    public Salesperson()
    {
        super(100.0);

    }
}


class Mechanic extends Staff {
    public Mechanic()
    {
        super(80.0);
    }
}

class Intern extends Staff {
    public Intern()
    {
        super(20.0);
    }
}