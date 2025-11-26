package o1.AwesomeGame

import o1.AwesomeGame.Enemies.Enemy

class Skill(val name       : String,       // the name of the skill
            val description: String,       // the description of the skill
            val demage     : Option[Int]): // the demage caused by the skill, if any

  override def toString: String = 
    s"${this.name}: ${this.description}"
  
  def use = this.demage.getOrElse(0)

end Skill

