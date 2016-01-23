package org.stairwaybook.extractors

// Extractor with two or more variables
object EMail extends ((String, String) => String) {
  // The injection method (optional)
  def apply(user: String, domain: String) = user + "@" + domain

  // The extraction method (mandatory)
  def unapply(str: String): Option[(String, String)] = {
    val parts = str split "@"
    if (parts.length == 2) Some(parts(0), parts(1)) else None
  }
}

// Extractor with one variable
object Twice {
  def apply(s: String): String = s + s

  def unapply(s: String): Option[String] = {
    val length = s.length / 2
    val half = s.substring(0, length)
    if (half == s.substring(length)) Some(half) else None
  }
}

// Extractor with no variables
object UpperCase {
  def unapply(s: String): Boolean = s.toUpperCase == s
}

// Extractor for vararg matching
object Domain {
  // The injection method (optional)
  def apply(parts: String*): String =
    parts.reverse.mkString(".")

  // The extraction method (mandatory)
  def unapplySeq(whole: String): Option[Seq[String]] =
    Some(whole.split("\\.").reverse)
}

object ExpandedEmail {

  def unapplySeq(email: String): Option[(String, Seq[String])] = {
    val parts = email split "@"
    if (parts.length == 2)
      Some(parts(0), parts(1).split("\\.").reverse.toSeq)
    else
      None
  }

}
