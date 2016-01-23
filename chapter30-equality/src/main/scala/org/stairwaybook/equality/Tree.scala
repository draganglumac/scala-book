package org.stairwaybook.equality

trait Tree[+T] {
  def elem: T

  def left: Tree[T]

  def right: Tree[T]
}

object EmptyTree extends Tree[Nothing] {
  def elem =
    throw new NoSuchElementException("EmptyTree.elem")

  def left =
    throw new NoSuchElementException("EmptyTree.left")

  def right =
    throw new NoSuchElementException("EmptyTree.right")
}

class Branch[+T](
                  val elem: T,
                  val left: Tree[T],
                  val right: Tree[T]
                ) extends Tree[T] {

  override def equals(other: Any): Boolean = other match {
    //    case that: Branch[T] => // compiler erases the type parameter T so can't really check that it's the correct type and you'll get a warning,
    case that: Branch[t] => // so instead, just use the 'unknown' type which starts with a lowercase, can also use Branch[_]
      this.elem == that.elem &&
        this.left == that.left &&
        this.right == that.right

    case _ => false
  }

  override def hashCode: Int =
    41 * (
      41 * (
        41 + elem.hashCode
        ) + left.hashCode
      ) + right.hashCode

  def canEqual(other: Any) = other match {
    case that: Branch[_] => true
    case _ => false
  }

//  // could have also done this
//  def canEqual(other: Any) = other.isInstanceOf[Branch[_]]

}