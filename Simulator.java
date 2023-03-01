import java.io.FileWriter;
import java.io.File;
import java.io.IOException;

// Simulator to cycle for select number of days
public class Simulator implements SysOut {
    final int numDays;
    Enums.DayOfWeek dayOfWeek;

    Simulator() {
        numDays = 30;  // magic number for days to run here
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

        FNCD fncd = new FNCD();
        Publisher publisher = Publisher.getInstance();

        Tracker tracker = new Tracker(publisher);

        for (int day = 1; day <= numDays; ++day) {
            // Loggers are instantiated at the beginning of each simulated day and close at the end of each day.
            Logger logger = new Logger(day, publisher);

            out(">>> Start Simulation Day "+day+" "+dayOfWeek);
            if (dayOfWeek == Enums.DayOfWeek.Sun || dayOfWeek == Enums.DayOfWeek.Wed) fncd.raceDay(dayOfWeek);  // no work on Sunday
            else fncd.normalDay(dayOfWeek);  // normal stuff on other days
            out(">>> End Simulation Day "+day+" "+dayOfWeek+"\n");
            dayOfWeek = getNextDay(dayOfWeek);  // increment to the next day
            
            tracker.printSummary(day);
            logger.close();
        }
        tracker.close();
    }
}
