package org.stairwaybook.recipe

import org.scalatest.FunSuite

class RecipeTest extends FunSuite {

  import TestData._
  import TestDoubles._

  test("retreiving foods from database") {
    assert(Apple == SimpleDatabase.foodNamed("Apple").get)
    assert(Sugar == SimpleDatabase.foodNamed("Sugar").get)
    assert(None == SimpleDatabase.foodNamed("Potato"))
  }

  test("recipe browser") {
    assert("fruit salad" == SimpleBrowser.recipesUsing(Apple).head.name)
  }

}
