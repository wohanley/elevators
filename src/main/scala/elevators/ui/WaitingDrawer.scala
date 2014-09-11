package elevators.ui

import elevators.queue.RequestQueue
import elevators.queue.RequestQueueIterator
import java.awt.Color
import java.awt.Dimension
import java.awt.Graphics
import javax.swing.JPanel

class WaitingDrawer(requests: RequestQueue[Int]) extends JPanel with DrawsWaiting {
    
  var waiting = requests

  this.setPreferredSize(new Dimension(200, 500))

  override def loadRequests(requests: RequestQueue[Int]): Unit = {
    waiting = requests
  }

  override def paintComponent(graphics: Graphics): Unit = {
    graphics.setColor(Color.GRAY)
    graphics.fillRect(0, 0, this.getWidth(), this.getHeight())
    graphics.setColor(Color.WHITE)
    val iter = new RequestQueueIterator(waiting)
    while (iter.hasNext) {
      val next = iter.next
      this.drawWaitingFloor(graphics, next)
    }
  }

  private def drawWaitingFloor(graphics: Graphics, request: Int): Unit = {
    graphics.fillRect(5, request * 12, this.getWidth() - 10, 10)
  }
}












