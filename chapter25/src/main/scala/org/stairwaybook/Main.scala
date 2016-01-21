package org.stairwaybook

import org.stairwaybook.collections._

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

  def main(args: Array[String]): Unit = {
    rnaMain()
  }
}
