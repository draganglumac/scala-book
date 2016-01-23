package org.stairwaybook.equality

class Rational(val n: Int, val d: Int) {

  require(d != 0)

  private val g = gcd(n.abs, d.abs)
  val numer: Int = (if (d < 0) -n else n) / g
  val denom: Int = d.abs / g

  private def gcd(a: Int, b: Int): Int =
    if (b == 0) a else gcd(b, a % b)

  // Tip 3 - parameter other should always be of Any type
  override def equals(other: Any): Boolean =
  // Tip 4 - write body of equals as a single match expression
    other match {
      // Tip 5 - the match expression should have 2 cases, one for current class and one for other (_)

      case that: Rational => // Tip 6 - always && together expressions that must be true for two objects to be equal
        (that canEqual this) &&
          numer == that.numer &&
          denom == that.denom

      case _ => false // Tip 7 - case _ should always yield false
    }

  // Tip 1 - if overriding equals in non-final class implement canEqual
  def canEqual(other: Any): Boolean =
    other.isInstanceOf[Rational] // Tip 2 - the method should yield true for objects of current class only

  // Tips for hashCode
  // - include each field in the calculation
  // - calculate it using the following formula:
  //   41 * (41 * ... 41 * (41 + field_1.hashCode) + field_2.hashCode + ... + field_n-1.hashCode) + field_n.hashCode
  // - number 41 was selected because it's an odd prime, could be any other odd prime
  // - also additions don't have to be to 41 but to any other odd prime for example or any other integer
  //   so long as it yields a good scatter of hash values
  override def hashCode: Int =
    41 * (
      41 + numer
      ) + denom

  override def toString =
    if (denom == 1) numer.toString else numer + "/" + denom

}
