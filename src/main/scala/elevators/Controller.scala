package elevators

import elevators.queue.RequestQueue
import elevators.ui.View

/**
 * It strikes me that Controller also conforms to the View trait. Is this a
 * good sign, or a bad one? (It could, that is)
 * TODO: add foreach to RequestQueue so that I can initialize this properly ()
 */ 
class Controller(requests: RequestQueue[Int], view: View) {

  private var queue = requests

  def enqueue(request: Int): Unit = {
    this.queue = this.queue.enqueue(request)
    view.requested(request)
  }

  def dequeue(): Unit = {
    val dequeue = this.queue.dequeue
    this.queue = dequeue._2
    view.serviced(dequeue._1)
  }
}



















