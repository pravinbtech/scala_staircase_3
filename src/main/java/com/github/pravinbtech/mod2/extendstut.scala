package com.github.pravinbtech.mod2

abstract class vehicle(val name:String,val age:Integer) {
  override def toString: String = {
    s"$name is $age  years old"
  }
}

  class car (
              override val name:String,
              override val age:Integer,
              val make:String,
              val year:String) extends vehicle(name,age){

    override def toString: String ={
      s"a $make $year ,${super.toString}"
    }

}
object extendstut {
  def main(args: Array[String]): Unit = {
    val car1=new car("Sonata",5,"Hyundai","2015")
    println(car1.toString)
  }
}
