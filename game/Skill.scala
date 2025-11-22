package o1.game

import scala.collection.mutable.Map

/* Skills player can obtain through the game. Currently, three skills are available:

   1. HIT  - Basic skill. We start our game with it. Takes away XXXX of the opponent's health.
   2. STAB - The skill player can gain when completing the optional Glass Ball level. Takes away XXXX of the opponent's health.
   3. HUG  - The skill player can gain when completing the Grandma's Kitchen level. Reduces the damage caused by the Sheep in
             level Wolf&Sheep Fight from 15 to 10 [LOOK CHARACTER->SHEEP]. 

   All skills have
   @param name        - skill's name
   @param description - skill's description */

sealed class Skill(name: String, description: String):
  
  val skillName = this.name
  
  case object hit  extends Skill("hit", "XXX")
  case object stab extends Skill("stab", "XXX")
  case object hug  extends Skill("hug", "XXX")

end Skill




