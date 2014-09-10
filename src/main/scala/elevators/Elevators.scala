package elevators

import elevators.ui.MainPanel
import java.util.Random
import javax.swing.JFrame
import javax.swing.JPanel

object Elevators {
  
  val random = new Random()

  def main(args: Array[String]) = {
    
    val frame = new JFrame("Elevators")
    val mainPanel = new MainPanel()
    frame.setContentPane(mainPanel)
    frame.pack()
    frame.setResizable(false)
    frame.setVisible(true)
  }
}




