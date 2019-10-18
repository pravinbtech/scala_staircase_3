package com.github.pravinbtech.mod2

abstract class superclass{
  def blip:String
  def blop(name:String):String="BLOP"
  def op(x:Int,y:Int):Int
}

class norm extends superclass{
  def blip:String="Hello Blip function"
  override def blop(name:String)=s"Hello $name"
  def op(x:Int,y:Int)=x+y

}
object overridetut {
  def main(args: Array[String]): Unit = {
    val norm1 = new norm
    println(norm1.blip)
    println(norm1.blop("Pravin"))
    println(norm1.op(5, 10))
  }
}
