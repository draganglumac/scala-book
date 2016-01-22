package org.stairwaybook

import org.stairwaybook.collections._
import org.stairwaybook.patricia._

object Main {

  def rnaMain(): Unit = {
    val rna = RNA.fromSeq(List(A, G, T, A))
    val rna1 = RNA(A, U, G, G, T)

    println("rna.length = " + rna.length)
    println("rna1.length = " + rna1.length)
    println("rna1.last = " + rna1.last)
    println("rna1.take(3) = " + rna1.take(3))

    val rna2 = RNA(A, U, G, G, T)
    val map2 = rna2 map {
      case A => T
      case b => b
    }
    println("map2 = " + map2)
    println("map2 ++ map2 = " + (map2 ++ map2))
  }

  def patriciaMain(): Unit = {
    val m: PrefixMap[Int] = PrefixMap("abc" -> 0, "abd" -> 1, "al" -> 2, "all" -> 3, "xy" -> 4)
    println("m get \"a\" = " + (m get "a"))
    println("m withPrefix \"a\" = " + (m withPrefix "a"))
  }

  def main(args: Array[String]): Unit = {
    rnaMain()
    patriciaMain()
  }
}
