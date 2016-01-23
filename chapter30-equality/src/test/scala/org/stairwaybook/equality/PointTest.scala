package org.stairwaybook.equality

import org.scalatest.FunSuite

class PointTest extends FunSuite {

  val p, q = new Point(1, 2)
  val cp = new ColouredPoint(1, 2, Colour.Green)
  val pAnon = new Point(1, 1) {
    override val y = 2
  }

  test("colour equality") {
    assert(p == q && q == p)
    assert(p == pAnon && pAnon == p)
    assert(p != cp && cp != p)
  }

  test("collection membership") {
    val coll = List(p)
    // Point and anonymous match...
    assert(coll contains p)
    assert(coll contains pAnon)

    // ...but ColouredPoint is no good here
    assert(!coll.contains(cp))
  }
}
