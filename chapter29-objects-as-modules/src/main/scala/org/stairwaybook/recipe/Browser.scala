package org.stairwaybook.recipe

abstract class Browser {

  val database: Database

  def recipesUsing(food: Food): List[Recipe] =
    database.allRecipes.filter(_.ingredients.contains(food))

  def displayCategory(category: database.FoodCategory): Unit = {
    println(category)
  }

}