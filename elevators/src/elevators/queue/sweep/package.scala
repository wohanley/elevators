package elevators.queue

import elevators._

package object sweep {
  
  def eligibleRequestFilter(position: Int, direction: SeekDirection) =
    direction match {
      case Up => gte(position)_
      case Down => lte(position)_
    }
  
  def createScanQueue(lowerBound: Int, upperBound:Int, requests: List[Int]):
    ScanQueue = {
    return new ScanQueue(0, lowerBound, upperBound, requests, Up)
  }
  
  def createLookQueue(requests: List[Int]): LookQueue = {
    return new LookQueue(0, requests, Up)
  }
}