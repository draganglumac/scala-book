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