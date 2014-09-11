package object elevators {

  import scala.util.Random

  abstract class SeekDirection
  case object Up extends SeekDirection
  case object Down extends SeekDirection
  
  def difference(x: Int)(y: Int): Int = Math.abs(x - y)
  
  /**
   * Note that this actually flips the arguments - it is intended to be
   * partially applied. Read it as "greater than or equal to x".
   */
  def gte[T <% Ordered[T]](x: T)(y: T): Boolean = x <= y
  
  /**
   * Note that this actually flips the arguments - it is intended to be
   * partially applied. Read it as "less than or equal to x".
   */
  def lte[T <% Ordered[T]](x: T)(y: T): Boolean = x >= y

  def generateRequest(controller: Controller, gen: Random): Controller = {
    var request = gen.nextInt(50)
    while (controller.requests.contains(request)) {
      request = gen.nextInt(50)
    }
    return controller.enqueue(request)
  }
}
