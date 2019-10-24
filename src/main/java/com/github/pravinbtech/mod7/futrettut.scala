package com.github.pravinbtech.mod7

import scala.concurrent.{Await, Future}
import scala.util.{Success, Try}
import scala.util.control.NonFatal
import scala.concurrent.ExecutionContext.Implicits.global

object  futrettut extends App {
    var time = 0;

    def calc(): Int = {
      println(s"Time: $time")
      if (time > 3) 10 else {
        time += 1
        throw new IllegalStateException("not yet")
      }
    }
def resettries()= time=0;

 println(Try(calc()));
  println(Try (calc()));
  println(Try(calc()));
  println(Try(calc()));
  println(Try(calc()));


  def retry[T](op: => T,retries:Int):Future[T]={
  Future(op)recoverWith{ case NonFatal(_) if retries>0 => retry(op,retries-1)}
  }

  val f4: Future[Int] = retry(calc(), 5)
import scala.concurrent.duration._;
 Await.result(f4,10.seconds)
  println(f4.isCompleted)
  f4.onComplete{
    case Success(i)=>println(s"$i")
  }
  }
