package com.github.pravinbtech.mod2

import java.time.LocalDate
abstract class person(val name:String,val company:String,val year:Integer)
{
def isVintage:Boolean
}

object hello_world {
  def main(args: Array[String]): Unit = {
    println("hellomam")

    val mustang = new person("Ford", "Mustang", 1963) {
      def isVintage: Boolean = LocalDate.now.getYear - year > 18

      println(isVintage)

    }
   println( mustang.isVintage)
    println(mustang.company)

  }
}

