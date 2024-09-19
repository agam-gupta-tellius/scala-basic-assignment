import scala.annotation.tailrec
import scala.util.{Failure, Success, Try}

object RetryObject {

  @tailrec
  def retryFunction[T](block: => T, retries: Int): Try[T] = {
    Try(block) match {
      case Success(value) => Success(value)
      case Failure(_) if retries > 0 => {
        println(s"$retries attempts left...")
        retryFunction(block, retries-1)
      }
      case Failure(exception) => Failure(exception)
    }
  }
  
  @tailrec
  def retryFunctionWithBackOff[T](block: => T, retries: Int, delay: Int): Try[T] = {
    val result = Try(block);
    if(result.isSuccess) return result; 
    if(retries > 0) {
      println(s"$retries attempts left..., retrying after ${delay/1000} seconds...")
      Thread.sleep(delay);
      retryFunctionWithBackOff(block, retries - 1, delay)
    }
    else result
  }
}
