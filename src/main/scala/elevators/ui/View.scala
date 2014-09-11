package elevators.ui

import elevators.queue.RequestQueue

trait View {
  def drawRequest(request: Int): Unit
  def drawService(request: Int): Unit
}
