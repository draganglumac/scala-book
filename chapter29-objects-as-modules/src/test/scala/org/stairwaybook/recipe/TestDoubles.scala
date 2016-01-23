package org.stairwaybook.recipe

object TestDoubles {

  import TestData._

  object SimpleDatabase extends Database {

    def allFoods = List(Apple, Orange, Cream, Sugar)

    def allRecipes: List[Recipe] = List(FruitSalad)

    private var categories = List(
      FoodCategory("fruits", List(Apple, Orange)),
      FoodCategory("misc", List(Cream, Sugar))
    )

    def allCategories = categories
  }

  object SimpleBrowser extends Browser {

    val database = SimpleDatabase
  }

  object StudentDatabase extends Database {

    object FrozenFood extends Food("FrozenFood")

    object HeatItUp extends Recipe(
      "heat it up",
      List(FrozenFood),
      "Microwave the 'food' for 10 minutes."
    )

    def allFoods = List(FrozenFood)

    def allRecipes = List(HeatItUp)

    def allCategories = List(
      FoodCategory("edible", List(FrozenFood))
    )
  }

  object StudentBrowser extends Browser {
    val database = StudentDatabase
  }

}
