package elevators.ui;

public interface View {
    void requested(int request);
    void serviced(int request);
}
