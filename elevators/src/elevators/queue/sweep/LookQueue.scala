package elevators.queue.sweep

import elevators._
import elevators.queue.RequestQueue

class LookQueue(position: Int, requests: List[Int],
  direction: SeekDirection) extends RequestQueue[Int] {

  override def enqueue(request: Int): LookQueue = {
    return new LookQueue(position, request :: requests, direction)
  }
  
  override def dequeue: (Int, LookQueue) = {
    
    val compare = eligibleRequestFilter(position, direction)
    
    val service = requests
      .filter(req => compare(req))
      .sortBy(difference(position)_).head
        
    return (service, new LookQueue(service,
      requests.filter(req => req != service), newDirection(service)))
  }
  
  def newDirection(service: Int): SeekDirection =
    direction match {
      case Up => if (service == requests.max) Down else Up
      case Down => if (service == requests.min) Up else Down
    }
}