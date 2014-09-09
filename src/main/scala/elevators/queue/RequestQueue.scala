package elevators.queue

trait RequestQueue[T] {
  def enqueue(request: T): RequestQueue[T]
  def dequeue: (T, RequestQueue[T])
}