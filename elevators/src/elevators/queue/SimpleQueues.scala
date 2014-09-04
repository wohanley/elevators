package elevators.queue

import elevators._

import scala.collection.immutable.Queue

/**
 * Very thin wrapper around a standard [[scala.collection.immutable.Queue]].
 * So thin, in fact, it manages to add no type constraints whatsoever.
 */
class FirstComeFirstServedQueue[T <% Ordered[T]](requests: Queue[T])
  extends RequestQueue[T] {
  
  override def enqueue(request: T): RequestQueue[T] = {
    return new FirstComeFirstServedQueue(requests.enqueue(request))
  }
  
  override def dequeue: (T, RequestQueue[T]) = {
    val dequeued = requests.dequeue
    return (dequeued._1, new FirstComeFirstServedQueue(dequeued._2))
  }
}

/**
 * This one is paramaterized because I need subtraction.
 */
class ShortestSeekQueue(position: Int, requests: List[Int])
  extends RequestQueue[Int] {
  
  override def enqueue(request: Int): RequestQueue[Int] = {
    return new ShortestSeekQueue(position, request :: requests)
  }
  
  override def dequeue: (Int, RequestQueue[Int]) = {
    val sorted = requests.sortBy(difference(position)_)
    return (sorted.head, new ShortestSeekQueue(sorted.head, sorted.tail))
  }
}