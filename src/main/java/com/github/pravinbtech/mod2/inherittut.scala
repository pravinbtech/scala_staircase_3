package com.github.pravinbtech.mod2

abstract class fruit{
  def name:String
}

abstract class orange extends fruit

class food(val name: String) extends fruit{

}
object inherittut {

  def main(args: Array[String]): Unit = {

    val apple=new food("Orange");

    println(apple.name)

  }
}
