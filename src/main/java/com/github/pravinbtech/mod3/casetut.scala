package com.github.pravinbtech.mod3
class triplet1( val i: Int,val name: Char,val Str : String){


  def canEqual(other: Any): Boolean = other.isInstanceOf[triplet1]

  override def equals(other: Any): Boolean = other match {
    case that: triplet1 =>
      (that canEqual this) &&
        i == that.i &&
        name == that.name &&
        Str == that.Str
    case _ => false
  }

  override def hashCode(): Int = {
    val state = Seq(i, name, Str)
    state.map(_.hashCode()).foldLeft(0)((a, b) => 31 * a + b)
  }
}
//class triplet2(i:Int,name: Char,Str: String);
case class  triplet3(i:Int,name: Char,Str : String);
//case class triplet4(i:Int,name:Char,Str: String);
object casetut {




  def main(args: Array[String]): Unit = {


  val trip6: (Int, Char, String) =(1,'A',"First");
    val trip1=new triplet1(1,'A',"First");
    val trip2=new triplet1(1,'B',"First");
    val trip3=triplet3(1,'A',"First");
    //val trip4=triplet4(1,'A',"First");
    val trip5=triplet3(1,'A',"First")
    println(trip1.equals(trip2));
    //println(trip1.equals (trip6));
    println(trip1.eq(trip2));
    println(trip1==trip2);
//    println(trip3==trip4);
//    println(trip3.eq(trip4));
//    println(trip3.equals(trip4));
    println(trip3==trip5);
    println(trip5==(1,'A',"First"));
  println(trip3.productIterator.toList)
    println(trip3.productArity)
    println(trip3.productElementNames)
    println(trip3.productElementName(1))
  }

}
