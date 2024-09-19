import RetryObject.{retryFunction, retryFunctionWithBackOff}

import scala.util.{Failure, Success}
import scala.util.Random

object RetryApp{
  def main(args: Array[String]): Unit = {
    
    def blockOfCode(): Int = {
        val random = new Random();
        if(random.nextBoolean()) 1
        else throw new RuntimeException("No value for you")
    }

    retryFunction[Int](blockOfCode(), 3) match
      case Success(value) => println(value)
      case Failure(exception) => println(exception.getMessage)

    retryFunctionWithBackOff[Int](blockOfCode(), 3, 2000) match {
      case Success(value) => println(value)
      case Failure(exception) => println(exception.getMessage)
    }
  }
}
