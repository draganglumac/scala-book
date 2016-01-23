package org.stairwaybook.recipe

trait FoodCategory {

  case class FoodCategory(name: String, foods: List[Food]) extends FoodCategory

  def allCategories: List[FoodCategory]
}

abstract class Database extends FoodCategory {

  def allFoods: List[Food]

  def allRecipes: List[Recipe]

  def foodNamed(name: String) =
    allFoods.find(f => f.name == name)

}
