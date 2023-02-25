import java.io.FileWriter;
import java.io.IOException;

public class Logger implements Observer {
    private FileWriter fileWriter;

    public Logger(int day) {
        try {
            fileWriter = new FileWriter("Logger-" + day + ".txt");
        } catch (IOException e) {
            e.printStackTrace();
        }
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
    }
}
