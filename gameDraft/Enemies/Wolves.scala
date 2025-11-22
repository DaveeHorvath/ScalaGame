package o1.gameDraft.Enemies

import o1.gameDraft.PlayerObject

class Wolves(  renderable   : String,             // the form that is displayed to the World
                interactable : Boolean,            //returns if the objects can be interacted with
                 worldID     : Int,                // the ID of the world the object is located in 
                 name        : String,             // the name of the object
                 description : String,             // the description of the object
                 initialHealthLevel : Int,         // the initial health level of the enemy, aka how hard you have to hit it to kill it
                 demage      : Option[Int])        //damage caused by the enemy's attack if any
  extends Enemy( "XXX", interactable, worldID, "Three little wolves", "XXX", 120, Option(10)):

  override def interract(player: PlayerObject): String =
    s"IMPLEMENTATION REQUIRED"

end Wolves

