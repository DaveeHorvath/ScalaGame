package o1.game

import scala.collection.mutable.{ArrayStack, Map}

/* Each Level represents different areas player can find in the game.

   All levels have
   @param name        - level's name
   @param description - level's description */

class Level(name: String, description: String):

  /* Lists all the skills that the player can collect in this level in the form of a map, where the key is the name of the skill. For some levels 
     skills is going to be empty */
  private val skills      :Map[String, Skill]     = Map[String, Skill]()
  /* Lists all the objects that the player can collect in this level in the form of a map, where the key is the name of the object. for some levels 
     objects is going to be empty */
  private val objects     :Map[String, Object]    = Map[String, Object]()
  /* Lists all the characters that the player can interact with in this level in the form of a map, where the key is the name of the character.
     For some levels, objects is going to be empty */
  private val characters  :Map[String, Character] = Map[String, Character]()
  /* Lists all the neighbouring levels, where the key is a direction, fx. north, east, etc. */
  private val neighbours  :Map[String, Level]     = Map[String, Level]()
  /* Variable stating if the level has been completed. In order to complete the game, all levels have to be marked as completed. */
  private var isCompleted :Boolean = false

  def complete() = this.isCompleted = true

  /*              METHODS ABOUT NEIGHTBOUR                 */

  /* Creates a reference from the key value, that is, direction fx. north, east, etc., and the level to which it's pointing. */
  def setNeighbor(direction: String, neighbor: Level) =
    this.neighbours += direction -> neighbor
  /* Adds all the directions in the key, pointer form, to the private var neighbours. */
  def setNeighbors(exits: Vector[(String, Level)]) =
    this.neighbours ++= exits
   /* returns the neighbor in the given direction. */
  def neighbor(direction: String) = this.neighbours.get(direction)

  /*              METHODS ABOUT COMPLETING                 */

  /* Returns whether the level is completed. */
  def completionState = this.isCompleted
  /* completes this level. */
  def completeLevel() = this.isCompleted = true

  /* SUBCLASSES FOR DIFFERENT LEVELS */

/* Special subclass for level 1: Glass Ball of Wisdom. The level is essentially a riddle. The player is asked three riddles,
   and if they collect the correct answer, they are rewarded with obtaining an additional skill [Look: World]. */
class RiddlesLevel(name: String, description: String, player: PlayerObject) extends Level(name, description):

  /* variable lists all available in the chapter riddles and their answers in the form of a Map */
  private val riddlesAndAnswers = Map[String, String]()
  /* variable lists three active riddles randomly chosen from the riddlesAndAnswers Vector */
  private var chosenRiddle = Vector[(String, String)]()
  /* stores how many of the answers given by the player in one attempt are correct. */
  private var correctAnswers = 0
  /* stores how many attempts the player has left. When available attempts are equal to 0 and the level is still not completed, the player loses the possibility of completing the level. */
  private var availableAttempts = 3

// FINNISH IMPLEMENTATION!!!!!!!
  def askRiddle =
    if !this.completionState && this.availableAttempts > 0 then
      this.chosenRiddle = scala.util.Random.shuffle(this.riddlesAndAnswers.toVector).take(1)
      val riddleAndAnswer = this.chosenRiddle(0)
      s"The Glass Ball of Wisdom wants to know:\n ${this.chosenRiddle(0)}"
    else if this.completionState then
      s"You are already done. Why do you want to go through this hell again? No no no. You are done here."
    else
      s"Sorry, loser. You have used all 3/3 attempts. The Glass Ball of Wisdom was so done with you that it shattered into a million pieces. Better luck next time."

  def isCorrect(playersAnswer: String, gainedSkill: Skill, telDestination: Level) =
    val riddleAndAnswer = this.chosenRiddle(0)
    if riddleAndAnswer(1) == playersAnswer.toLowerCase then
      this.correctAnswers += 1
      if this.correctAnswers == 3 then
        this.whenWon(gainedSkill, telDestination)
      else
        this.askRiddle
    else
      this.availableAttempts -= 1
      this.correctAnswers = 0
      s"Wrong answer. You have to try again. You have ${this.availableAttempts} available attempts."

  /* adds new riddle and answer to it to the map of riddles. */
  def addRiddle(riddle: String, answer: String) = this.riddlesAndAnswers += (riddle -> answer)

  /* Method checking if the player won the riddle challenge. The challenge is won if the player answers correctly three executive riddles. */
  private def whenWon(gainedSkill: Skill, telDestination: Level) =
    if this.correctAnswers == 3 then
      this.completeLevel()
      this.player.changeHappines(0.10)
      this.player.teleport(telDestination)
      this.player.addSkill(gainedSkill)

end RiddlesLevel

class FightSceane(name: String, description: String) extends Level(name, description)