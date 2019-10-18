package com.github.pravinbtech.mod4

trait Cars
{
 def name: String
  def color= {
    println(s"$name is black in color")
  }
}

class vehicle (val name:String,val colour:String)extends Cars

class lorry extends Cars with Function1[String,Int]{

override val name="Hello"
  override def apply(v1: String): Int ={
    (name+v1).length
  }
}

class Demo extends Cars with Function1[String, String] {
  val name="Lorry"
  override def color = "red"
  override def apply(v1:String): String = s"$v1 $color"
}
object traittut {
  def main(args: Array[String]): Unit = {
  val nerve: vehicle =new vehicle("Jaguar","Green");
    nerve.color

    val demo = new Demo
    demo("cherry") // cherry red
    val descriptionLength = demo.andThen(_.length)
    descriptionLength("Aarushi") // 1
    val newborn=new lorry
    val ne1=newborn.andThen(_.+(100))
  ne1("Aarushi")





    val mustang = new Cars {
       val name = "Mustang";

    }
    mustang.color
    val news: Cars =mustang
    val news1=nerve
    news1.color
    news.color
  }
}

