package elevators

import scala.collection.immutable.Queue
import java.util.Random
import elevators.queue._
import elevators.queue.sweep._

object Elevators {
  
  val random = new Random()

  def main(args: Array[String]) = {
    
    val requests = List(12,4,34,452,124,234,43,56,34,25,21)
    
    val fcfs = new FirstComeFirstServedQueue(Queue.concat(requests))
    val sstf = new ShortestSeekQueue(0, requests)
    val scan = createScanQueue(0, 500, requests)
    
    def service(queue: RequestQueue[Int]): Unit = {
      val nextQueue = queue.dequeue
      println(nextQueue._1)
      service(nextQueue._2.enqueue(random.nextInt(500)))
    }
    
    service(scan)
  }
}