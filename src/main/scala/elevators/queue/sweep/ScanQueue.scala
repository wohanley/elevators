package elevators.queue.sweep

import elevators._
import elevators.queue.RequestQueue

case class ScanQueue(position: Int, lowerBound: Int, upperBound: Int,
    requests: List[Int], direction: SeekDirection) extends RequestQueue[Int] {

  private lazy val compare = eligibleRequestFilter(position, direction)
  
  private lazy val eligibleRequests = requests.filter(req => compare(req))

  private lazy val service = if (eligibleRequests.nonEmpty) {
    eligibleRequests.sortBy(difference(position)_).head
  } else direction match {
    case Up => upperBound
    case Down => lowerBound
  }
  
  override def enqueue(request: Int): ScanQueue = {
    return new ScanQueue(position, lowerBound, upperBound, request :: requests,
      direction)
  }
  
  override def dequeue: (Int, RequestQueue[Int]) = {
    return (this.service, new ScanQueue(this.service, lowerBound, upperBound,
      requests.filter(req => req != this.service), newDirection(this.service)))
  }

  override def head: Int = this.service
  
  def newDirection(service: Int): SeekDirection = direction match {
    case Up => if (service == upperBound) Down else Up
    case Down => if (service == lowerBound) Up else Down
  }
}
