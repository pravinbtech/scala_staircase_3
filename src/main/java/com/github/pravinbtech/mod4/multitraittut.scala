package com.github.pravinbtech.mod4

abstract class Car{
  def color:String
  def describe:String=s"$color"

  override def toString: String ={
    s"$describe car"
  }
}


trait Classic extends Car{
  def vintage:Integer

  override def describe: String ={
    s"vintage $vintage ${super.describe}"
  }
}

trait Convertible extends Car{
  override def describe: String ={
    s"convertible ${super.describe}"
  }
}

trait PoweredConvertible extends Convertible{
  override def describe: String = {
    s"Powered ${super.describe}"
  }
}

trait Hardtopconvertible extends Convertible{
  override def describe: String ={
    s"Hardtop ${super.describe}"
  }
}

class classiconvert1(val color:String,val vintage:Integer)
  extends Car with PoweredConvertible with Classic with Hardtopconvertible

class classiconvert2(val color:String,val vintage:Integer)
extends Car with Classic with PoweredConvertible with Hardtopconvertible

class classiconvert3(val color:String,val vintage:Integer)
extends Car with PoweredConvertible with Hardtopconvertible with Classic

class classiccar(val color:String,val vintage:Integer) extends Car with Classic
object multitraittut {

  def main(args: Array[String]): Unit = {
    val class1=new classiconvert1("red",1965)
    val class2=new classiconvert2("red",1965)
    val class3=new classiconvert3("red",1965)
    val class4=new classiccar("Black",2019) with Hardtopconvertible with PoweredConvertible
    println(class4.describe)
println(class1.describe)
println(class2.describe)
println(class3.describe)

  }
}
