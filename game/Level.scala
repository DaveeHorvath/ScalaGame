package o1.game

import scala.collection.mutable.Map

/* Each Level represents different areas player can find in the game.

   All levels have
   @param name        - level's name
   @param description - level's description */

class Level(name: String, description: String):

  /* Lists all the skills that the player can collect in this level in the form of a map, where the key is the name of the skill. For some levels 
     skills is going to be empty */
  private val skills     :Map[String, Skill]     = Map[String, Skill]()
  /* Lists all the objects that the player can collect in this level in the form of a map, where the key is the name of the object. for some levels 
     objects is going to be empty */
  private val objects    :Map[String, Object]    = Map[String, Object]()
  /* Lists all the characters that the player can interact with in this level in the form of a map, where the key is the name of the character.
     For some levels, objects is going to be empty */
  private val characters :Map[String, Character] = Map[String, Character]()
  /* Lists all the neighbouring levels, where the key is a direction, fx. north, east, etc. */
  private val neighbours :Map[String, Level]     = Map[String, Level]()
  /* Variable stating if the level has been completed. In order to complete the game, all levels have to be marked as completed. */
  private var isCompleted: Boolean = false
  
  /* Creates a reference from the key value, that is, direction fx. north, east, etc., and the level to which it's pointing. */
  def setNeighbor(direction: String, neighbor: Level) =
    this.neighbours += direction -> neighbor
  /* Adds all the directions in the key, pointer form, to the private var neighbours. */
  def setNeighbors(exits: Vector[(String, Level)]) =
    this.neighbours ++= exits
   /* returns the neighbor in the given direction. */
  def neighbor(direction: String) = this.neighbours.get(direction)
  /* Returns whether the level is completed. */
  def completionState = this.isCompleted
  
  
  
  
  
  /* SUBCLASSES FOR DIFFERENT LEVELS */
  
  class Riddles(name: String, description: String) extends Level(name, description):
    ???
  class FightSceane(name: String, description: String) extends Level(name, description):
    ???