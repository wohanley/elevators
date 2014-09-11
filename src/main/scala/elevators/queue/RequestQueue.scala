package elevators.queue

trait RequestQueue[T] {
  def enqueue(request: T): RequestQueue[T]
  def dequeue: (T, RequestQueue[T])
  def head: T
  def isEmpty: Boolean
}

class RequestQueueIterator[T](queue: RequestQueue[T]) extends Iterator[T] {

  var currentQueue = queue

  override def hasNext: Boolean = {
    return !currentQueue.isEmpty
  }

  override def next: T = {
    val dequeued = currentQueue.dequeue
    currentQueue = dequeued._2
    return dequeued._1
  }
}
