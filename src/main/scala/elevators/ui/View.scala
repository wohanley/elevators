package elevators.ui

import elevators.queue.RequestQueue

trait View {
  def drawQueue(requests: RequestQueue[Int]): Unit
  def drawRequest(request: Int): Unit
  def drawService(request: Int): Unit
}
