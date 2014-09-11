package elevators.ui

import elevators.queue.RequestQueue
import elevators.queue.RequestQueueIterator
import java.awt.Dimension
import java.awt.Graphics
import javax.swing.JPanel

class WaitingDrawer(requests: RequestQueue[Int]) extends JPanel with DrawsWaiting {
    
  var waiting = requests

  this.setPreferredSize(new Dimension(200, 500))

  override def loadRequests(requests: RequestQueue[Int]): Unit = {
    waiting = requests
  }

  override def paint(graphics: Graphics): Unit = {
    val iter = new RequestQueueIterator(waiting)
    while (iter.hasNext) {
      val next = iter.next
      this.drawWaitingFloor(graphics, next)
    }
  }

  private def drawWaitingFloor(graphics: Graphics, request: Int): Unit = {
    graphics.drawOval(5, request * 5, 4, 4)
  }
}












