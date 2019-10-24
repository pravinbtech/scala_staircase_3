package com.github.pravinbtech.mod7

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.{Await, Future, Promise}
import scala.io.Source
import scala.util.Success
object futuretut extends App {
 val f: Future[Int] = Future(1 + 1)
 val f2 = f.map(_ * 2)

 println(f.isCompleted)
 println(f2.isCompleted)
 println(s"$f+$f2")


 val a = 1
 val b = 2
 val c = 3
 val d = "The Answer is"
 val sum = a + b + c
 println(s"$d $sum")

 val af = Future {
  1
 }
 val bf = Future {
  Thread.sleep(1000); 2
 }
 val cf = Future {
  Thread.sleep(1000);
  4
 }

 val Fres: Future[String] = for {
  a <- af
  b <- bf
  c <- cf

 } yield {
  val sumf = a + b + c
  s"sum is $sumf"
 }

 import scala.concurrent.duration._;
 //Await.result(Fres,2.seconds)
 // Await.ready(Fres,2.seconds)
 Await.ready(Fres, 2.seconds)
 Await.result(Fres, 2.seconds)
 println(Fres.value)
 Thread.sleep(1000)
 println(Fres.value)


 val fa: Future[Any] = Future {
  'a'
 }
 val fi = fa.collect {
  case i: Int => i
  case _ => throw new RuntimeException
  //case _=>println("Exception")
 }
 println(fi.value)
 val ffi = fi.filter(_ > 11)
 val ffa: Future[Int] = ffi.transform(i => i * 5, { ex =>
  println("Exception in Future Transform")
  throw new RuntimeException("It is Failed", ex)
 })
 println(ffa.value)

 val f6: Future[Unit] = Future {

  for (lines <- Source.fromFile("/opt/scala_staircase_1/hello1.dat").getLines())
   if (lines.length > 0)
    println(lines)

 }
 val f8: Future[Unit] = Future {

  for (lines <- Source.fromFile("/opt/scala_staircase_1/hello.dat").getLines())
   println(lines)

 }

 f6.andThen {
  case Success(i) => println(s"File Processed l")
  case _ => f6.fallbackTo(f8)
 }
 println(f6.value)


 val failedfut = Future.failed(new RuntimeException("ooh"))
 failedfut.fallbackTo(Future.successful(0))
 Thread.sleep(2000)
 println(failedfut.value)

 val ff: Future[Nothing] = Future.failed(new IllegalArgumentException("nope"))

 val fr: Future[Int] = ff.recover {
  case _: IllegalArgumentException => 22
 }

 //Thread.sleep(2000)
 println(fr.value)

 fr.onComplete {
  case Success(i) => println(s"$i")
  case _ => 40
 }
fr.andThen {
 case Success(i) => i
}

 val num1=1 until 10
 val num=num1.toList
def square(x:Int)={
 Future(x*x)
}
 val fsq: List[Future[Int]] =num.map(square)
fsq.map(_.onComplete{
case Success(i)=>println(s"$i")
case _=>println("Not calculated")
})

 val futlist=Future.sequence(fsq)
 val futtravlist=Future.traverse(num)(square)
 println(futlist.value)
 println(futtravlist.value)

 val ft1=Future{5}
 val ft2=Future{Thread.sleep(2000);10}
 val ft3=Future{Thread.sleep(2000);50}
 val sft: List[Future[Int]] =List(ft1,ft2,ft3)


println(Await.ready(Future.firstCompletedOf(sft),2.seconds))
println(Await.ready(Future.foldLeft(sft)(0)(_+_),2.seconds))
println(Await.ready(Future.reduceLeft(sft)(_+_),2.seconds))

 val promise=Promise[Int]
 val futprom: Future[Int] =promise.future

 promise.success(10)
 println(futprom.value)

 val promise2=Promise[Int]
 val futprom2=promise2.future
 promise2.failure(new IllegalArgumentException("Nah"))
 println(futprom2.value)


}