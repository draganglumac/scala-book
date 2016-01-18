package org.stairwaybook.funcqueue

trait Rational {
  val numerArg: Int
  val denomArg: Int

  lazy val numer = numerArg / g
  lazy val denom = denomArg / g

  override def toString = numer + "/" + denom

  private lazy val g = {
    require(denomArg != 0)
    gcd(numerArg.abs, denomArg.abs)
  }

  private def gcd(a: Int, b: Int): Int =
    if (b == 0) a else gcd(b, a % b)
}
