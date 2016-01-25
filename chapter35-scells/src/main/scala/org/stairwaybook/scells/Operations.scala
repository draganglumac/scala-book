package org.stairwaybook.scells

object Operations {

  trait Arithmetic {
    this: Evaluator =>

    operations +=(
      "add" -> { case List(x, y) => x + y },
      "sub" -> { case List(x, y) => x - y },
      "div" -> { case List(x, y) => x / y },
      "mul" -> { case List(x, y) => x * y },
      "mod" -> { case List(x, y) => x % y },
      "sum" -> { case xs => (0.0 /: xs) (_ + _) },
      "prod" -> { case xs => (1.0 /: xs) (_ * _) }
      )

  }

}