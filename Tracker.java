// Concrete class implementation of the Observer in the Observer  OO design pattern
public class Tracker implements Observer, SysOut {
    private double staffMoneyIn;
    private double dealerMoneyInOut;
    private Publisher publisher;

    public Tracker(Publisher publisher) {
        this.publisher = publisher;
        this.staffMoneyIn = 0;
        this.dealerMoneyInOut = 0;
        publisher.registerObserver(this);
    }
    
    @Override
    public void update(String event, double staffMoneyIn, double dealerMoneyInOut) {
        this.staffMoneyIn += staffMoneyIn;
        this.dealerMoneyInOut += dealerMoneyInOut;
    }

    public void printSummary(int day) {
        out("Tracker: Day" + " " + day);
        out("Total money earned by all Staff: " + Utility.asDollar(this.staffMoneyIn));
        out("Total money earned by the FNCD: " + Utility.asDollar(this.dealerMoneyInOut) + "\n");
    }

    public void close() {
        publisher.removeObserver(this);
    }
}
