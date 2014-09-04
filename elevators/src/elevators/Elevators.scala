package elevators

import scala.collection.immutable.Queue
import elevators.queue._
import elevators.queue.sweep._

object Elevators {

    def main(args: Array[String]) = {
      
      val requests = List(12,4,34,452,124,234,43,56,34,25,21)
      
      val fcfs = new FirstComeFirstServedQueue(Queue.concat(requests))
      val sstf = new ShortestSeekQueue(0, requests)
      val scan = createScanQueue(0, 500, requests)
      
      def next[T](queue: RequestQueue[T]): Unit = {
        val nextQueue = queue.dequeue
        println(nextQueue._1)
        next(nextQueue._2)
      }
      
      next(scan)
    }
}