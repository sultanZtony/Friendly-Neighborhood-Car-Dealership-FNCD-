// Concrete class implementation of the Subject in the Observer OO design pattern
import java.util.ArrayList;

public class Publisher implements Subject {
    private static Publisher instance;
    private ArrayList<Observer> observers;

    private String event;
    private double staffMoneyIn;
    private double dealerMoneyInOut;

    private Publisher() {
        this.observers = new ArrayList<>();
    }

    // Publisher implements the Singleton Pattern.
    // We can refer to a single instance of this class anywhere in the program.
    public static synchronized Publisher getInstance() {
        if (instance == null) {
            instance = new Publisher();
        }
        return instance;
    }

    @Override
    public void registerObserver(Observer o) {
        observers.add(o);
    }

    @Override
    public void removeObserver(Observer o) {
        observers.remove(o);
    }

    @Override
    public void notifyObservers() {

        for (Observer o : observers) {
            o.update(event, staffMoneyIn, dealerMoneyInOut);
        }
    }

    public void eventOccured(String event) {
        this.event = event;
        this.staffMoneyIn = 0;
        this.dealerMoneyInOut = 0;
        notifyObservers();
    }

    public void financialEvent(double staffMoneyIn, double dealerMoneyInOut) {
        this.event = "";
        this.staffMoneyIn = staffMoneyIn;
        this.dealerMoneyInOut = dealerMoneyInOut;
        notifyObservers();
    }
}
