package org.stairwaybook

import extractors._

import scala.util.matching.Regex

object Main {

  def userTwiceUpper(s: String) = s match {
    case EMail(Twice(x@UpperCase()), domain) =>
      "match: " + x + " in domain " + domain
    case _ =>
      "no match"
  }

  def isTomInDotCom(s: String): Boolean = s match {
    case EMail("tom", Domain("com", _*)) => true
    case _ => false
  }

  def main(args: Array[String]): Unit = {
    println("email: " + EMail.unapply("Jonh@epfl.ch"))
    println("non-email: " + EMail.unapply("John Doe"))

    "bobbob" match {
      case Twice(s) => println(s + " extracted")
      case _ => println("no match baby!")
    }

    "BOB" match {
      case UpperCase() => println("It's upper case.")
      case _ => println("meh!")
    }

    println("\n" + userTwiceUpper("DIDI@hotmail.com"))
    println(userTwiceUpper("DIDO@hotmail.com"))
    println(userTwiceUpper("didi@hotmail.com"))

    println("\n" + "isTomInDotCom(\"tom@sun.com\") = " + isTomInDotCom("tom@sun.com"))
    println("isTomInDotCom(\"peter@sun.com\") = " + isTomInDotCom("peter@sun.com"))
    println("isTomInDotCom(\"tom@acm.org\") = " + isTomInDotCom("tom@acm.org"))

    val s = "tom@support.epfl.ch"
    val ExpandedEmail(name, topdom, subdoms@_*) = s
    println("name = " + name)
    println("topdom = " + topdom)
    println("subdoms = " + subdoms)

    //    val RegexDecimal = new Regex("""(-)?(\d+)(\.\d+)?""")
    val RegexDecimal =
      """(-)?(\d+)(\.\d+)?""".r
    val RegexDecimal(sign, integerpart, decimalpart) = (-1.23).toString
    println("\nsign = " + sign)
    println("integerpart = " + integerpart)
    println("decimalpart = " + decimalpart)

    val RegexDecimal(sign1, integerpart1, decimalpart1) = 1.0.toString
    println("\nsign = " + sign1)
    println("integerpart = " + integerpart1)
    println("decimalpart = " + decimalpart1 + "\n")

    val input = "-1.23 1.0 42 37.5 5"
    for (RegexDecimal(s, i, d) <- RegexDecimal findAllIn input)
      println("sign: " + s + ", integer: " + i + ", decimal: " + d)
  }

}
