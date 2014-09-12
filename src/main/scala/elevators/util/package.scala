package elevators

package object util {

  import scala.annotation.tailrec

  @tailrec
  def first[T](list: List[T], sought: T => Boolean): Option[T] = list match {
    case List() => None
    case head :: tail => if (sought(head)) Some(head) else first(tail, sought)
  }
}
