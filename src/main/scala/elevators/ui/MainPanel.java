package elevators.ui;

import java.awt.Dimension;
import java.awt.Graphics;
import javax.swing.JPanel;
import java.util.LinkedList;
import java.util.List;

public class MainPanel extends JPanel implements View<Integer> {

    private final LinkedList<Integer> floorsWaiting;

    public MainPanel() {
	this.setPreferredSize(new Dimension(400, 600));
	this.floorsWaiting = new LinkedList<Integer>();
    }

    @Override 
    public void requested(int request) {
	floorsWaiting.add(request);
    }

    @Override
    public void serviced(int request) {
	floorsWaiting.remove(request);
    }

    @Override
    public void paint(Graphics graphics) {
	for (int request : this.floorsWaiting) {
	    this.drawWaitingFloor(graphics, request);
	}
    }

    private void drawWaitingFloor(Graphics graphics, int request) {
    
    }
}





