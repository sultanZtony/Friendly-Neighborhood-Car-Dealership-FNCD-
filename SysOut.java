import java.io.FileWriter;
import java.io.IOException;

// It annoys me to type SYSTEM.OUT.PRINTLN every time I want to print!!!!
// Sorry, I'll calm down now.
public interface SysOut {
    default void out(String msg) {
        System.out.println(msg);

        try {
            FileWriter fileWriter = new FileWriter("SimResults.txt", true);
            fileWriter.write(msg + "\n");
            fileWriter.flush();
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Publisher.getInstance().eventOccured(msg);
    }
}
