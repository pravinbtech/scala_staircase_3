package com.github.pravinbtech.mod4
  trait CompareAge[T] {
    def older(item: T): T

  }


  case class vintage(val make: Integer, val Color: String, val model: String) extends CompareAge[vintage] {

    def older(other: vintage): vintage = {
      if (this.make < other.make) this else other
    }
  }

  case class person(val name: String, val age: Integer) extends CompareAge[person] {
    override def older(item: person): person = {
      if (this.age > item.age) this else item
    }
  }

 object vintage {
  def main(args: Array[String]): Unit = {

    val mustang = vintage(1981, "Green", "mustang");
     val ford = vintage(1980, "Red", "Ford")
      val pravin = person("pravin", 31)
      val priya = person("priya", 27)
//
//
def golder[T <: CompareAge[T]](item1: T, item2: T): T = {
  item1 older item2
}



    println(golder(mustang,ford))
    println(golder(pravin,priya))

    }
  }

