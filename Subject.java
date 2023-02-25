// Interface for a class to implement the Subject in the Subject/Observer relationship of the Observer pattern
public interface Subject {
    public void registerObserver(Observer o);
    public void removeObserver(Observer o);
    public void notifyObservers();
}
