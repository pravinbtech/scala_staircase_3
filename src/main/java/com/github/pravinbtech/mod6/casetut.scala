package com.github.pravinbtech.mod6
case class Address (street:String,zipcode:Option[Int],city:String,country:Option[String])
case class person(name:String,phone:Option[String],address:Option[Address])
object casetut {
  def main(args: Array[String]): Unit = {
    val pravin=person("Pravin",Some("614-397-7372"),Some(Address("valley Ranch Pkwy",Some(75063),"Irving",Some("US"))))
    val aarushi=person("Pravin",Some("614-397-7373"),pravin.address)
    val priya=aarushi.copy("Priya",phone=aarushi.phone)
    val tamil=aarushi.copy("Tamil",Some("614-397-7374"),aarushi.address)

    def getpostcode(p:person)=p match {
      case person("Pravin",_,Some(Address(_,Some(zipcode),_,_)))=> zipcode
      case _=>"Unkown"
    }


    def typematch(x:Any)=x match{
      case i: Int=>println(s"$i integer")
      case d:Double=>println(s"$d double")
      case s:String=>println(s)
      case p:person=>println(p.name)
      case _=>println("Unknown")

    }


    val mypeople=List(aarushi,pravin,priya,tamil);
    for{
      person(name,Some(phone),_)<-mypeople
      if(Some(phone)!=None)
          } yield println(name->Some(phone).getOrElse(""))


    typematch(1)
    typematch(3.2)
    typematch("Pravin")
    typematch(aarushi)
    typematch(0)
    typematch(None)
    println(getpostcode(pravin))
    println(getpostcode(tamil))
    println(priya)
    println(pravin==aarushi);
    println(pravin==priya)
    println(pravin.hashCode())
    println(aarushi.productElementNames.toList)
    println(aarushi.productArity)
    println(aarushi.productIterator.toList)
  }

}
