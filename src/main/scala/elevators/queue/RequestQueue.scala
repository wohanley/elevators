package elevators.queue

trait RequestQueue[T] {
  def enqueue(request: T): RequestQueue[T]
  def dequeue: (T, RequestQueue[T])
  def head: T
  def isEmpty: Boolean
}

object RequestQueueExt {
  implicit object RequestQueueIterator[T] extends Iterator[RequestQueue[T]] {
    def hasNext[T](queue: RequestQueue[T]): Boolean = {
      return queue.head != null
    }
  }
}
