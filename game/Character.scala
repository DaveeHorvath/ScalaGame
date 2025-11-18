package o1.game

/* Each character in the game can be separated into an Enemy and an NPC.
   All enemies and NPCs share: */

sealed trait Character

/* Enemy is an extension of a Character trait. Enemies contain both characters with the ability to kill the player, as well as characters
   that cannot cause damage, but which the player has to defeat to finish the game [Note: there is one enemy, "glassBall"
   which doesn't have to be defeated. It is an optional enemy. The enemies like that are denoted by the damage = 0.
   Currently, enemies present in the game are:

   1. Glass Ball   - The Glass ball is the first enemy the player can meet. This is also the only optional enemy. Glass Ball asks the player
                     three riddles. If the player answers all the questions correctly, the ball commits suicide, and the player gains the skill
                     "Stab" [LOOK: @SKILL->STAB]. Ball disappears.
                     The player has up to 3 attempts to defeat the ball. If the player fails to win all three attempts, the ball "leaves"
                     (it disappears) and the player loses the ability to gain the skill "Stab".

   2. Grandma      - Grandma is the second enemy the player meets and the first one that can actually kill them (and cause game over).
                     There are two ways of defeating Grandma.
                     a. Completing the "Grandma's Kitchen" level - [LOOK: LEVELS->GRANDMA'S KITCHEN].
                     b. If the player fails to complete the level, they can use the skill "Stab" on Grandma, killing her.
                     NOTE: GRANDMA'S DAMAGE IS SET TO 50 ON PURPOSE. THIS MEANS THAT IF THE PLAYER DOESN'T HAVE SKILL "STAB" THEY'LL DIE
                           INSTANTLY FROM GRANDMA'S ATTACK.

   3. Sheep        - Sheep is one of the enemies in the "Big Bad Sheep and Three Little Wolves" level. Sheep are the strongest opponents
                     in the game, as they cause the highest damage (except Grandma, but she's supposed to kill). However, sheep's damage
                     can be reduced to 10 if the player uses their skill "hug" on the sheep [LOOK SKILL->HUG].

   4. Three Wolves - Other enemies in the "Big Bad Sheep and Three Little Wolves" level. Wolves are just ordinary opponents the player has to
                     defeat.

   5. Soup Man     - Soup Man is the second enemy that cannot kill the character. He is here only because he has to be "defeated" end of the game.
                     Soup Man is defeated when the character gains a "Soup" object [LOOK OBJECTS->SOUP].

   Every enemy has
   @param name         - enemy's name
   @param description  - enemy's description
   @param healthPoints - enemy's health
   @param damage       - damage caused by an enemy to the player */
sealed class Enemy(name: String, description: String, healthPoints: Int, damage: Int) extends Character:

  case object glassBall   extends Enemy("The glass ball of wisdom", "XXX", 30, 0)
  case object grandma     extends Enemy("Granny", "XXX", 20, 50)
  case object sheep       extends Enemy("Big Bad Sheep", "XXX", 60, 15)
  case object threeWolves extends Enemy("Three Little Wolves", "XXX", 120, 10)
  case object soupMan     extends Enemy("The Soupman", "XXX", 20, 0)

sealed class NPC(name: String, description: String) extends Character
/* In development */