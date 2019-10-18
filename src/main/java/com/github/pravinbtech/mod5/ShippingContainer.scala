package com.github.pravinbtech.mod5

class ShippingContainer_S [T]private(val items:Seq[T]){
  private[this] val maxCount=10
  private def ItemsFull= items.length>=maxCount

  override def toString: String = {
    s"${ShippingContainer_S.container_colour}"


  }

}
object ShippingContainer_S{

  def apply[T] (items: Seq[T]): ShippingContainer_S[T] = new ShippingContainer_S(items)

  def maxitems(container: ShippingContainer_S[_]): Boolean ={
    container.ItemsFull

  }
  val sc_1=ShippingContainer_S(Seq('d','e','f'))

println(ShippingContainer_S.maxitems(sc_1))


  private def container_colour="Green"

}
object ShippingContainer {
  def main(args: Array[String]): Unit = {
 val sc= ShippingContainer_S(Seq('a','b','c'))
println(sc.items)
    println(sc.toString)


  }
}
