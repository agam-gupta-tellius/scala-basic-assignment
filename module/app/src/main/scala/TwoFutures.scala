import scala.concurrent.{Await, Future}
import scala.concurrent.ExecutionContext.Implicits.global
import scala.util.{Try, Failure, Success}
import scala.util.Random

object TwoFutures {
  def handleFutureFailure[T](future: Future[T]): Future[T] = {
    future.recover {
      case exception: Exception =>
        println(s"Future failed with message: ${exception.getMessage}")
        throw exception
    }
  }
  def main(args: Array[String]): Unit = {
    val random = new Random();

    val future1: Future[String] = Future {
      Thread.sleep(1000);
      if(random.nextBoolean()) {
        throw new RuntimeException("Future 1 failed")
      }
      "future 1"
    }

    val future2: Future[String] = Future {
      Thread.sleep(1000);
      if (random.nextBoolean()) {
        throw new RuntimeException("Future 2 failed")
      }
      "future 2"
    }

    val combinedFuture = for {
      result1 <- handleFutureFailure[String](future1)
      result2 <- handleFutureFailure[String](future2)
    } yield (result1, result2)

    Thread.sleep(3000)
    println("resolving future")
    combinedFuture.onComplete {
      case Success(value) => println(s"Result of future: ${value}")
      case Failure(exception) => println(s"Failed with ${exception.getMessage}");
    }
  }
}
