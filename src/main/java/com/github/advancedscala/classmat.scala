package com.github.advancedscala

class classmat(val name:String, val Age:Int)

object classmat extends App{
  def unapply(arg: classmat): Option[(String, Int)] = {

    if(arg.Age>21)
      Some(arg.name,arg.Age)
  else
      None
  }

  def unapply(age:Int): String = {
  if(age<21)
"The given  age is Minor"
    else
 "The given age is Major"
  }

  val pravin=new classmat("Pravin",30)

 val bstres= pravin match{
    case classmat(n,a)=>s"Best student named$n, aged $a"
  }

  println(bstres)

  case class OR[A,B](a:A,b:B);
   val either=OR(2,"Pravin")
  either match {
    case number OR string=>println(s"$string aged $number years old")
  }

  abstract class Mylist[+T] {
    def head:T= ???
    def tail:Mylist[T] = ???
  }

  case object Empty extends Mylist[Nothing]
  case class cons[+T] (override val head:T,override val tail:Mylist[T]) extends Mylist[T]

  object Mylist{

    def unapplySeq[T](list:Mylist[T]): Option[Seq[T]]=
   if(list==Empty) None
      else
     unapplySeq(list.tail).map(list.head +: _)
  }

  val mylist:Mylist[Int]=cons(1,cons(2,cons(3,Empty)))
  println(mylist.head)
  println(mylist.tail)

  val decomposed=mylist match{
    case Mylist(1,2,_*)=> println("List starting with 1,2")
    case _=>println("something")
  }

val family=List("Aarushi","Pravin","Priya")
  println(family.map(_.length))
//  abstract class MyList[+A] {
//    def head: A = ???
//    def tail: MyList[A] = ???
//  }
//  case object Empty extends MyList[Nothing]
//  case class Cons[+A](override val head: A, override val tail: MyList[A]) extends MyList[A]
//
//  object MyList {
//    def unapplySeq[A](list: MyList[A]): Option[Seq[A]] =
//      if (list == Empty) Some(Seq.empty)
//      else unapplySeq(list.tail).map(list.head +: _)
//  }
//
//  val myList: MyList[Int] = Cons(1, Cons(2, Cons(3, Empty)))
//  val decomposed = myList match {
//    case MyList(1, 2, _*) => "starting with 1, 2"
//    case _ => "something else"
//  }
//
//  println(decomposed)




}

