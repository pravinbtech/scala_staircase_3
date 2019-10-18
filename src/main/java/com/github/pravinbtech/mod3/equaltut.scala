package com.github.pravinbtech.mod3
class Person(val name:String,val age:Int){
  def myEquals(other:Person):Boolean={this.name == other.name && this.age==other.age}

  override def hashCode(): Int = 1

   override def equals(other:Any): Boolean={
     myEquals(other.asInstanceOf[Person])
   }
}


object equaltut{
  def main(args: Array[String]): Unit = {
    val p = new Person("Mike",57)

    val p0 = new Person("Mike",57)

    println(p.equals(p0));
    println(p==p0)

    println(p.myEquals(p0))

  }

}
