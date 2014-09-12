package elevators

package object util {

  import java.awt.Graphics
  import java.awt.image.BufferedImage
  import java.io.File
  import java.lang.Exception
  import javax.imageio.ImageIO
  import javax.swing.JComponent
  import scala.annotation.tailrec

  @tailrec
  def first[T](list: List[T], sought: T => Boolean): Option[T] = list match {
    case List() => None
    case head :: tail => if (sought(head)) Some(head) else first(tail, sought)
  }

  class GifExporter() {

    var fileNumber = 0

    def gif(component: JComponent): Unit = {
      try {
        val image = new BufferedImage(component.getSize().width,
          component.getSize().height, BufferedImage.TYPE_INT_ARGB)
        val graphics = image.createGraphics();
        component.paint(graphics)
        graphics.dispose();
        ImageIO.write(image, "gif", new File("capture" + fileNumber + ".gif"))
        fileNumber += 1
      } catch {
        case ex: Exception => ()
      }
    }
  }
}
