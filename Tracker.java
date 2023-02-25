public class Tracker implements Observer, SysOut {
    private double staffMoneyIn;
    private double dealerMoneyInOut;

    @Override
    public void update(String event, double staffMoneyIn, double dealerMoneyInOut) {
        this.staffMoneyIn += staffMoneyIn;
        this.dealerMoneyInOut += dealerMoneyInOut;
    }

    public void printSummary(int day) {
        out("Tracker: Day" + " " + day);
        out("Total money earned by all Staff: " + Utility.asDollar(this.staffMoneyIn));
        out("Total money earned by the FNCD: " + Utility.asDollar(this.dealerMoneyInOut));
    }
}
