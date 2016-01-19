package org.stairwaybook.currencies

object Main extends App {
  val yenFrom100Dollars = Japan.Yen from US.Dollar * 100
  println(yenFrom100Dollars)

  val eurosFromYen = Europe.Euro from yenFrom100Dollars
  println(eurosFromYen)

  val dollarsFromEuros = US.Dollar from eurosFromYen
  println(dollarsFromEuros)
}
