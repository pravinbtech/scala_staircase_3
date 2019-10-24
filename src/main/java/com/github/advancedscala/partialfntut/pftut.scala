package com.github.advancedscala.partialfntut

object pftut extends App{


  val aFun = (x:Int)=>x+1

  val afusfun=(x:Int)=>x match{
    case 1=> "one"
    case 2=>"Two"
    case 3=>"Three"
    case _=>throw new matchnotfoundexception
  }

  class matchnotfoundexception extends RuntimeException{

    println("Value not matched")

    val anicefn=(x:Int)=>x match{
      case 1=>"one"
      case 2=>"Two"
      case 3=>"three"

    }
  }


  pftut.afusfun(1)
//  pftut.afusfun(4)

  val pftut1=new PartialFunction[Int,Int] {
    override def isDefinedAt(x: Int): Boolean = {
      x==1 || x==2 || x==3
    }

    override def apply(v1: Int): Int = v1 match {
      case 1=>100
      case 2=>200
      case 3=>300
    }
  }

  println(pftut1(1))
  //println(pftut1(5))

  val pftut2=pftut1.orElse[Int,Int]{
    case _=>67
  }
println(pftut2(5))

//  val pftut3=new

  //scala.io.Source.stdin.getLines().foreach(println)
}
