package com.github.pravinbtech.mod4

import java.util.UUID

import scala.util.Try

trait fakeacess_t{
  def save[T](item:T):String
  def load[T](id:String):Option[T]
}

class fakeaccessdb extends fakeacess_t
{
  private [this] var itemmap=Map.empty[String,Any];
  def save[T](item:T):String={
val uuid=UUID.randomUUID().toString
    println(uuid)
    itemmap=itemmap+(uuid->item)
    uuid
  }

  def load[T](id:String):Option[T]={
  Try(itemmap(id).asInstanceOf[T]).toOption
  }
}
case class person(name:String,age:Integer)
object fakeaccess {
  def main(args: Array[String]): Unit = {

    val p=new person("Pravin",30)
    val fdb=new fakeaccessdb
    val t=fdb.save(p)
   println( fdb.load(t).get);

  }
}
