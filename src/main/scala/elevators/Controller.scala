package elevators

import elevators.queue.RequestQueue
import elevators.ui.View

/**
 * It strikes me that Controller also conforms to the View trait. Is this a
 * good sign, or a bad one?
 */ 
class Controller[T](requests: RequestQueue[T], view: View[T]) {

  private var queue = requests

  def enqueue(request: T): Unit = {
    this.queue = this.queue.enqueue(request)
  }

  def dequeue(): Unit = {
    val dequeue = this.queue.dequeue
    this.queue = dequeue._2
    
  }
}



















