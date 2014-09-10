package elevators.ui;

public interface View<T> {
    void requested(int request);
    void serviced(int request);
}
