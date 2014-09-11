package elevators

import elevators.queue.sweep._
import elevators.ui.MainPanel
import scala.util.Random
import javax.swing.JFrame
import javax.swing.JPanel

object Elevators {
  
  val random = new Random()

  def main(args: Array[String]) = {

    val queue = createLookQueue(List())

    val mainPanel = new MainPanel(queue)

    for (i <- List.range(0, 10)) {
      mainPanel.enqueue(random.nextInt(50))
    }

    val frame = new JFrame("Elevators")
    frame.setContentPane(mainPanel)
    frame.pack()
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE)
    frame.setVisible(true)

    while (true) {
      Thread.sleep(200)
      mainPanel.randomRequest()
      mainPanel.dequeue
    }
  }
}




