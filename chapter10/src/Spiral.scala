import Element.elem

object Spiral {

  val space = elem(" ")
  val corner = elem("+")

  def spiral(nEdges: Int, direction: Int): Element = {
    if (nEdges == 1)
      corner
    else {
      val sp = spiral(nEdges - 1, (direction + 3) % 4)
      val verticalBar = elem('|', 1, sp.height)
      val horizontalBar = elem('-', sp.width, 1)
      direction match {
        case 0 =>
          (corner beside horizontalBar) above (sp beside space)
        case 1 =>
          (sp above space) beside (corner above verticalBar)
        case 2 =>
          (space beside sp) above (horizontalBar beside corner)
        case 3 =>
          (verticalBar above corner) beside (space above sp)
      }
    }
  }

  def main(args: Array[String]) {
    val nSides = args(0).toInt
    println(spiral(nSides, 0))
  }

}