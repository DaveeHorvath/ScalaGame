package o1.gameDraft.Enemies

import o1.gameDraft.PlayerObject

class SoupMan(renderable   : String,             // the form that is displayed to the World
                interactable : Boolean,            //returns if the objects can be interacted with
                 worldID     : Int,                // the ID of the world the object is located in 
                 name        : String,             // the name of the object
                 description : String,             // the description of the object
                 initialHealthLevel : Int,         // the initial health level of the enemy, aka how hard you have to hit it to kill it
                 demage      : Option[Int])        //damage caused by the enemy's attack if any
  extends Enemy( "XXX", interactable, worldID, "The Soup Man", "XXX", 20, None):

  private val soupsPrice = 10
  private val soupsReducedPrice = soupsPrice / 2
  private val bribeInCookies = 3
  
  override def interract(player: PlayerObject): String = 
    s"Welcome! I have a delicious soup. It costs ${this.soupsPrice}. Do you want to buy it?\n What would you like to do?" +
        s"\n 1. Buy soup for ${this.soupsPrice} coins." +
        s"\n 2. Negotiate price." +
        s"\n 3. Bribe with ${this.bribeInCookies} cookies." +
        s"\n 4. Attack Soup Man." +
        s"\n 5. Leave." +
        s"\n Please, choose a number 1-5."

end SoupMan
