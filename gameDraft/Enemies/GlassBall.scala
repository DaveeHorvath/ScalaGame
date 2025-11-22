package o1.gameDraft.Enemies

import o1.gameDraft.Enemies.Enemy
import o1.gameDraft.{PlayerObject, Skill}

import scala.collection.mutable.Map

class GlassBall(renderable   : String,             // the form that is displayed to the World
                interactable : Boolean,            //returns if the objects can be interacted with
                 worldID     : Int,                // the ID of the world the object is located in 
                 name        : String,             // the name of the object
                 description : String,             // the description of the object
                 initialHealthLevel : Int,         // the initial health level of the enemy, aka how hard you have to hit it to kill it
                 demage      : Option[Int])        //damage caused by the enemy's attack if any
  extends Enemy( "XXX", interactable, worldID, "Glass Ball of Wisdom", "XXX", 20, None):

  /* variable lists all available in the chapter riddles and their answers in the form of a Map */
  private val riddlesAndAnswers = Map[String, String]()
  /* variable lists three active riddles randomly chosen from the riddlesAndAnswers Vector */
  private var chosenRiddle = Vector[(String, String)]()
  /* stores how many of the answers given by the player in one attempt are correct. */
  private var correctAnswers = 0
  /* stores how many attempts the player has left. When available attempts are equal to 0 and the level is still not completed, the player loses the possibility of completing the level. */
  private var availableAttempts = 3
  
  private var isInterractable = this.interactable
  
  /* adds new riddle and answer to it to the map of riddles. */
  def addRiddle(riddle: String, answer: String) = this.riddlesAndAnswers += (riddle -> answer)

  def askRiddle =
    if this.isInterractable && this.availableAttempts > 0 then
      this.chosenRiddle = scala.util.Random.shuffle(this.riddlesAndAnswers.toVector).take(1)
      val riddleAndAnswer = this.chosenRiddle(0)
      s"The Glass Ball of Wisdom wants to know:\n ${this.chosenRiddle(0)}"
    else if !this.isInterractable && this.availableAttempts > 0 then
      s"You are already done. Why do you want to go through this hell again? No no no. You are done here."
    else
      s"Sorry, loser. You have used all 3/3 attempts. The Glass Ball of Wisdom was so done with you that it shattered into a million pieces. Better luck next time."

  def answerIsCorrect(playersAnswer: String, gainedSkill: Skill, player: PlayerObject) =
    val riddleAndAnswer = this.chosenRiddle(0)
    if riddleAndAnswer(1) == playersAnswer.toLowerCase then
      this.correctAnswers += 1
      if this.correctAnswers == 3 then
        this.whenWon(gainedSkill, player)
      else
        this.askRiddle
    else
      this.availableAttempts -= 1
      this.correctAnswers = 0
      s"Wrong answer. You have to try again. You have ${this.availableAttempts} available attempts."

  /* Method checking if the player won the riddle challenge. The challenge is won if the player answers correctly three executive riddles. */
  private def whenWon(gainedSkill: Skill, player: PlayerObject) =
    if this.correctAnswers == 3 then
      this.isInterractable = false
      player.changeHappines(0.10)
      player.addSkill(gainedSkill)

  
  override def interract(player: PlayerObject): String = this.askRiddle
    
end GlassBall
