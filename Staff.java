import java.util.ArrayList;
public class Staff {
    
private static int count = 0; 
private int ID;
private String name;
private double DailyRate;
private double totalEarned;
private int totalDaysWorked;


public Staff(double DailyRate)
{
    this.ID = count ++;
    this.name = "Staff" + ID;

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