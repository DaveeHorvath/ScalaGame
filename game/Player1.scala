package o1.game

import scala.collection.mutable.Map

class PlayerObject(initialLocation: Level, world: World) extends GameObjects( "XXX", false, world): // change int to something else

  /*                    VARIABLES STORED IN PLAYER (INSIDES)                           */

  var posX: Int = 0
  var posY: Int = 0

  override def width: Int => 0 = ???
  override def height: Int => 0 = ???

  // POSITION OF PLAYER'S FEET AT A CURRENT TIME
  private var currentLocation: Level = initialLocation
  // THE DIRECTION AT WHICH THE CHARACTER IS FACING
  private var currentFacing = ???
  // SKILLS OBTAINED BY THE PLAYER WITH A GIVEN NAME
  private var skills = Map[String, Skill]()
  // ITEMS COLLECTED BY THE PLAYER WITH A GIVEN NAME
  private var inventory = Map[String, Object]()
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
  def howHealthy = this.happinesLevel
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

  /* returns current position of a player */
  def position = this.currentLocation
  /* Method allowing the player to move from one location to the other. The player moves to the location specified by the direction, as assigned in the "World".
     If a location exists, the player's position is updated the communicate "You go ..." is displayed. If the location is invalid, the player states in the same position
     and communicates "You can't go ..." appreas */
  def go(direction: String) =
    val destination = this.currentLocation.neighbor(direction)
    this.currentLocation = destination.getOrElse(this.currentLocation)
    if destination.isDefined then s"You go $direction." else s"You can't go $direction."
  /* teleports the player from one level to the other after fx. Completing the level. */
  def teleport(destination: Level) =
    this.currentLocation = destination

  /*                    SKILLS FUNCTIONS                           */

  /* adds new skill to the list of skills the player has */
  def addSkill(skill: Skill) = this.skills += skill.skillName -> skill

