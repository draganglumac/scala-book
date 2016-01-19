package org.stairwaybook.funcqueue

class Fodder

abstract class Animal {

  type SuitableFodder <: Fodder

  def eat(food: SuitableFodder)

}

class Grass extends Fodder

class Cow extends Animal {

  type SuitableFodder = Grass

  override def eat(food: Grass) {}

}

class DogFood extends Fodder

class Dog extends Animal {

  type SuitableFodder = DogFood

  override def eat(food: DogFood) {}

}

// Structural subtyping

class Pasture {

  var animals: List[Animal {type SuitableFood = Grass}] = Nil

}

class LoanPattern {

  def using[T <: {def close() : Unit}, S](obj: T)(operation: T => S) = {
    val result = operation(obj)
    obj.close()
    result
  }
}

// Enumerations

object Colour extends Enumeration {
  val Red = Value
  val Green = Value
  val Blue = Value
}