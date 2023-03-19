import java.io.FileWriter;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;

// Simulator to cycle for select number of days
public class Simulator implements SysOut {
    final int numDays;
    Enums.DayOfWeek dayOfWeek;

    Simulator() {
        numDays = 31;  // magic number for days to run here
        dayOfWeek = Utility.randomEnum(Enums.DayOfWeek.class);  // we'll start on a random day (for fun)
    }

    // cycling endlessly through enum values
    // https://stackoverflow.com/questions/34159413/java-get-next-enum-value-or-start-from-first
    public Enums.DayOfWeek getNextDay(Enums.DayOfWeek e)
    {
        int index = e.ordinal();
        int nextIndex = index + 1;
        Enums.DayOfWeek[] days = Enums.DayOfWeek.values();
        nextIndex %= days.length;
        return days[nextIndex];
    }

    void run() {
        File file = new File("SimResults.txt");
        boolean fileExists = file.exists();

        // If file exists, overwrite it.
        try {
            FileWriter fileWriter = new FileWriter(file, !fileExists);
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Adding one additional FNCD
        FNCD northFNCD = new FNCD("North");
        FNCD southFNCD = new FNCD("South");


        Publisher publisher = Publisher.getInstance();
        Tracker tracker = Tracker.getInstance();
        Logger logger = Logger.getInstance();

        for (int day = 1; day <= numDays; ++day) {
            logger.setLogger(day, publisher);

            out(">>> Start Simulation Day "+day+" "+dayOfWeek);

            // if (dayOfWeek == Enums.DayOfWeek.Sun || dayOfWeek == Enums.DayOfWeek.Wed) northFNCD.raceDay(dayOfWeek); 
            // else northFNCD.normalDay(dayOfWeek);  // normal stuff on other days

            if (day == 31) {
                Scanner scanner = new Scanner(System.in); // to get inputs
                out("Select location");
                out("1- North FNCD");
                out("2- South FNCD");
                out("Enter command number: ");
                int command = scanner.nextInt();
                scanner.nextLine(); // Consume the remaining newline character
                while (true) {

                if (command == 1) {
                    northFNCD.command(dayOfWeek);
                    out(">>> End Simulation Day "+day+" "+dayOfWeek+"\n");

                }
                else if(command == 2){
                    southFNCD.command(dayOfWeek);
                    out(">>> End Simulation Day "+day+" "+dayOfWeek+"\n");

                    dayOfWeek = getNextDay(dayOfWeek);  // increment to the next day
                    
                    tracker.printSummary(day);
                    logger.close();
                }
                else {
                    out("Invalid command number.");
                }
            }
            }
            else
            {
            if (dayOfWeek == Enums.DayOfWeek.Sun || dayOfWeek == Enums.DayOfWeek.Wed) { // race day
                northFNCD.open();
                southFNCD.open();

                northFNCD.wash();
                southFNCD.wash();

                northFNCD.repair();
                southFNCD.repair();

                northFNCD.sell(dayOfWeek);
                southFNCD.sell(dayOfWeek);

                northFNCD.race(dayOfWeek);
                southFNCD.race(dayOfWeek);

                northFNCD.end();
                southFNCD.end();
            } else {    // normal day
                northFNCD.open();
                southFNCD.open();

                northFNCD.wash();
                southFNCD.wash();

                northFNCD.repair();
                southFNCD.repair();

                northFNCD.sell(dayOfWeek);
                southFNCD.sell(dayOfWeek);

                northFNCD.end();
                southFNCD.end();
            } 
        }

            out(">>> End Simulation Day "+day+" "+dayOfWeek+"\n");

            dayOfWeek = getNextDay(dayOfWeek);  // increment to the next day
            
            tracker.printSummary(day);
            logger.close();
        }
        tracker.close();
    }
}
