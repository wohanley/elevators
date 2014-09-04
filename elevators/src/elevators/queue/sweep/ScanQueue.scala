package elevators.queue.sweep

import elevators._
import elevators.queue.RequestQueue

class ScanQueue(position: Int, lowerBound: Int, upperBound: Int,
    requests: List[Int], direction: SeekDirection) extends RequestQueue[Int] {
  
  override def enqueue(request: Int): ScanQueue = {
    return new ScanQueue(position, lowerBound, upperBound, request :: requests,
      direction)
  }
  
  override def dequeue: (Int, RequestQueue[Int]) = {
    
    val compare = eligibleRequestFilter(position, direction)
    
    val eligibleRequests = requests.filter(req => compare(req))

    val service = if (eligibleRequests.nonEmpty) {
      eligibleRequests.sortBy(difference(position)_).head
    } else direction match {
      case Up => upperBound
      case Down => lowerBound
    }
        
    return (service, new ScanQueue(service, lowerBound, upperBound,
      requests.filter(req => req != service), newDirection(service)))
  }
  
  def newDirection(service: Int): SeekDirection = direction match {
    case Up => if (service == upperBound) Down else Up
    case Down => if (service == lowerBound) Up else Down
  }
}