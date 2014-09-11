package elevators.ui

import elevators.queue.RequestQueue
import java.awt.Container
import java.awt.Dimension
import java.awt.Graphics
import javax.swing.JPanel

/**
 * @param requests: only used for initialization
 */ 
class MainPanel(requests: RequestQueue[Int]) extends JPanel with View {

  val waitingRequests = new WaitingDrawer(requests)
  this.setPreferredSize(new Dimension(200, 500))

  override def drawQueue(requests: RequestQueue[Int]) = {
    waitingRequests.loadRequests(requests)
    waitingRequests.repaint()
  }

  override def drawRequest(request: Int): Unit = {
    println("Drawing request: " + request)
    this.repaint()
  }

  override def drawService(request: Int): Unit = {
    println("Drawing service: " + request)
    this.repaint()
  }

  override def paint(graphics: Graphics): Unit = {
    waitingRequests.repaint()
  }
}





