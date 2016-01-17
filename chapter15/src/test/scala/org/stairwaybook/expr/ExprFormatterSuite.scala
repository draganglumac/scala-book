package org.stairwaybook.expr

import org.scalatest.FunSuite
import org.stairwaybook.layout.Element
import org.stairwaybook.layout.Element.elem

class ExprFormatterSuite extends FunSuite {

  val formatter = new ExprFormatter

  def assertEquals(actual: Element, expected: Element): Unit =
    assert(actual.contents === expected.contents)

  test("number expressions") {
    assertEquals(formatter.format(Number(3)), elem("3"))
    assertEquals(formatter.format(Number(3.0)), elem("3"))
    assertEquals(formatter.format(Number(22.763)), elem("22.763"))
  }

  test("variable expressions") {
    assertEquals(formatter.format(Var("bob")), elem("bob"))
  }

  test("unary operation") {
    assertEquals(formatter.format(UnOp("-", Number(2.64))), elem("-2.64"))
    assertEquals(formatter.format(UnOp("^", Var("x"))), elem("^x"))
  }

  test("binary operation except division") {
    assertEquals(formatter.format(BinOp("+", Var("x"), Number(3.0))), elem("x + 3"))

    val mixOp = BinOp("+", Var("x"), BinOp("*", Var("y"), Number(3.14)))
    assertEquals(formatter.format(mixOp), elem("x + y * 3.14"))

    val parens = BinOp("*", Var("x"), BinOp("+", Var("y"), Number(3.14)))
    assertEquals(formatter.format(parens), elem("x * (y + 3.14)"))

    val moreParens = BinOp("*",
      BinOp("-", Var("x"), BinOp("+", Var("y"), Var("z"))),
      BinOp("+", Number(3), Var("x"))
    )
    assertEquals(formatter.format(moreParens), elem("(x - y + z) * (3 + x)"))

    val yetMoreParens = BinOp("*",
      BinOp("+", Var("x"), Var("y")),
      BinOp("*", Number(3), Number(5))
    )
    assertEquals(formatter.format(yetMoreParens), elem("(x + y) * 3 * 5"))
  }

  test("division") {
    assertEquals(formatter.format(BinOp("/", Var("x"), Var("y"))),
      elem(Array("x", "-", "y"))
    )
    assertEquals(formatter.format(BinOp("/", Var("x"), Number(3.14))),
      elem(Array(" x  ", "----", "3.14"))
    )
    val complexFraction = BinOp("/",
      BinOp("/", Var("x"), Number(3.14)),
      BinOp("+",
        BinOp("/", Number(3), Var("bob")),
        BinOp("/", Var("fred"), Var("bob")))
    )
    assertEquals(formatter.format(complexFraction),
      elem(Array("    x     ", "   ----   ", "   3.14   ", "----------", " 3    fred", "--- + ----", "bob   bob "))
    )
    println(formatter.format(complexFraction).toString)
  }
}
