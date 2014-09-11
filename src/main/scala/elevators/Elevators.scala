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

    // the Controller constructor doesn't do what I'd like. We need to add all
    // our elements manually.
    val controller = new Controller(queue, mainPanel)
    for (i <- List.range(0, 10)) {
      controller.enqueue(random.nextInt(50))
    }

    val frame = new JFrame("Elevators")
    frame.setContentPane(mainPanel)
    frame.setResizable(false)
    frame.pack()
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE)
    frame.setVisible(true)

    while (true) {
      Thread.sleep(1000);
      controller.enqueue(random.nextInt(50));
      controller.dequeue();
    }
  }
}




