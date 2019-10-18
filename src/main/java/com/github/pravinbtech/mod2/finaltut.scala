package com.github.pravinbtech.mod2

class finaltut (val name: String) {

  /*final */def myfunc(name: String) = {
    s"Hello name you passed is $name"
  }
}
  object finaltut{
  def main(args: Array[String]): Unit = {
    val finalext1=new finalext("Pravin")
    println(finalext1.myfunc("Ashwin"))

    }
}
class finalext(name: String) extends finaltut(name){
  override def myfunc(name :String)= {
    s"Hello $name and exteding final method you passed is "
  }
}