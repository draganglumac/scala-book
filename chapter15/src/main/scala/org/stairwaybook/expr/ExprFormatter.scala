package org.stairwaybook.expr

import org.stairwaybook.layout.Element
import org.stairwaybook.layout.Element.elem

sealed abstract class Expr

case class Var(name: String) extends Expr

case class Number(num: Double) extends Expr

case class UnOp(operator: String, arg: Expr) extends Expr

case class BinOp(operator: String, left: Expr, right: Expr) extends Expr

class ExprFormatter {
  // Contains operators in groups of increasing precedence
  private val opGroups =
    Array(
      Set("|", "||"),
      Set("&", "&&"),
      Set("^"),
      Set("==", "!="),
      Set("<", "<=", ">", ">="),
      Set("+", "-"),
      Set("*", "%")
    )

  private val precedence = {
    val assocs =
      for {
        i <- opGroups.indices
        op <- opGroups(i)
      } yield op -> i

    assocs.toMap
  }

  private val unaryPrecedence = opGroups.length
  private val fractionPrecedence = -1

  private def format(e: Expr, enclPrec: Int): Element =
    e match {
      case Var(name) =>
        elem(name)

      case Number(num) =>
        def stripDot(s: String) =
          if (s.endsWith(".0")) s.substring(0, s.length - 2)
          else s
        elem(stripDot(num.toString))

      case UnOp(op: String, arg: Expr) =>
        elem(op) beside format(arg, unaryPrecedence)

      case BinOp("/", left: Expr, right: Expr) =>
        val top = format(left, enclPrec)
        val bot = format(right, enclPrec)
        val line = elem('-', top.width max bot.width, 1)
        val fraction = top above line above bot
        if (enclPrec != fractionPrecedence) fraction
        else elem(" ") beside fraction beside elem(" ")

      case BinOp(op: String, left: Expr, right: Expr) =>
        val opPrec = precedence(op)
        val l = format(left, opPrec)
        val r = format(right, opPrec)
        val expr = l beside elem(" " + op + " ") beside r
        if (opPrec < enclPrec) elem("(") beside expr beside elem(")")
        else expr
    }

  def format(e: Expr): Element = format(e, 0)
}
