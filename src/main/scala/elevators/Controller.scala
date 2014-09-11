package elevators

import elevators.queue.RequestQueue
import elevators.ui.View

case class Controller(requests: RequestQueue[Int], view: View) {

  def enqueue(request: Int): Controller = {
    val enqueued = requests.enqueue(request)
    view.drawRequest(request)
    view.drawQueue(enqueued)
    return Controller(enqueued, view)
  }

  def dequeue(): (Int, Controller) = {
    val dequeue = requests.dequeue
    view.drawService(dequeue._1)
    view.drawQueue(dequeue._2)
    return (dequeue._1, Controller(dequeue._2, view))
  }
}
