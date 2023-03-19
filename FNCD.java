import java.util.ArrayList;
import java.util.Scanner;

// This represents the FNCD business and things they would control
public class FNCD implements SysOut {
    String name;
    ArrayList<Staff> staff;  // folks working
    ArrayList<Staff> departedStaff;   // folks that left
    ArrayList<Vehicle> inventory;   // vehicles at the FNCD
    ArrayList<Vehicle> soldVehicles;   // vehicles the buyers bought;
    private double budget;   // big money pile
    FNCD(String name) {
        this.name = name;
        staff = new ArrayList<>();
        departedStaff = new ArrayList<>();
        inventory = new ArrayList<>();
        soldVehicles = new ArrayList<>();
        budget = 100000;  // I changed this just to see additions to the budget happen
    }
    double getBudget() {
        return budget;    // I'm keeping this private to be on the safe side
    }
    double moneyIn(double cash) {  // Nothing special about taking money in yet
        budget += cash;
        return cash;
    }
    double moneyOut(double cash) {   // I check for budget overruns on every payout
        budget -= cash;
        if (budget<0) {
            budget += 250000;
            out("***Budget overrun*** Added $250K, budget now: " + Utility.asDollar(budget));
            Publisher.getInstance().financialEvent(0, -250000);
        }
        return cash;
    }

    // Refactor code into different activities

    void open() {
        // opening
        out("The "+this.name+ " FNCD is opening...");
        out("Money in the budget " + Utility.asDollar(budget));
        hireNewStaff();    // hire up to 3 of each staff type
        updateInventory();  // buy up to 4 of each type
        out("End opening activity for "+this.name+" FNCD...\n");
    }

    void wash() {
        // washing - tell the interns to do the washing up
        out("The "+this.name+ " FNCD interns are washing...");
        ArrayList<Staff> interns = Staff.getStaffByType(staff, Enums.StaffType.Intern);
        for (Staff s:interns) {
            Intern i = (Intern) s;
            // Earned bonus get payed out by FNCD
            moneyOut(i.washVehicles(inventory));
        }
        out("End washing activity for "+this.name+" FNCD...\n");
    }

    void repair() {
        // repairing - tell the mechanics to do their repairing
        out("The "+this.name+ " FNCD mechanics are repairing...");
        ArrayList<Staff> mechanics = Staff.getStaffByType(staff, Enums.StaffType.Mechanic);
        for (Staff s:mechanics) {
            Mechanic m = (Mechanic) s;
            // Earned bonus get payed out by FNCD
            moneyOut(m.repairVehicles(inventory));
        }
        out("End repairing activity for "+this.name+" FNCD...\n");
    }



    // A Human-controlled Interface to allow a user to act as a Buyer
    void command(Enums.DayOfWeek day) {
        Scanner scanner = new Scanner(System.in); // to get inputs

        ArrayList<Staff> salespeople = Staff.getStaffByType(staff, Enums.StaffType.Salesperson); // A list of all sales persons is needed

        int randomSeller = Utility.rndFromRange(0,salespeople.size()-1); // Get a random salesperson 

        Salesperson seller = (Salesperson) salespeople.get(randomSeller);

        while (true) {
            // Display the command menu
            out("Welcome to " + this.name + " FNCD");
            out("1. Change location");
            out("2. Get salesperson name");
            out("3. Get time");
            out("4. Select different salesperson");
            out("5. Get store inventory");
            out("6. Get details on inventory item");
            out("7. Buy item");
            out("8. End interactions");
            out("Enter command number: ");
            
            // Get the user's command choice
            int command = scanner.nextInt();
            scanner.nextLine(); // Consume the remaining newline character
            
            // Execute the user's chosen command
            switch (command) {
                case 1:
                out("Please quit this interface and choose other location"); // change location
                break;

                case 2:
                out(" My name is " + seller.name); // SalesPerson name
                java.time.LocalDateTime.now();
                break;

                case 3:
                System.out.println(java.time.LocalDateTime.now()); // print system time
                break;

                case 4:
                int randomSeller2 = Utility.rndFromRange(0,salespeople.size()-1); // Get a random salesperson 
                Salesperson seller2 = (Salesperson) salespeople.get(randomSeller2);
                out(" Don't worry I'll get you " + seller2.name );
                break;

                case 5:
                for (int i = 0; i < inventory.size(); i++) {        // Print inventory with ID's
                    out("ID = " + i + " " + inventory.get(i).name);
                }
                break;

                case 6: 
                out("Type the car ID");                 // Print car details
                int command1 = scanner.nextInt();
                scanner.nextLine(); // Consume the remaining newline character

                out(" Car name: " + inventory.get(command1).name +  " Cleanliness: " + inventory.get(command1).cleanliness + " condition: " +inventory.get(command1).condition + " Price " +  inventory.get(command1).price);

                break;

                case 7:                 // Buy car and add-ons
                
                out("Please follow me to the office to discuss your purchase");
                out("Type the car ID");
                int command2 = scanner.nextInt();
                scanner.nextLine(); // Consume the remaining newline character

                out("Would you want buy the car? ");
                out("1- Yes");
                out("2- No");
                out("Enter command number: ");

                int command3 = scanner.nextInt();
                scanner.nextLine(); // Consume the remaining newline character

                if (command3 == 1) {

                    Vehicle vSold = seller.sellVehicleinterface(inventory.get(command2), this);

                    out("Would you want to buy add-ons the car? ");
                    out("1- Yes");
                    out("2- No");
                    out("Enter command number: ");
    
                    int command4 = scanner.nextInt();
                    scanner.nextLine(); // Consume the remaining newline character

                    if (command4 == 1) {
                        Vehicle vSoldDecorated = vSold;
                        boolean continueAddingAddOns = true;
                        while (continueAddingAddOns) {
                            out("Choose what add-ons you want to buy?"); // Options for add-ons
                            out("1- Extended Warranty");
                            out("2- Undercoating");
                            out("3- Road Rescue Coverage");
                            out("4- Satellite Radio");
                            out("5- Continue without adding more add-ons");
                    
                            int command5 = scanner.nextInt();
                            scanner.nextLine(); // Consume the remaining newline character
                    
                            switch(command5) {
                                case 1:
                                    vSoldDecorated = new ExtendedWarranty(vSoldDecorated, vSold.getPrice());
                                    out("Extended Warranty added to " + vSold.name + ", price increased to: " + Utility.asDollar(vSoldDecorated.getPrice())); // Extended Warranty = 20% of Vehicle sale price
 
                                    break;
                                case 2:
                                    vSoldDecorated = new Undercoating(vSoldDecorated, vSold.getPrice());
                                    out("Undercoating added to " + vSold.name + ", price increased to: " + Utility.asDollar(vSoldDecorated.getPrice())); // Undercoating = 5% of price
                                    break;
                                case 3:
                                    vSoldDecorated = new RoadRescue(vSoldDecorated, vSold.getPrice());
                                    out("Road Rescue Coverage added to " + vSold.name + ", price increased to: " + Utility.asDollar(vSoldDecorated.getPrice()));  // Road Rescue Coverage = 2% of price
                                    break;
                                case 4:
                                    vSoldDecorated = new SatRadio(vSoldDecorated, vSold.getPrice());
                                    out("Satellite Radio added to " + vSold.name + ", price increased to: " + Utility.asDollar(vSoldDecorated.getPrice())); // Satellite Radio = 5% of price
                                    break;
                                case 5:
                                    continueAddingAddOns = false;
                                    break;
                                default:
                                    out("Invalid command");
                                    break;
                            }
                        }
                        soldVehicles.add(vSold);
                        moneyIn(vSoldDecorated.getPrice());
                        Publisher.getInstance().financialEvent(0, vSoldDecorated.getPrice());
                        inventory.remove(vSold);
                    }
                }

                break;

                case 8:
                // ending
                // daily report
                reportOut();
                System.exit(0);

                default:
                out("Invalid command number.");
                
            }

        }

    }


    void sell(Enums.DayOfWeek day) {
        // selling
        out("The "+this.name+ " FNCD salespeople are selling...");
        ArrayList<Buyer> buyers = getBuyers(day);
        ArrayList<Staff> salespeople = Staff.getStaffByType(staff, Enums.StaffType.Salesperson);
        // tell a random salesperson to sell each buyer a car - may get bonus
        for(Buyer b: buyers) {
            out("Buyer "+b.name+" wants a "+b.preference+" ("+b.type+")");
            int randomSeller = Utility.rndFromRange(0,salespeople.size()-1);
            Salesperson seller = (Salesperson) salespeople.get(randomSeller);
            // Need to pass in FNCD instance to vehicle selling process to pay out Salesperson bonus.
            // Not good practice, but it works as a temp solution.
            Vehicle vSold = seller.sellVehicle(b, inventory, this);
            // What the FNCD needs to do if a car is sold - change budget and inventory
            if (vSold != null) {
                // Buyers will be offered 4 add-on purchases after deciding to buy Vehicle
                double chance = Utility.rnd();
                
                Vehicle vSoldDecorated = vSold;
                
                // Extended Warranty = 20% of Vehicle sale price, 25% chance of Buyer adding 
                if (chance <= 0.25) {
                    vSoldDecorated = new ExtendedWarranty(vSoldDecorated, vSold.getPrice());
                    out("Extended Warranty added to " + vSold.name + ", price increased to: " + Utility.asDollar(vSoldDecorated.getPrice()));
                }

                // Undercoating = 5% of price, 10% chance of adding
                if (chance <= 0.10) {
                    vSoldDecorated = new Undercoating(vSoldDecorated, vSold.getPrice());
                    out("Undercoating added to " + vSold.name + ", price increased to: " + Utility.asDollar(vSoldDecorated.getPrice()));
                }

                // Road Rescue Coverage = 2% of price, 5% chance of adding
                if (chance <= 0.05) {
                    vSoldDecorated = new RoadRescue(vSoldDecorated, vSold.getPrice());
                    out("Road Rescue Coverage added to " + vSold.name + ", price increased to: " + Utility.asDollar(vSoldDecorated.getPrice()));
                }

                // Satellite Radio = 5% of price, 40% chance of adding
                if (chance <= 0.40) {
                    vSoldDecorated = new SatRadio(vSoldDecorated, vSold.getPrice());
                    out("Satellite Radio added to " + vSold.name + ", price increased to: " + Utility.asDollar(vSoldDecorated.getPrice()));
                }

                soldVehicles.add(vSold);
                moneyIn(vSoldDecorated.getPrice());
                Publisher.getInstance().financialEvent(0, vSoldDecorated.getPrice());

                // inventory.removeIf ( n -> n.name == vSold.name);
                // Replaced removeIf with remove because vSold had to be final.
                inventory.remove(vSold);
            }
        }
        out("End selling activity for "+this.name+" FNCD...\n");
    }

    void race(Enums.DayOfWeek day) {
        out("The "+this.name+ " FNCD might run a Race Event today for "+day);

        // Select Vehicles of a random type to the race that aren't regular Cars or Electric Cars
        ArrayList<Vehicle> raceVehicles = Vehicle.getRaceVehicles(inventory);
        
        if (raceVehicles.size() == 0) {
            out("Sorry, there are no eligible vehicles in the FNCD to race today.");
        }
        // Assign each driver at random to an eligible vehicle to race
        ArrayList<Staff> drivers = Staff.getStaffByType(staff, Enums.StaffType.Driver);

        Driver driver = new Driver();
        driver.startRace(raceVehicles, drivers, this);
        out("End race activity for "+this.name+" FNCD\n");
    }

    void end() {
        out("End of day activity for "+this.name+" FNCD...");
        // ending
        // pay all the staff their salaries
        payStaff();
        // anyone quitting? replace with an intern (if not an intern)
        checkForQuitters();
        // daily report
        reportOut();
    }

    // Here's where I process daily activities
    // I debated about moving the individual activities out to an Activity class
    // It would make the normal day less of a monster maybe, eh...

    void raceDay(Enums.DayOfWeek day) {   // Nothing really special about closed days
        out("The FNCD might run a Race Event today for "+day);

        // Select Vehicles of a random type to the race that aren't regular Cars or Electric Cars
        ArrayList<Vehicle> raceVehicles = Vehicle.getRaceVehicles(inventory);
        
        if (raceVehicles.size() == 0) {
            out("Sorry, there are no eligible vehicles in the FNCD to race today.");
        }
        // Assign each driver at random to an eligible vehicle to race
        ArrayList<Staff> drivers = Staff.getStaffByType(staff, Enums.StaffType.Driver);

        Driver driver = new Driver();
        driver.startRace(raceVehicles, drivers, this);

        // for (Vehicle v : raceVehicles) {
        //     int randomIndex = (int) (Math.random() *drivers.size());
        //     Staff driver = drivers.get(randomIndex);
        //     v.setDriver((Driver) driver);
        //     drivers.remove(driver);
        //     out(driver.name + " will be racing in the " + v.name);
        // }

        // get race postion if top 3 winner. if last 5 damaged

        //  If the Vehicle is in the Damaged category, the Vehicle’s condition goes to Broken, and the Driver of the Vehicle has a 30% chance of being Injured. 
 
    }

    void normalDay(Enums.DayOfWeek day) {  // On a normal day, we do all the activities

        // opening
        out("The FNCD is opening...");
        out("Money in the budget " + Utility.asDollar(budget));
        hireNewStaff();    // hire up to 3 of each staff type
        updateInventory();  // buy up to 4 of each type

        // washing - tell the interns to do the washing up
        out("The FNCD interns are washing...");
        ArrayList<Staff> interns = Staff.getStaffByType(staff, Enums.StaffType.Intern);
        for (Staff s:interns) {
            Intern i = (Intern) s;
            // Earned bonus get payed out by FNCD
            moneyOut(i.washVehicles(inventory));
        }

        // repairing - tell the mechanics to do their repairing
        out("The FNCD mechanics are repairing...");
        ArrayList<Staff> mechanics = Staff.getStaffByType(staff, Enums.StaffType.Mechanic);
        for (Staff s:mechanics) {
            Mechanic m = (Mechanic) s;
            // Earned bonus get payed out by FNCD
            moneyOut(m.repairVehicles(inventory));
        }

        // selling
        out("The FNCD salespeople are selling...");
        ArrayList<Buyer> buyers = getBuyers(day);
        ArrayList<Staff> salespeople = Staff.getStaffByType(staff, Enums.StaffType.Salesperson);
        // tell a random salesperson to sell each buyer a car - may get bonus
        for(Buyer b: buyers) {
            out("Buyer "+b.name+" wants a "+b.preference+" ("+b.type+")");
            int randomSeller = Utility.rndFromRange(0,salespeople.size()-1);
            Salesperson seller = (Salesperson) salespeople.get(randomSeller);
            // Need to pass in FNCD instance to vehicle selling process to pay out Salesperson bonus.
            // Not good practice, but it works as a temp solution.
            Vehicle vSold = seller.sellVehicle(b, inventory, this);
            // What the FNCD needs to do if a car is sold - change budget and inventory
            if (vSold != null) {
                // Buyers will be offered 4 add-on purchases after deciding to buy Vehicle
                double chance = Utility.rnd();
                
                Vehicle vSoldDecorated = vSold;
                
                // Extended Warranty = 20% of Vehicle sale price, 25% chance of Buyer adding 
                if (chance <= 0.25) {
                    vSoldDecorated = new ExtendedWarranty(vSoldDecorated, vSold.getPrice());
                    out("Extended Warranty added to " + vSold.name + ", price increased to: " + Utility.asDollar(vSoldDecorated.getPrice()));
                }

                // Undercoating = 5% of price, 10% chance of adding
                if (chance <= 0.10) {
                    vSoldDecorated = new Undercoating(vSoldDecorated, vSold.getPrice());
                    out("Undercoating added to " + vSold.name + ", price increased to: " + Utility.asDollar(vSoldDecorated.getPrice()));
                }

                // Road Rescue Coverage = 2% of price, 5% chance of adding
                if (chance <= 0.05) {
                    vSoldDecorated = new RoadRescue(vSoldDecorated, vSold.getPrice());
                    out("Road Rescue Coverage added to " + vSold.name + ", price increased to: " + Utility.asDollar(vSoldDecorated.getPrice()));
                }

                // Satellite Radio = 5% of price, 40% chance of adding
                if (chance <= 0.40) {
                    vSoldDecorated = new SatRadio(vSoldDecorated, vSold.getPrice());
                    out("Satellite Radio added to " + vSold.name + ", price increased to: " + Utility.asDollar(vSoldDecorated.getPrice()));
                }

                soldVehicles.add(vSold);
                moneyIn(vSoldDecorated.getPrice());
                Publisher.getInstance().financialEvent(0, vSoldDecorated.getPrice());

                // inventory.removeIf ( n -> n.name == vSold.name);
                // Replaced removeIf with remove because vSold had to be final.
                inventory.remove(vSold);
            }
        }

        // ending
        // pay all the staff their salaries
        payStaff();
        // anyone quitting? replace with an intern (if not an intern)
        checkForQuitters();
        // daily report
        reportOut();
    }

    // generate buyers
    ArrayList<Buyer> getBuyers(Enums.DayOfWeek day) {
        // 0 to 5 buyers arrive (2-8 on Fri/Sat)
        int buyerMin = 0;  //normal Mon-Thur
        int buyerMax = 5;
        if (day == Enums.DayOfWeek.Fri || day == Enums.DayOfWeek.Sat) {
            buyerMin = 2;
            buyerMax = 8;
        }
        ArrayList<Buyer> buyers = new ArrayList<Buyer>();
        int buyerCount = Utility.rndFromRange(buyerMin,buyerMax);
        for (int i=1; i<=buyerCount; ++i) buyers.add(new Buyer());
        out("The FNCD has "+buyerCount+" buyers today...");
        return buyers;
    }

    // see if we need any new hires
    void hireNewStaff() {
        final int numberInStaff = 3;
        for (Enums.StaffType t : Enums.StaffType.values()) {
            int typeInList = Staff.howManyStaffByType(staff, t);
            int need = numberInStaff - typeInList;
            for (int i = 1; i<=need; ++i) addStaff(t);
        }
    }

    // adding staff
    // smells like we need a factory or something...
    void addStaff(Enums.StaffType t) {
        Staff newStaff = null;
        newStaff = StaffCreator.createStaff(t);
        // if (t == Enums.StaffType.Intern) newStaff = new Intern();
        // if (t == Enums.StaffType.Mechanic) newStaff = new Mechanic();
        // if (t == Enums.StaffType.Salesperson) newStaff = new Salesperson();
        // if (t == Enums.StaffType.Driver) newStaff = new Driver();
        out("Hired a new "+newStaff.type+" named "+ newStaff.name);
        staff.add(newStaff);
    }

    // see if we need any vehicles
    void updateInventory() {
        // The Inventory count of all Vehicle type instances at an FNCD changes from 4 to 6.
        final int numberInInventory = 6;    
        for (Enums.VehicleType t : Enums.VehicleType.values()) {
            int typeInList = Vehicle.howManyVehiclesByType(inventory, t);
            int need = numberInInventory - typeInList;
            for (int i = 1; i<=need; ++i) addVehicle(t);
        }
    }

    // add a vehicle of a type to the inventory
    void addVehicle(Enums.VehicleType t) {
        Vehicle v = null;
        v = VehicleCreator.createVehicle(t);
        // if (t == Enums.VehicleType.Car) v = new Car();
        // if (t == Enums.VehicleType.PerfCar) v = new PerfCar();
        // if (t == Enums.VehicleType.Pickup) v = new Pickup();
        // if (t == Enums.VehicleType.ElectricCar) v = new ElectricCar();
        // if (t == Enums.VehicleType.MonsterTruck) v = new MonsterTruck();
        // if (t == Enums.VehicleType.Motorcycle) v = new Motorcycle();

        moneyOut(v.cost);
        Publisher.getInstance().financialEvent(0, -v.cost);
        out ("Bought "+v.name+", a "+v.cleanliness+" "+v.condition+" "+v.type+" for "+Utility.asDollar(v.cost));
        inventory.add(v);
    }

    // pay salary to staff and update days worked
    void payStaff() {
        for (Staff s: staff) {
            s.salaryEarned += s.salary;  // they get paid
            out(s.type + " " + s.name + " received " + Utility.asDollar(s.salary) + ", Total-salary: " + Utility.asDollar(s.salaryEarned) + " Total-bonus: " + Utility.asDollar(s.bonusEarned));
            moneyOut(s.salary);
            Publisher.getInstance().financialEvent(s.salary, -s.salary);
            s.daysWorked += 1; // they worked another day
        }
    }

    // Huh - no one wants to quit my FNCD!
    // I left this as an exercise to the reader...
    void checkForQuitters() {
        out("No-one on the staff is leaving!");

        // I would check the percentages here
        // Move quitters to the departedStaff list
        // If an intern quits, you're good
        // If a mechanic or a salesperson quits
        // Remove an intern from the staff and use their properties to
        // create the new mechanic or salesperson
    }

    void reportOut() {
        // We're all good here, how are you?
        // Quick little summary of happenings, you could do better

        out("Vehicles in inventory "+inventory.size());
        out("Vehicles sold count "+soldVehicles.size());
        out("Money in the budget "+ Utility.asDollar(getBudget()));
        out("That's it for the day.\n");
    }
}
