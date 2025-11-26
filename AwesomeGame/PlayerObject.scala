package o1.AwesomeGame

import scala.collection.mutable.Map

class PlayerObject(world: World, initialPosition: Area):

  private var quitCommandGiven = false              // one-way flag
  def hasQuit = this.quitCommandGiven

  private var currentLocation = this.initialPosition
  // ITEMS COLLECTED BY THE PLAYER WITH A GIVEN NAME
  private val inventory = Map[String, Item]()
  // AMOUNT OF MONEY CHARACTER HAS. INITIALLY SET TO 0 (WE ARE POOR, OK?)
  private var money = 0.0
  /* PLAYER'S HAPPINESS LEVEL. THE NUMBER OSCILATES BETWEEN 0.0 AND 1.0.
     X < 0.5 - PLAYER IS UNHAPPY. IF THE PLAYER'S HAPPINESS LEVEL IS BELOW HALF WHEN THE PLAYER REACHES THE HOUSE, THE GAME IS LOST.
     X = 5.0 - PLAYER IS ON AVERAGE HAPPY. IF YOU REACH THE HOUSE WITH A HAPPINESS LEVEL OF 0.5, YOU GET A SPECIAL MESSAGE OF 'THE CHARACTER IS STARING AT THE WALL'.
     X > 0.5 - PLAYER IS HAPPY. YOU WIN, CONGRATS.
    INITIALLY THE PLAYER IS AVERAGE HAPPY. */
  private var happinesLevel: Double = 0.2
  /* PLAYER'S HEALTH LEVEL. WE START THE GAME WITH AN INITIAL (AND MAXIMUM) HEAL LEVEL OF 42.
     HEALTH LEVEL DECREASES WHEN THE CHARACTER TAKES DAMAGE.
     HEALTH LEVEL CAN BE REGAINED IF WE USE SPECIAL OBJECTS FX. COOKIES. HOWEVER, HEALTH LEVEL CAN NEVER EXCEED 42. */
  private var healthLevel: Int = 42
  
  
  /*                    HAPPINESS LEVEL FUNCTIONS                           */

  def quit() =
    quitCommandGiven = true
    "You decide to just give up, lie down on the ground, and ignore all future instructions"

  /* returns the happiness level of the player*/ 
  def howHappy = this.happinesLevel
  def getHappiness():String =
    val intValue = (howHappy * 10).toInt
    s"Happiness Level: ${intValue}/10 [${"#" * intValue}${" " * (10 - intValue)}]"
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

  def location = this.currentLocation

  def go(direction: String) =
    val destination = this.location.neighbor(direction)
    this.currentLocation = destination.getOrElse(this.currentLocation)
    if destination.isDefined then s"You go $direction." else s"You can't go $direction."


  /*                    OBJECT FUNCTIONS                           */

  /* adds new item to the list of items the player has */
  def addItem(item: Item) = this.inventory += (item.name -> item)
  def examine(itemName: String): String =
    this.inventory.get(itemName) match {
        case Some(i) =>
          i.description
        case None =>
          s"Are you sure that ${itemName} actually exists? You definitely don't have it."
    }

  def removeItem(itemName: String): String =
    this.inventory.get(itemName) match {
        case Some(i) =>
          this.inventory.remove(itemName)
          s"You drop the $itemName."
        case None =>
          "You don't have that!"
    }
  // Determines whether the player is carrying an item of the given name.
  def hasItem(itemName: String): Boolean =
    this.inventory.contains(itemName)
  
  def whatInInventory = this.inventory
    
  def getInventoryString =
    if this.inventory.keys.toList.length == 0 then
      "Your bag of bags is empty, get your stuff together!"
    else
      this.inventory.keys.mkString(", ")
  // talk_to grandma
  // talk_to grandma: I want cookies!
  def interact(name: String) =
    val n = name.split(": ").head
    val in = name.split(": ").drop(1).mkString
    if in.length > 0 then
      this.currentLocation.getEnemyWithName(n) match {
        case None => s"Did you halucinate talking to ${n}?"
        case Some(enemy) => enemy.talkTo(this, in)
      }
    else
      this.currentLocation.getEnemyWithName(n) match {
        case None => s"Did you halucinate seeing ${n}? Are you sure you aren't going insane?"
        case Some(enemy) => enemy.interract(this)
      }
  
  def get(itemName: String): String =
    this.currentLocation.removeItem(itemName) match
      case Some(i) =>
        this.inventory += itemName -> i
        s"You pick up the $itemName."
      case None    =>
        s"There is no $itemName here to pick up."
  


  def getHelpString: String = "no help for strong people"

end PlayerObject
