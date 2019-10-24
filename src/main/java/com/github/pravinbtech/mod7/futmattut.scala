package com.github.pravinbtech.mod7

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.{Await, Future}
import scala.util.Success

object futmattut extends App {

  def calc(x: Int): Future[Int] = Future {
    println(s"Calculating $x")
    //  Thread.sleep(500)
    x * x

  }

  def processed(xs: Vector[Int]): Future[Vector[Int]] = {
    //val allfutures: Vector[Future[Int]] =xs.map(calc)
    Future.traverse(xs)(calc)
  }

 // val t1: Future[Vector[Int]] = futmattut.processed(Vector(1, 2, 3, 4));
  //Await.result(t1,10.seconds)
  //println(t1.isCompleted)

  //  val addres: Future[Vector[Int]] =for {
  //    t2<-t1
  //  } yield t2
  //
  //  val finalres: Future[Any] =addres.recover{case ex:Exception => -1}
  //  finalres.onComplete{
  //    case Success(i)=>println(s"$i")
  //    case _=>println(s"not completed")
  //  }
  //  println(finalres.isCompleted)

 import scala.concurrent.duration._;
//  Await.result(t1, 10.seconds)
//  t1.onComplete {
//    case Success(i) => println(s"$i")
//    case _ => println("Failed")
//  }

  def processseqbatch(xs: Vector[Int], batchsz: Int): Future[Vector[Int]] = {
    val batches: Iterator[Vector[Int]] = xs.grouped(batchsz)
    val start: Future[Vector[Int]] = Future.successful(Vector.empty[Int])
    batches.foldLeft(start) { (accF, batch) =>
      for {
        acc <- accF
        batchres <- processed(batch)
      } yield acc ++ batchres

    }
  }

    val t4: Vector[Int] = (1 to 100).toVector
    val t2 = futmattut.processseqbatch(t4, 25)

    Await.result(t2, 10.seconds)
  t2.onComplete{
    case Success(i)=>println(s"$i")
    case _=>println("Not processed")
  }

}