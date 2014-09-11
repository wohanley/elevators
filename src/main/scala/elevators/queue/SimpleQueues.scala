package elevators.queue

import elevators._

import scala.collection.immutable.Queue

/**
 * Very thin wrapper around a standard [[scala.collection.immutable.Queue]].
 * So thin, in fact, it manages to add no type constraints whatsoever.
 */
case class FirstComeFirstServedQueue[T <% Ordered[T]](requests: Queue[T])
  extends RequestQueue[T] {
  
  override def enqueue(request: T): RequestQueue[T] = {
    return new FirstComeFirstServedQueue(requests.enqueue(request))
  }
  
  override def dequeue: (T, RequestQueue[T]) = {
    val dequeued = requests.dequeue
    return (dequeued._1, new FirstComeFirstServedQueue(dequeued._2))
  }

  override def head: T = requests.head
}

/**
 * This one is paramaterized because I need subtraction.
 */
case class ShortestSeekQueue(position: Int, requests: List[Int])
  extends RequestQueue[Int] {

  val sortedRequests = requests.sortBy(difference(position)_)

  override def enqueue(request: Int): RequestQueue[Int] = {
    return new ShortestSeekQueue(position, request :: requests)
  }
  
  override def dequeue: (Int, RequestQueue[Int]) = {
    return (sortedRequests.head, new ShortestSeekQueue(sortedRequests.head, sortedRequests.tail))
  }

  override def head: Int = requests.head
}
