package elevators.ui

import java.awt.Color
import java.awt.Dimension
import java.awt.Graphics
import java.awt.event.MouseListener
import javax.swing.JPanel

class RequestRegion extends JPanel {

  var waiting = false

  def requested(): Unit = {
    waiting = true
  }

  def serviced(): Unit = {
    waiting = false
  }

  override def paintComponent(graphics: Graphics): Unit = {
    (if (this.waiting) paintWaiting else paintOpen)(graphics)
  }

  private def fillSelf(color: Color)(graphics: Graphics): Unit = {
    graphics.setColor(color)
    graphics.fillRect(0, 0, this.getWidth(), this.getHeight())
  }

  private def paintWaiting = this.fillSelf(Color.WHITE)_

  private def paintOpen = this.fillSelf(Color.GRAY)_
}
