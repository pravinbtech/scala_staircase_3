package com.github.pravinbtech.mod6

import scala.util.{Failure, Success, Try}

object patterntut {
  def main(args: Array[String]): Unit = {


    def matchlist(x: List[String]) =x match {
      case a :: b :: rest => println(s"$a,$b,$rest")

      case anythingElse => println("List Matching")
    }

    def opposite(x:String)= x match{
      case "Pravin" => println("Hello")
      case inword@ ("Aarushi"|"Ammu"|"Chellam")=>println("My Daughter")
      case _=>None
    }

val Maxlimit=10
    val minlimit=1
    def islimit(x:Int)=x match{
      case Maxlimit=>true
      case `minlimit`=>true
      case _=>false
    }


    def describeNumber(x: Int): String = x match {
      case 0 => "zero"
      case n if n > 0 && n < 100 => "smallish positive"
      case n if n > 0 => "large positive"
      case n if n < 0 && n > -100 => "smallish negative"
      case _ => "large negative"
    }

    def matchseq(x:Vector[Int])=x match{

      case 1 +: 2 +: rest => s"A 1, 2 vector followed by $rest"
      case 2 +: 4 +: rest=>println(s"2,4")
      case Vector(a,b,rest @_*)=> println(s"$a,$b,$rest")
      case _=>println("Unknown")
    }

    def utilmatch(t:Try[_])= t match {
      case Success(x)=>println("Try Method Passed")
      case Failure(e)=>println("Try Method Failed")
    }

    utilmatch(Try(None:Option[String]))
    utilmatch(Try("Hello"))
    utilmatch(Try(2/3))
    utilmatch(Try(2/0))

    matchseq(Vector(1,5,9))
    matchseq(Vector())
    matchseq(Vector(2,4))
    println(describeNumber(2))
    println(describeNumber(100))
    println(describeNumber(-101))

    println(islimit(10))
    println(islimit(1))
   println(islimit(3))
    matchlist(List("Hello", "Pravin"))
    matchlist(List("Priya","Pravin","Aarushi"))
    opposite("Pravin")
    opposite("Ammu")
    opposite("Priya")
  }
}
