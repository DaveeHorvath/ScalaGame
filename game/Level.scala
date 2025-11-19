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

  /* Special subclass for level 1: Glass Ball of Wisdom. The level is essentially a riddle. The player is asked three riddles,
     and if they collect the correct answer, they are rewarded with obtaining an additional skill [Look: World]. */
class RiddlesLevel(name: String, description: String) extends Level(name, description):

    /* variable lists riddles available in the chapter and their answers in the form of Map */
  private val riddlesAndAnswers = Map[String, String]()
    /*
    "What walks on four legs in the morning, two legs in the afternoon, and three legs in the evening?"                                                                                                      -> "man",
    "I am built without bricks, wood, or stone, and offer a refuge when you feel alone. I can be empty, full of sorrow, or full of light. I grow bigger as you get older, and you only own one. What am I? " -> "memory",
    "I am weightless and can be seen, but you can only truly feel me when I'm gone. I am the great equalizer, yet no one can own me. I steal your moments, but give you experience in return. What am I? "   -> "time",
    "I have no voice, but I can imitate yours perfectly. I follow you in the day, yet vanish when the sun sets completely. I am your constant companion, yet hold no substance. What am I? "                 -> "shadow",
    "I travel without a single step and create pictures you cannot see. I have no hands, but I can break a house into dust. I am feared, but I am necessary. Who am I? "                                     -> "wind",
    "I am always hungry, I must always be fed. The finger I lick will soon turn red. I start small, but I can consume the entire world. Who am I? "                                                          -> "fire"
    ) */
  def addRiddle(riddle: String, answer: String) = this.riddlesAndAnswers + (riddle -> answer)

    /* Method checking if the player correctly guessed the answer. The method checks if the chosen riddle matches the answer given by the player. */
  def riddleIsGuessed(riddle: String, answer: String) = this.riddlesAndAnswers(riddle) == answer.toLowerCase

    /* Method checking if the player won the riddle challenge. The challenge is won if the player answers correctly three executive riddles. */
  def isWon =
    val threeRiddles = scala.util.Random.shuffle(riddlesAndAnswers).take(3)
    threeRiddles.forall(riddleIsGuessed)

end RiddlesLevel

class FightSceane(name: String, description: String) extends Level(name, description):
  ???