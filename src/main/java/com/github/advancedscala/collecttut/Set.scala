package com.github.advancedscala.collecttut

import scala.annotation.tailrec

trait MySet[A] extends (A=> Boolean) {

  def apply(elem:A): Boolean = contains(elem)
  def contains(elem:A):Boolean
  def +(elem:A):MySet[A]
  def ++(anotherSet:MySet[A]):MySet[A]
  def map[B](f:A=>B):MySet[B]
  def foreach(f:A=>Unit):Unit
  def flatmap[B](f:A=>MySet[B]):MySet[B]
  def filter(Predicate:A=>Boolean):MySet[A]
  def -(elem:A):MySet[A] //removing
  def &(anotherset:MySet[A]):MySet[A] //Intersection
  def --(anotherset:MySet[A]):MySet[A] //difference
  def unary_! :MySet[A]
}

class EmptySet[A] extends MySet[A]{
  def contains(elem:A):Boolean=false
  def +(elem:A):MySet[A]=new NonemptySet[A](elem,this)
  def ++(anotherSet:MySet[A]):MySet[A]=anotherSet
  def map[B](f:A=>B):MySet[B]=new EmptySet[B]
  def foreach(f:A=>Unit):Unit=()
  def flatmap[B](f:A=>MySet[B]):MySet[B]=new EmptySet[B]
  def filter(predicate:A=>Boolean):MySet[A]=this
  def -(elem:A):MySet[A]=this
  def &(anotherset:MySet[A]):MySet[A]=this
  def --(anotherset:MySet[A]):MySet[A]=this
  //def unary_! :MySet[A]=this
  def unary_! : MySet[A]=new PropertybasedSet[A](_=>true)
}

class NonemptySet[A] (head:A,tail:MySet[A])extends MySet[A]{
  def contains(elem:A):Boolean={
    head==elem || tail.contains(elem)
  }
  def +(elem:A):MySet[A]={
if (this contains elem)
  this
    else
  new NonemptySet[A](elem,this)
  }

//  List(1,2,3) ++ List(4,5)
//
//  List(2,3)++List(4,5)+1
//  List(3)++List(4,5)+1+2

  def ++(anotherSet:MySet[A]):MySet[A]= {
    tail ++ anotherSet + head
  }
  def map[B](f:A=>B):MySet[B]={
    (tail.map(f) +f(head))
  }
  def foreach(f:A=>Unit):Unit={
    tail foreach f
    f(head)
  }
  def flatmap[B](f:A=>MySet[B]):MySet[B]={
    (tail flatmap f) ++ f(head)
  }
  def filter(predicate:A=>Boolean):MySet[A]={
 val filteredtail: MySet[A] =tail.filter(predicate)
    if(predicate(head))
      filteredtail+head
    else
      filteredtail

  }

  def -(elem:A):MySet[A]={
    if(head==elem) tail
    else
      tail-elem+head
  }

  def &(anotherset:MySet[A]):MySet[A]={
  //filter(x=>anotherset.contains(x))
   // filter(x=>anotherset(x))
    filter(anotherset)
  }

  def --(anotherset:MySet[A]):MySet[A]={
//filter(x => !anotherset.contains(x))

    filter (x=> !anotherset(x))
  }

  def unary_! : MySet[A]=new PropertybasedSet[A](x=> !this.contains(x))
}


class PropertybasedSet[A] (property:A=>Boolean) extends MySet[A] {

  def contains(elem:A):Boolean=property(elem)

//  def contains(elem: A): Boolean = property(elem)
  // { x in A | property(x) } + element = { x in A | property(x) || x == element }
  def +(elem: A): MySet[A] =
    new PropertybasedSet[A](x=>property(x)|| x==elem)
  //  new PropertybasedSet[A](x => property(x) || x == elem)

  // { x in A | property(x) } ++ set => { x in A | property(x) || set contains x }
  def ++(anotherSet: MySet[A]): MySet[A] =
    new PropertybasedSet[A](x => property(x) || anotherSet(x))

  // all integers => (_ % 3) => [0 1 2]
  def map[B](f: A => B): MySet[B] = politelyFail
  def flatmap[B](f: A => MySet[B]): MySet[B] = politelyFail
  def foreach(f: A => Unit): Unit = politelyFail

  def filter(predicate: A => Boolean): MySet[A] = new PropertybasedSet[A](x => property(x) && predicate(x))
  def -(elem: A): MySet[A] = filter(x => x != elem)
  def --(anotherSet: MySet[A]): MySet[A] = filter(!anotherSet)
  def &(anotherSet: MySet[A]): MySet[A] = filter(anotherSet)
  def unary_! : MySet[A] = new PropertybasedSet[A](_=>false)

  def politelyFail = throw new IllegalArgumentException("Really deep rabbit hole!")
}




object MySet{

  /*
    val s = MySet(1,2,3) = buildSet(seq(1,2,3), [])
    = buildSet(seq(2,3), [] + 1)
    = buildSet(seq(3), [1] + 2)
    = buildSet(seq(), [1, 2] + 3)
    = [1,2,3]
   */
  def apply[A](values: A*): MySet[A] = {
    @tailrec
    def buildSet(valSeq: Seq[A], acc: MySet[A]): MySet[A] =
      if (valSeq.isEmpty) acc
      else buildSet(valSeq.tail, acc + valSeq.head)

    buildSet(values.toSeq, new EmptySet[A])
  }
}
object MySetPlayground extends App {
  val s = MySet(1, 2, 3, 4,5)
  s + 5 ++ MySet(-1, -2) + 3 foreach (println(_))
  //flatmap (x => MySet(x, 10 * x)) filter (_ % 2 == 0) foreach println
  s.foreach(println(_))
  val d = MySet("a", "b", "c")

  // val g= d+"e"
  //g.foreach(println(_))
  val negative = !s ++ MySet(8,10,12)

  //x.foreach(println(_))
  println(negative(2))
  println(negative(9))

  val negativeEven = negative.filter(_ % 2 == 0)
  println(negativeEven(10))
 println(negativeEven(20))
  val negativeEven5 = negativeEven + 10 // all the even numbers > 4 + 5
  println(negativeEven5(14))

  val pri: Boolean =MySet(1,2,3) contains(5)
  println(pri)

}