package o1.gameDraft

import scala.collection.mutable.Map

class Item(renderable  : String,        // the form that is displayed to the World
           worldID     : Int,           // the id of the world the object is located in 
           name        : String,        // the name of the object
           description : String,        // initial position of the item 
           val initialPos: (Int, Int))  // the description of the object
  extends GameObjects(renderable, true, worldID, name, description):

  var posX: Int = 0                // the position of the object on the x-axis
  var posY: Int = 0                // the position of the object on the y-axis
  var pos: (Int, Int) = initialPos // the position of the object in two dimensions

  // adds the item to player's inventory and returns the message.
  override def interract(player: PlayerObject): String = 
    player.addItem(this)
    s"You have gained ${this.name}."