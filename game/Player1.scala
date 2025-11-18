package o1.game

import o1.game.Skill

import scala.collection.mutable.Map

class Player1(initialPosition: Level): // change int to something else

  // POSITION OF PLAYER'S FEET AT A CURRENT TIME
  private var currentPosition: Level = initialPosition
  // THE DIRECTION AT WHICH THE CHARACTER IS FACING
  private var currentFacing = ???
  // SKILLS OBTAINED BY THE PLAYER WITH A GIVEN NAME
  private var skills = Map[String, Skill]()
  // ITEMS COLLECTED BY THE PLAYER WITH A GIVEN NAME
  private var inventory = ???
  // AMOUNT OF MONEY CHARACTER HAS. INITIALLY SET TO 0 (WE ARE POOR, OK?)
  private var money = 0.0
  /* PLAYER'S HAPPINESS LEVEL. THE NUMBER OSCILATES BETWEEN 0.0 AND 1.0.
     X < 0.5 - PLAYER IS UNHAPPY. IF THE PLAYER'S HAPPINESS LEVEL IS BELOW HALF WHEN THE PLAYER REACHES THE HOUSE, THE GAME IS LOST.
     X = 5.0 - PLAYER IS ON AVERAGE HAPPY. IF YOU REACH THE HOUSE WITH A HAPPINESS LEVEL OF 0.5, YOU GET A SPECIAL MESSAGE OF 'THE CHARACTER IS STERING AT THE WALL'.
     X > 0.5 - PLAYER IS HAPPY. YOU WIN, CONGRATS.
    INITIALLY THE PLAYER IS AVERAGE HAPPY. */
  private var happinesLevel: Double = 0.5
  /* PLAYER'S HEALTH LEVEL. WE START THE GAME WITH AN INITIAL (AND MAXIMUM) HEAL LEVEL OF 42.
     HEALTH LEVEL DECREASES WHEN THE CHARACTER TAKES DAMAGE.
     HEALTH LEVEL CAN BE REGAINED IF WE USE SPECIAL OBJECTS FX. COOKIES. HOWEVER, HEALTH LEVEL CAN NEVER EXCEED 42. */
  private var healthLevel: Int = 42
  
  /* returns the happiness level of the player*/ 
  def howHappy = this.happinesLevel
  
  /* returns current position of a player */
  def position = this.currentPosition

end Player1

