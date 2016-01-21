package org.stairwaybook.patricia

import collection._

class PrefixMap[T] extends mutable.Map[String, T]
  with mutable.MapLike[String, T, PrefixMap[T]] {

}
