package o1.AwesomeGame

import scala.collection.mutable.Map

class Item(val world: World, val name: String, val description: String):

  override def toString: String =
    s"${this.name}: ${this.description}"

end Item

class Soup(val soupWorld: World) extends Item(soupWorld, "soup", "The best soup in the town"):
  
  private val additionalHappiness = 0.25

  if this.world.player.location == this.world.destination then
    this.world.player.changeHappines(this.additionalHappiness)
  else
    this.world.player.changeHappines(0)
  
end Soup
  