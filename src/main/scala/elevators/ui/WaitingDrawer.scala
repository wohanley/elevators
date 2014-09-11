package elevators.ui

import elevators.queue.RequestQueue
import java.awt.Graphics
import javax.swing.JPanel

class WaitingDrawer(requests: RequestQueue[Int]) extends JPanel with DrawsWaiting {
    
  var waiting = requests

  override def loadRequests(requests: RequestQueue[Int]): Unit = {
    waiting = requests
  }

  override def paint(graphics: Graphics): Unit = {
    
  }

  private def drawWaitingFloor(graphics: Graphics, request: Int): Unit = {
    graphics.drawOval(5, request * 5, 4, 4)
  }
}












