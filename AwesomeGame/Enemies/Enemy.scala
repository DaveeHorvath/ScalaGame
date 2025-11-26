package o1.AwesomeGame.Enemies

import o1.AwesomeGame.{GameObjects, PlayerObject, Skill, World}
  
class Enemy(val world: World, val name: String, val description: String, val initialHealthLevel: Int):
  
  private var healthLevel = this.initialHealthLevel
  private var canBeInteracted = true
  
  def howHealthy = this.healthLevel
  
  def takeDemmage(attack: Skill) =
    this.healthLevel = this.healthLevel - attack.use

  def isDead = this.healthLevel <= 0
  
  def die() =
    if this.isDead then
      this.canBeInteracted = false
    else
      this.canBeInteracted = true

  override def toString: String =
    this.name + ": " + this.description

  def interract(player: PlayerObject): String =
    s"You fight with ${this.name}."

  def talkTo(player: PlayerObject, cont: String) =
    "growls back at you"
  
end Enemy

