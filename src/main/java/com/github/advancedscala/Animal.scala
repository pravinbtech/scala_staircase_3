package com.github.advancedscala

trait Animal {
def eat(x:Int):Int
}

class Mystream[T]
{
 // def foreach(value: Any) = ???

  def -->:(Value:T):Mystream[T]={
  // println(this.-->:(Value))
    println(Value)
    println(this.getClass.getFields())
    this
  }

}


class mutable{
  def member(value: 69) = ???

  private var mutmember=0;
  def member=mutmember;
  def member_(value:Int)={
    mutmember=value;
     }
}

trait Action{
  def act(x:Int);
}




object carnivores extends Animal with App {

  //val action:Action=(x:Int)=>x

  override def eat(x: Int) = x+1
  val tiger=new Animal{
    override def eat(x: Int): Int = x+2
  }
val mystream: Mystream[Int] =1-->:2-->:3-->: new Mystream[Int];
println(mystream.isInstanceOf[Mystream[Int]])

val varmut=new mutable;
  varmut.member_(42)
  varmut.member(69)
  println(varmut.member)
}