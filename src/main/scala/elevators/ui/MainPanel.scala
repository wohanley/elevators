package elevators.ui

import elevators._
import elevators.queue.RequestQueue
import elevators.util.first
import elevators.util.GifExporter
import java.awt.Container
import java.awt.Dimension
import java.awt.Graphics
import java.awt.GridLayout
import java.awt.event.MouseEvent
import java.awt.event.MouseListener
import javax.swing.JComponent
import javax.swing.JPanel
import scala.collection.immutable.HashMap
import scala.util.Random

/**
 * @param requests: only used for initialization
 */ 
class MainPanel(initialRequests: RequestQueue[Int]) extends JPanel with View {

  var requests = initialRequests

  this.setPreferredSize(new Dimension(200, 500))
  val gridLayout = new GridLayout(0, 1)
  gridLayout.setVgap(1)
  this.setLayout(gridLayout)

  var requestRegions = HashMap[Int, RequestRegion]()
  for (i <- List.range(1, 50)) {
    val region = new RequestRegion()
    Interaction.onClick(region, () => this.enqueue(i))
    this.add(region)
    this.requestRegions += i -> region
  }

  val giffer = new GifExporter()

  def enqueue(request: Int): Unit = {
    this.drawRequest(request)
    this.requests = this.requests.enqueue(request)
  }

  def dequeue: Unit = {
    if (!this.requests.isEmpty) {
      val dequeued = this.requests.dequeue
      this.drawService(dequeued._1) // sleep?
      this.requests = dequeued._2
    }
  }

  override def drawRequest(request: Int): Unit = {
    this.repaintRegion(request, region => region.requested())
  }

  override def drawService(request: Int): Unit = {
    this.repaintRegion(request, region => region.serviced())
  }

  private def repaintRegion[T](request: Int, f: RequestRegion => T): Option[T] = {
    this.requestRegions.get(request).map(region => {
      val result = f(region)
      region.repaint()
      giffer.gif(this)
      return Some(result)
    })
  }

  override def paintComponent(graphics: Graphics): Unit = {
    for (region <- this.requestRegions) {
      region._2.repaint()
    }
  }

  def randomRequest(): Unit = {
    val request = generateRequest(this.requests, new Random())
    this.enqueue(request)
  }
}

object Interaction {
  def onClick(component: JComponent, clicked: () => Unit) {
    component.addMouseListener(new DelegateMouseListener(clicked))
  }

  class DelegateMouseListener(clicked: () => Unit) extends MouseListener {
    override def mouseClicked(event: MouseEvent) = {
      clicked()
    }
    override def mouseEntered(event: MouseEvent): Unit = ()
    override def mouseExited(event: MouseEvent): Unit = ()
    override def mousePressed(event: MouseEvent): Unit = ()
    override def mouseReleased(event: MouseEvent): Unit = ()
  }
}
