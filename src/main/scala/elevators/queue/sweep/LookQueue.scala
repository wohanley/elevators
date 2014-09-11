package elevators.queue.sweep

import elevators._
import elevators.queue.RequestQueue

case class LookQueue(position: Int, requests: List[Int],
  direction: SeekDirection) extends RequestQueue[Int] {

  private lazy val compare = eligibleRequestFilter(position, direction)
  
  private lazy val eligibleRequests = requests.filter(req => compare(req))
  
  private lazy val service = if (eligibleRequests.nonEmpty) {
    eligibleRequests.sortBy(difference(position)_).head
  } else direction match {
    /*
     * If I'm looking up and there are no requests higher than my current
     * position, I need to turn around and head down, and the first request
     * I meet in that direction must be the largest in the list.
     */
    case Up => requests.max
    // converse logic here
    case Down => requests.min
  }
  
  override def enqueue(request: Int): LookQueue = {
    return new LookQueue(position, request :: requests, direction)
  }
  
  override def dequeue: (Int, LookQueue) = {
    return (this.service, new LookQueue(this.service,
      requests.filter(req => req != this.service), newDirection(this.service)))
  }

  def newDirection(service: Int): SeekDirection =
    direction match {
      case Up => if (service == requests.max) Down else Up
      case Down => if (service == requests.min) Up else Down
    }

  override def head: Int = this.service

  override def contains(request: Int): Boolean = requests.contains(request)

  override def isEmpty: Boolean = requests.isEmpty
}
