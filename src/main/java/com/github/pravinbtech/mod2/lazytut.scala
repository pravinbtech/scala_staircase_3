package com.github.pravinbtech.mod2
class demo {
  val a: Int = {
    println("Evaluating a")
    10
  }
  val b:Int={
    println("Evaluating b")
    20
  }

  lazy val c:Int={
    println("Evaluating c")
    30
  }
}
object lazytut {

  def main(args: Array[String]): Unit = {
val demo1=new demo();
    val demo2=new demo();
    println(demo1.a)
    println(demo2.a)
    println(demo1.c)
    println(demo1.c)
    println(demo1.b)
    println(demo2.b)
  }

}
