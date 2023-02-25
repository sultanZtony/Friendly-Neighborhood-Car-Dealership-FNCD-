// Concrete class implementation of an Observer in the Observer OO design pattern
import java.io.FileWriter;
import java.io.IOException;

public class Logger implements Observer {
    private FileWriter fileWriter;
    private Publisher publisher;

    public Logger(int day, Publisher publisher) {
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
