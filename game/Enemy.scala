package o1.game

import scala.collection.mutable.Map

class Enemy(var r  : String,
            var i  : Boolean,
            val w       : World,
            val name        : String,
            val description : String,
            val healthPoints: Int,
            val demage      : Int) extends GameObjects(r, i, w):

  var posX = 0
  var posY = 0

  override def width: Int => 0 = ???
  override def height: Int => 0 = ???

  override def interract(playerObject: PlayerObject): String = "Hello"

class RiddleMaster(var masterR  : String,
                   var masterI  : Boolean,
                   val masterW       : World,
                   val masterName        : String,
                   val masterDescription : String) extends Enemy(masterR, masterI, masterW, masterName, masterDescription, 25, 0):

  /* variable lists all available in the chapter riddles and their answers in the form of a Map */
  private val riddlesAndAnswers = Map[String, String]()
  /* variable lists three active riddles randomly chosen from the riddlesAndAnswers Vector */
  private var chosenRiddle = Vector[(String, String)]()
  /* stores how many of the answers given by the player in one attempt are correct. */
  private var correctAnswers = 0
  /* stores how many attempts the player has left. When available attempts are equal to 0 and the level is still not completed, the player loses the possibility of completing the level. */
  private var availableAttempts = 3

  /* adds new riddle and answer to it to the map of riddles. */
  def addRiddle(riddle: String, answer: String) = this.riddlesAndAnswers += (riddle -> answer)

  def askRiddle =
    if this.masterI && this.availableAttempts > 0 then
      this.chosenRiddle = scala.util.Random.shuffle(this.riddlesAndAnswers.toVector).take(1)
      val riddleAndAnswer = this.chosenRiddle(0)
      s"The Glass Ball of Wisdom wants to know:\n ${this.chosenRiddle(0)}"
    else if !this.masterI && this.availableAttempts > 0 then
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
      this.masterI = false
      player.changeHappines(0.10)
      player.addSkill(gainedSkill)

  override def interract(playerObject: PlayerObject): String = this.askRiddle
