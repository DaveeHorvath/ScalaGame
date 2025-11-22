package o1.gameDraft

import scala.collection.mutable.Map

class PlayerObject(renderable     : String,       // the form that is displayed to the World
                   worldID        : Int,          // the ID of the world the player is located in 
                   initialPosition: (Int, Int) ): // initial position of the player

  /*                    VARIABLES STORED IN PLAYER (INSIDES)                           */
  private var posX: Int = 0
  private var posY: Int = 0
  private var pos : (Int, Int) = initialPosition

  // THE DIRECTION AT WHICH THE CHARACTER IS FACING
  private var currentFacing = ???
  // SKILLS OBTAINED BY THE PLAYER WITH A GIVEN NAME
  private val skills = Map[String, Skill]()
  // ITEMS COLLECTED BY THE PLAYER WITH A GIVEN NAME
  private val inventory = Map[String, Item]()
  // AMOUNT OF MONEY CHARACTER HAS. INITIALLY SET TO 0 (WE ARE POOR, OK?)
  private var money = 0.0
  /* PLAYER'S HAPPINESS LEVEL. THE NUMBER OSCILATES BETWEEN 0.0 AND 1.0.
     X < 0.5 - PLAYER IS UNHAPPY. IF THE PLAYER'S HAPPINESS LEVEL IS BELOW HALF WHEN THE PLAYER REACHES THE HOUSE, THE GAME IS LOST.
     X = 5.0 - PLAYER IS ON AVERAGE HAPPY. IF YOU REACH THE HOUSE WITH A HAPPINESS LEVEL OF 0.5, YOU GET A SPECIAL MESSAGE OF 'THE CHARACTER IS STARING AT THE WALL'.
     X > 0.5 - PLAYER IS HAPPY. YOU WIN, CONGRATS.
    INITIALLY THE PLAYER IS AVERAGE HAPPY. */
  private var happinesLevel: Double = 0.5
  /* PLAYER'S HEALTH LEVEL. WE START THE GAME WITH AN INITIAL (AND MAXIMUM) HEAL LEVEL OF 42.
     HEALTH LEVEL DECREASES WHEN THE CHARACTER TAKES DAMAGE.
     HEALTH LEVEL CAN BE REGAINED IF WE USE SPECIAL OBJECTS FX. COOKIES. HOWEVER, HEALTH LEVEL CAN NEVER EXCEED 42. */
  private var healthLevel: Int = 42
  
  
  /*                    HAPPINESS LEVEL FUNCTIONS                           */

  /* returns the happiness level of the player*/ 
  def howHappy = this.happinesLevel
  /* changes the happiness level of the player by the given amount. If x is negative, then the happiness level decreases.
     Happiness level can be a minimum of 0.0 and max 1.0. If the player achieved happiness level 0.0 and gains negative happiness, the happiness level doesn't change.
     Similarly, if the player reached happiness level 1.0 and gains positive happiness, the happiness level won't change. */
  def changeHappines(x: Double) =
    val potentialHappiness = (this.happinesLevel + x)
    if potentialHappiness > 1.0 then
      this.happinesLevel = 1.0
    if potentialHappiness < 0.0 then
      this.happinesLevel = 0.0
    else
      this.happinesLevel = potentialHappiness


  /*                    HEALTH LEVEL FUNCTIONS                           */

  /* returns the health level of the player*/
  def howHealthy = this.healthLevel
  /* changes the health level of the player by the given amount. If x is negative, then the health level decreases.
     Health level can be a maximum of 100. There is no minimum; however, if the health level is smaller than or equal to 0, the game is lost.
     If the player reached health level 100 and gains positive health, the health level won't change. */
  def changeHealth(x: Int) =
    val potentialHealth = (this.healthLevel + x)
    if potentialHealth > 100 then
      this.healthLevel = 100
    else
      this.healthLevel = potentialHealth


  /*                    POSITION FUNCTIONS                           */




  /*                    OBJECT FUNCTIONS                           */

  /* adds new skill to the list of skills the player has */
  def interractWith(gameObjects: GameObjects) = gameObjects.interract(this)
  
  /* adds new item to the list of items the player has */
  def addItem(item: Item) = this.inventory += (item.name -> item)

  def addSkill(skill: Skill) = this.skills += (skill.name -> skill)

end PlayerObject
