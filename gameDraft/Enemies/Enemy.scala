package o1.gameDraft.Enemies

import o1.gameDraft.{GameObjects, PlayerObject, Skill}

class Enemy (renderable  : String,             // the form that is displayed to the World
             interactable: Boolean,            //returns if the objects can be interacted with
             worldID     : Int,                // the ID of the world the object is located in 
             name        : String,             // the name of the object
             description : String,             // the description of the object
             initialHealthLevel : Int,         // the initial health level of the enemy, aka how hard you have to hit it to kill it
             demage      : Option[Int])        //damage caused by the enemy's attack if any
  extends GameObjects(renderable, interactable, worldID, name, description):
  
  var posX: Int = 0            // the position of the object on the x-axis
  var posY: Int = 0            // the position of the object on the y-axis
  var pos: (Int, Int) = (0, 0) // the position of the object in two dimensions
  
  private var healthLevel = this.initialHealthLevel
  
  def howHealthy = this.healthLevel
  
  def howMuchDemage = this.demage

  def attack(player: PlayerObject) = player.changeHealth(-this.demage.getOrElse(0))
  
  def takeDemmage(attack: Skill) =
    attack.demage match {
      case Some(x) => this.healthLevel -= x
      case None    => this.healthLevel
    }

  override def interract(player: PlayerObject): String =
    s"You fight with ${this.name}."
  
end Enemy

    