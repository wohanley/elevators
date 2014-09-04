package elevators

import scala.collection.immutable.Queue

object Strategies {

    def firstComeFirstServed(position: Int, requests: Queue[Int],
        direction: SeekDirection): (Int, Queue[Int]) = {
      return requests.dequeue
    }
    
    def shortestSeekFirst(position: Int, requests: Queue[Int],
        direction: SeekDirection): (Int, Queue[Int]) = {
      requests.map(request => Math.abs(request - position)).sorted.dequeue
    }
    
    def scan(position: Int, requests: Queue[Int],
        direction: SeekDirection): (Int, Queue[Int]) = {
      requests.map(request => Math.abs(request - position)).sorted.dequeue
    }
}