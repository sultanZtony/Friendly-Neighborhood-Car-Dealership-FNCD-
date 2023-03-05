// Logger follows the Singleton pattern using lazy instantiation.
// Concrete class implementation of an Observer in the Observer OO design pattern
import java.io.FileWriter;
import java.io.IOException;

public class Logger implements Observer {
    private static Logger instance;
    
    private FileWriter fileWriter;
    private Publisher publisher;

    private Logger() {
      
    }

    public static Logger getInstance() {
        if (instance == null) {
            instance = new Logger();
        }
        return instance;
    }

    public void setLogger(int day, Publisher publisher) {
        this.publisher = publisher;
        try {
            fileWriter = new FileWriter("Logger-" + day + ".txt");
        } catch (IOException e) {
            e.printStackTrace();
        }
        publisher.registerObserver(this);
    }

    @Override
    public void update(String event, double staffMoneyIn, double dealerMoneyIn) {
        try {
            if (event != "") {
                fileWriter.write(event + "\n");
                fileWriter.flush();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void close() {
        try {
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        publisher.removeObserver(this);        
    }
}
