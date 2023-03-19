import javax.lang.model.util.Types;

// Unused Imports
// import org.jcp.xml.dsig.internal.dom.Utils;
// import Enums.Condition;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Arrays;

public abstract class Staff implements SysOut {
    String name;
    double salary;  // daily salary
    double salaryEarned;
    double bonusEarned;
    Enums.StaffType type;
    int daysWorked;
    Enums.WashMethods washingMethods;
    int winCount;
    double winBonus;
    Enums.Health health;
    Staff() {
        salaryEarned = 0;
        bonusEarned = 0;
        daysWorked = 0;
    }

    // utility for getting Staff by Type
    // You could do this with getClass instead of Type, but I use the enum
    // because it's clearer to me - less Java-y
    static ArrayList<Staff> getStaffByType(ArrayList<Staff> staffList, Enums.StaffType t) {
        ArrayList<Staff> subclassInstances = new ArrayList<>();
        for (Staff s : staffList) {
            if (s.type == t) subclassInstances.add(s);
        }
        return subclassInstances;
    }

    //Utility for finding out how many of a Staff type there are
    static int howManyStaffByType(ArrayList<Staff> staffList, Enums.StaffType t) {
        int n = 0;
        for (Staff s: staffList) {
            if (s.type == t) n++;
        }
        return n;
    }
}

class Intern extends Staff {
    static List<String> names = Arrays.asList("Fred","Ethel","Lucy","Desi");
    static Namer namer = new Namer(names);
    Intern() {
        super();
        type = Enums.StaffType.Intern;
        name = namer.getNext();  // every new intern gets a new name
        salary = 60; // daily salary
        washingMethods= getWashingMethod();
    }

    // The following code below implements the Strategy Pattern for getting a Washing Method.
    // Details of the Strategy Pattern can be found in our UML Class diagram by following this link below: 
    // https://drive.google.com/file/d/1rY5RrPBjgLfuvFG_8AAndh7k1dU8KAtq/view?usp=sharing
    
    // How an intern washes cars
    // TODO: There's some duplication in this - it's a little clumsy - refactor me!
    Enums.WashMethods getWashingMethod() {
        int rand = Utility.rndFromRange(1, 3);
        switch (rand) {
            case 1:
                return Enums.WashMethods.Chemical;
            case 2:
                return Enums.WashMethods.Detailed;
            default:
                return Enums.WashMethods.ElbowGrease;
        }
    }

    double washVehicles(ArrayList<Vehicle> vList) {
        Publisher publisher = Publisher.getInstance();
        double washBonus = 0;
        int washCount = 0;
        Enums.Cleanliness startAs;
        Enums.WashMethods washMethod = this.washingMethods;
        //  Announce what Washing Method is in use. 
        out("Intern "+name+" is using "+washMethod+" washing method.");

        for (Vehicle v:vList) {
            // wash the first dirty car I see
            if (v.cleanliness == Enums.Cleanliness.Dirty) {
                washCount += 1;
                startAs = Enums.Cleanliness.Dirty;
                double chance = Utility.rnd();
                switch (washMethod) {
                    case Chemical:
                    // 80% chance of Clean, 10% chance of Sparkling, and a 10% chance of Vehicle becoming Broken (if not already) 
                        if (chance <= .8) v.cleanliness = Enums.Cleanliness.Clean;
                        if (chance >.8 && chance <=.9) {
                            v.cleanliness = Enums.Cleanliness.Sparkling;
                            bonusEarned += v.wash_bonus;
                            washBonus += v.wash_bonus;
                            out("Intern "+name+" got a bonus of "+Utility.asDollar(v.wash_bonus)+"!");
                        }
                        if (chance >.9) v.condition = Enums.Condition.Broken;
                        break;
                        // 70% chance of Clean, 5% chance of Sparkling, and 10% chance of Vehicle becoming Like New (if not already) 
                    case ElbowGrease:
                        if (chance <= .7) v.cleanliness = Enums.Cleanliness.Clean;
                        if (chance >.7 && chance <=.75) {
                            v.cleanliness = Enums.Cleanliness.Sparkling;
                            bonusEarned += v.wash_bonus;
                            washBonus += v.wash_bonus;
                            out("Intern "+name+" got a bonus of "+Utility.asDollar(v.wash_bonus)+"!");
                        }
                        if (chance >.9) v.condition = Enums.Condition.LikeNew;
                        break;
                        // 60% chance of Clean, 20% chance of Sparkling, No special effects as above methods 
                    case Detailed:
                        if (chance <= .6) v.cleanliness = Enums.Cleanliness.Clean;
                        if (chance >.6 && chance <=.8) {
                            v.cleanliness = Enums.Cleanliness.Sparkling;
                            bonusEarned += v.wash_bonus;
                            washBonus += v.wash_bonus;
                            out("Intern "+name+" got a bonus of "+Utility.asDollar(v.wash_bonus)+"!");
                        }
                        break;
                }
                out("Intern "+name+" washed "+v.name+" "+startAs+" to "+v.cleanliness);
                if (washCount == 2) break;
            }
        }
        if (washCount<2) {
            for (Vehicle v:vList) {
                // wash the first clean car I see
                if (v.cleanliness == Enums.Cleanliness.Clean) {
                    washCount += 1;
                    startAs = Enums.Cleanliness.Clean;
                    double chance = Utility.rnd();
                    switch (washMethod) {
                        // 10% chance of Dirty, 20% chance of Sparkling, and 10% chance of Vehicle becoming Like New (if not already) 
                        case Chemical:
                            if (chance <= .1) v.cleanliness = Enums.Cleanliness.Dirty;
                            if (chance >.1 && chance <=.3) {
                                v.cleanliness = Enums.Cleanliness.Sparkling;
                                bonusEarned += v.wash_bonus;
                                washBonus += v.wash_bonus;
                                out("Intern "+name+" got a bonus of "+Utility.asDollar(v.wash_bonus)+"!");
                            }
                            if (chance >.9) v.condition = Enums.Condition.Broken;
                            break;

                            // 15% chance of Dirty, 15% chance of Sparkling, and 10% chance of Vehicle becoming Like New (if not already) 
                            case ElbowGrease:
                            if (chance <= .15) v.cleanliness = Enums.Cleanliness.Dirty;
                            if (chance >.15 && chance <=.3) {
                                v.cleanliness = Enums.Cleanliness.Sparkling;
                                bonusEarned += v.wash_bonus;
                                washBonus += v.wash_bonus;
                                out("Intern "+name+" got a bonus of "+Utility.asDollar(v.wash_bonus)+"!");
                            }
                            if (chance >.9) v.condition = Enums.Condition.LikeNew;
                            break;

                            // 5% chance of Dirty, 40% chance of Sparkling 
                            case Detailed:
                            if (chance <= .05) v.cleanliness = Enums.Cleanliness.Dirty;
                            if (chance >.05 && chance <=.45) {
                                v.cleanliness = Enums.Cleanliness.Sparkling;
                                bonusEarned += v.wash_bonus;
                                washBonus += v.wash_bonus;
                                out("Intern "+name+" got a bonus of "+Utility.asDollar(v.wash_bonus)+"!");
                            }
                            break;
                    }
                    out("Intern "+name+" washed "+v.name+" "+startAs+" to "+v.cleanliness);
                    if (washCount == 2) break;
                }
            }
        }
        publisher.financialEvent(washBonus, -washBonus);
        return washBonus;
    }
}

class Mechanic extends Staff {
    static List<String> names = Arrays.asList("James", "Scotty", "Spock", "Uhura");
    static Namer namer = new Namer(names);
    Mechanic() {
        super();
        type = Enums.StaffType.Mechanic;
        name = namer.getNext();  // every new mechanic gets a new name
        salary = 120; // daily salary
    }

    // how Mechanics repair Vehicles - not as complicated as the Wash thing above
    double repairVehicles(ArrayList<Vehicle> vList) {
        Publisher publisher = Publisher.getInstance();
        double repairBonus = 0;
        int fixCount = 0;
        Enums.Condition startAs;
        // I'm just grabbing the first Vehicle I find - would be easy to randomly pick one
        for (Vehicle v: vList) {
            if (v.condition != Enums.Condition.LikeNew) {
                startAs = v.condition;
                if (v.cleanliness == Enums.Cleanliness.Clean) v.cleanliness = Enums.Cleanliness.Dirty;
                if (v.cleanliness == Enums.Cleanliness.Sparkling) v.cleanliness = Enums.Cleanliness.Clean;
                double chance = Utility.rnd();
                if (chance < .8) {
                    fixCount += 1;
                    if (v.condition == Enums.Condition.Used) {
                        v.condition = Enums.Condition.LikeNew;
                        v.price = v.price * 1.25;  // 25% increase for Used to Like New
                    }
                    if (v.condition == Enums.Condition.Broken) {
                        v.condition = Enums.Condition.Used;
                        v.price = v.price * 1.5;  // 50% increase for Broken to Used
                    }
                    bonusEarned += v.repair_bonus;
                    repairBonus += v.repair_bonus;
                    out("Mechanic "+name+" got a bonus of "+Utility.asDollar(v.repair_bonus)+"!");
                    out("Mechanic "+name+" fixed "+v.name+" "+startAs+" to "+v.condition);
                }
                else {
                    fixCount += 1;   // I'm saying a failed repair still took up a fix attempt
                    out("Mechanic "+name+" did not fix the "+v.condition+" "+v.name);
                }
            }
            if (fixCount==2) break;
        }
        publisher.financialEvent(repairBonus, -repairBonus);
        return repairBonus;
    }
}
class Salesperson extends Staff {
    static List<String> names = Arrays.asList("Rachel","Monica","Phoebe","Chandler","Ross","Joey");
    static Namer namer = new Namer(names);
    Salesperson() {
        super();
        type = Enums.StaffType.Salesperson;
        name = namer.getNext();  // every new salesperson gets a new name
        salary = 90; // daily salary
    }

    // Someone is asking this Salesperson to sell to this Buyer
    // We'll return any car we sell for the FNCD to keep track of (null if no sale)
    Vehicle sellVehicleinterface(Vehicle desired, FNCD fncd) {
        Publisher publisher = Publisher.getInstance();
        double salesBonus = 0;

        bonusEarned += desired.sale_bonus;
        salesBonus += desired.sale_bonus;
        // Bonus payed out by FNCD
        fncd.moneyOut(salesBonus);
        publisher.financialEvent(salesBonus, -salesBonus);
        return desired;
    }

    Vehicle sellVehicle(Buyer b, ArrayList<Vehicle> vList, FNCD fncd) {
        Publisher publisher = Publisher.getInstance();
        double salesBonus = 0;
        // buyer type determines initial purchase chance
        double saleChance = .7; // needs one
        if (b.type == Enums.BuyerType.WantsOne) saleChance = .4;
        if (b.type == Enums.BuyerType.JustLooking) saleChance = .1;
        // find the most expensive vehicle of the type the buyer wants that isn't broken
        // sales chance +10% if Like New, + 10% if Sparkling
        // if no vehicles of type, find remaining most expensive vehicle and sell at -20%
        ArrayList<Vehicle> desiredList = Vehicle.getVehiclesByType(vList, b.preference);
        Vehicle v;
        v = getMostExpensiveNotBroken(desiredList);  // could be null
        if (v == null) {
            // no unbroken cars of preferred type
            saleChance -= .2;
            v = getMostExpensiveNotBroken(vList);  // could still be null
        }
        if (v == null) {
            out("Salesperson "+name+" has no car for buyer "+b.name);
            return null;
        }
        else { //sell this car!
            if (v.condition == Enums.Condition.LikeNew) saleChance += .1;
            if (v.cleanliness == Enums.Cleanliness.Sparkling) saleChance += .1;
            double chance = Utility.rnd();
            if (chance<=saleChance) {  // sold!
                bonusEarned += v.sale_bonus;
                salesBonus += v.sale_bonus;
                out("Buyer "+b.name+" is buying! Salesperson "+name+" gets a bonus of "+Utility.asDollar(v.sale_bonus)+"!");
                // Bonus payed out by FNCD
                fncd.moneyOut(salesBonus);
                publisher.financialEvent(salesBonus, -salesBonus);
                out("Buyer "+b.name+" bought "+v.cleanliness+" "+v.condition+" "+v.name+" for "+Utility.asDollar(v.price));
                return v;
            }
            else {  // no sale!
                out("Buyer "+b.name+" decided not to buy.");
                return null;
            }
        }
    }

    // Little helper for finding most expensive and not broken in a list of vehicles
    // Used twice by salespeople
    Vehicle getMostExpensiveNotBroken(ArrayList<Vehicle> vList) {
        double highPrice = 0;
        int selected = -1;
        for (int index=0;index<vList.size();++index) {
            Vehicle v = vList.get(index);
            if (v.price>highPrice) {
                if (v.condition != Enums.Condition.Broken) {
                    selected = index;
                    highPrice = v.price;
                }
            }
        }
        if (selected == -1) return null;
        else return vList.get(selected);
    }
}



class Driver extends Staff {
    static List<String> names = Arrays.asList("Jon","Harry","Salah","Mark","Tylor","Sara");
    static Namer namer = new Namer(names);
    Driver() {
        super();
        type = Enums.StaffType.Driver;
        name = namer.getNext();  // every new Driver gets a new name
        winCount = 0;
        winBonus = 250.0;
        Enums.Health health  = Enums.Health.Healthy;
    }

     double startRace(ArrayList<Vehicle> vList, ArrayList<Staff> drivers, FNCD fncd) {
        Random random = new Random();

        ArrayList<Staff> currentDrivers = drivers;

        for (Vehicle v : vList) {
            int randomIndex = (int) (Math.random() *drivers.size());
            Staff driver = currentDrivers.get(randomIndex);
            currentDrivers.remove(randomIndex);
            v.setDriver((Driver) driver);
            v.setRacePosition(random.nextInt(20) + 1);
            if (v.getRacePosition() <= 3) {
                v.setWinCount(v.getWinCount() + 1);
                v.setPrice(v.getPrice() * 1.1);
                bonusEarned += winBonus;
                fncd.moneyOut(winBonus);
                this.winCount +=1;
                out("Driver: " + driver.name + " will be racing in the " + v.name);
                out("Driver: " + driver.name + " Poistion: " + v.getRacePosition() + " Won the race on the "+ v.name);

            }
            if (v.getRacePosition() >=16) {
                v.condition = v.condition.Broken;
                out("Driver: " + driver.name + " will be racing in the " + v.name);
                out("Driver: " + driver.name + " Poistion: " + v.getRacePosition() + " Lost the race on the "+ v.name);
                if (Utility.rndFromRange(0, 100) < 30) {
                    this.health = Enums.Health.Injured;
                    drivers.remove(driver);
                    out("Driver: " + driver.name + " Injured" );

                }
            }
            else{
                out("Driver: " + driver.name + " will be racing in the " + v.name);
                out("Driver: " + driver.name + " Poistion: " + v.getRacePosition() + " on the "+ v.name);
            }
        }
        return bonusEarned;
    }

}
