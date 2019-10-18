package com.github.pravinbtech.mod6

import scala.util.Try;

object customseq {
  def unapplySeq(coordsStr: String) = Try {
     coordsStr.split(",").toList.map(_.trim.toDouble)
  }.toOption
}

  object customseqtut {

    def main(args: Array[String]): Unit = {

      val coordsStr = "-122.432, 34.002"

      val ml=List(5,6,7,8,9)
    val d1=ml.iterator
      if(d1.hasNext)
        println(d1.foreach(println))
      //val fibs:LazyList[BigInt]=BigInt(0)#::BigInt(1)#::fibs.zip(fibs.tail).map{ case (a,b)=>a+b}
      //println(fibs.take(5).toList)


      coordsStr match {
        case customseq(c @_*) =>
          c foreach println
      }
    }
  }




