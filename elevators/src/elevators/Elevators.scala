package elevators

import scala.collection.immutable.Queue
import java.util.Random
import elevators.queue._
import elevators.queue.sweep._

object Elevators {
  
  val random = new Random()

  def main(args: Array[String]) = {
    
    val requests = for { i <- List.range(1, 100) } yield random.nextInt(500)
    
    val fcfs = new FirstComeFirstServedQueue(Queue.concat(requests))
    val sstf = new ShortestSeekQueue(0, requests)
    val scan = createScanQueue(0, 500, requests)
    val look = createLookQueue(requests)
    
    def service(queue: RequestQueue[Int]): Unit = {
      val nextQueue = queue.dequeue
      println(nextQueue._1)
      service(nextQueue._2.enqueue(random.nextInt(500)))
    }
    
    service(look)
  }
}