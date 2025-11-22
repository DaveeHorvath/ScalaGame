package o1.gameDraft

import o1.gameDraft.Enemies.Enemy

class Skill(val name       : String,       // the name of the skill
            val description: String,       // the description of the skill
            val demage     : Option[Int]): // the demage caused by the skill, if any

  override def toString: String = 
    s"${this.name}: ${this.description}"
  
  def use(enemy: Enemy): String =
    enemy.takeDemmage(this)
    s"You ${this.name} ${enemy.name}. New stats:\n ${enemy.name}'s health: ${enemy.howHealthy}\n demage: ${enemy.howMuchDemage}."

