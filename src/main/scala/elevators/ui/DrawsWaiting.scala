package elevators.ui

import elevators.queue.RequestQueue

trait DrawsWaiting {
  def loadRequests(requests: RequestQueue[Int]): Unit
  def repaint(): Unit
}
