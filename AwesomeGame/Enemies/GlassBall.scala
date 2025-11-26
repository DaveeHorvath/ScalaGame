package o1.AwesomeGame.Enemies

import o1.AwesomeGame.Enemies.Enemy
import o1.AwesomeGame.{GameObjects, PlayerObject, Skill, World}

import scala.collection.mutable.Map

class GlassBall(val GlassWorld: World)
  extends Enemy(GlassWorld, "glass ball of wisdom", "The wisest object in the world. No one is able to answer the ball's riddles.", 20):

  /* variable lists all available in the chapter riddles and their answers in the form of a Map */
  private val riddlesAndAnswers = Map[String, String]()
  /* variable lists three active riddles randomly chosen from the riddlesAndAnswers Vector */
  private var chosenRiddle = Vector[(String, String)]()
  /* stores how many of the answers given by the player in one attempt are correct. */
  private var correctAnswers = 0
  /* stores how many attempts the player has left. When available attempts are equal to 0 and the level is still not completed, the player loses the possibility of completing the level. */
  private var availableAttempts = 3

  private var isInterractable = true
  
  /* adds new riddle and answer to it to the map of riddles. */
  def addRiddle(riddle: String, answer: String) = this.riddlesAndAnswers += (riddle -> answer)

  def askRiddle =
    if this.isInterractable && this.availableAttempts > 0 then
      this.chosenRiddle = scala.util.Random.shuffle(this.riddlesAndAnswers.toVector).take(1)
      val (r, a) = this.chosenRiddle(0)
      s"Welcome traveler! I see that you decided to get off the path and wonder around. You are not very busy of a person, aren't you? Anyway! I have a question for you!" +
      s"\nThe Glass Ball of Wisdom (aka me. HAhaaha) wants to know:\n ${r}"
    else if !this.isInterractable && this.availableAttempts > 0 then
      s"You are already done. Why do you want to go through this hell again? No no no. You are done here."
    else
      s"Sorry, loser. You have used all 3/3 attempts. The Glass Ball of Wisdom was so done with you that it shattered into a million pieces. Better luck next time."

  def answerIsCorrect(playersAnswer: String, gainedSkill: Skill, player: PlayerObject): String =
    val riddleAndAnswer = this.chosenRiddle(0)
    if riddleAndAnswer._2 == playersAnswer.toLowerCase then
      this.correctAnswers += 1
      if this.correctAnswers == 3 then
        this.whenWon(gainedSkill, player)
      else
        s" Correct!\n ${this.askRiddle}"
    else
      this.availableAttempts -= 1
      this.correctAnswers = 0
      s"Wrong answer. You have to try again. You have ${this.availableAttempts} available attempts."

  /* Method checking if the player won the riddle challenge. The challenge is won if the player answers correctly three executive riddles. */
  private def whenWon(gainedSkill: Skill, player: PlayerObject) =
      this.isInterractable = false
      player.addMoney(10)
      player.changeHappines(0.10)
      s"You are a winner!"
  
  override def interract(player: PlayerObject): String = this.askRiddle

  override def talkTo(player: PlayerObject, cont: String): String = answerIsCorrect(cont, new Skill("looking pretty",":)",None), player)
end GlassBall
