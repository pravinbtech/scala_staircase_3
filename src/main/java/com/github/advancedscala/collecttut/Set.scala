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

}

class EmptySet[A] extends MySet[A]{
  def contains(elem:A):Boolean=false
  def +(elem:A):MySet[A]=new NonemptySet[A](elem,this)
  def ++(anotherSet:MySet[A]):MySet[A]=anotherSet
  def map[B](f:A=>B):MySet[B]=new EmptySet[B]
  def foreach(f:A=>Unit):Unit=()
  def flatmap[B](f:A=>MySet[B]):MySet[B]=new EmptySet[B]
  def filter(predicate:A=>Boolean):MySet[A]=this
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

//  List(1,2,3) ++ aList(4,5)
//
//  List(2,3)++List(4,5)+1
//  List(3)++List(4,5)+1+2

  def ++(anotherSet:MySet[A]):MySet[A]= {
    tail ++ anotherSet + head
  }
  def map[B](f:A=>B):MySet[B]={
    (tail map f)+f(head)
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
  val s = MySet(1, 2, 3, 4)
  s.foreach(println(_))
  s + 5 ++ MySet(-1, -2) + 3 flatmap (x => MySet(x, 10 * x)) filter (_ % 2 == 0) foreach println

val d =MySet("a","b","c")

 val g= d+"e"
g.foreach(println(_))

}