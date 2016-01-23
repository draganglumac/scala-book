package org.stairwaybook.equality

class Point(val x: Int, val y: Int) {
  override def hashCode = 41 * (41 + x) + y

  override def equals(other: Any): Boolean = other match {
    case that: Point => (that canEqual this) &&
      (that.x == this.x) && (that.y == this.y)

    case _ => false
  }

  def canEqual(other: Any): Boolean =
    other.isInstanceOf[Point]
}

object Colour extends Enumeration {
  val Red, Orange, Yellow, Green, Blue, Indigo, Violet = Value
}

class ColouredPoint(override val x: Int,
                    override val y: Int,
                    val colour: Colour.Value)
  extends Point(x, y) {

  override def hashCode = 41 * super.hashCode + colour.hashCode

  override def equals(other: Any) = other match {
    case that: ColouredPoint =>
      (that canEqual this) && super.equals(that) && this.colour == that.colour

    case _ => false
  }

  override def canEqual(other: Any) =
    other.isInstanceOf[ColouredPoint]
}