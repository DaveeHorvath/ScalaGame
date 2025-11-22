
SKILLS
/* Skills player can obtain through the game. Currently, three skills are available:

   1. HIT  - Basic skill. We start our game with it. Takes away XXXX of the opponent's health.
   2. STAB - The skill player can gain when completing the optional Glass Ball level. Takes away XXXX of the opponent's health.
   3. HUG  - The skill player can gain when completing the Grandma's Kitchen level. Reduces the damage caused by the Sheep in
             level Wolf&Sheep Fight from 15 to 10 [LOOK CHARACTER->SHEEP]. 

  All skills have
   @param name        - skill's name
   @param description - skill's description */



OBJECTS
/* Objects character can collect to their inventory through the journey (IMPORTANT: THE OBJECTS CHARACTER COLLECTS DURING THE GRANNY LEVEL
   AREN'T INVOLVED IN THIS LIST, AS THEY CANNOT BE ASSIGNED TO A CHARACTER'S INVENTORY!!!!) Currently, the objects character can collect are:

   1. Cookie     - can be collected after the Grandma's Kitchen chapter, if and only if the player manages to fulfil the mission.
                   If the player fails to fulfil the mission and ends up stabbing grandma, cookies (3 cookies, to be precise) are not added to their
                   inventory.
                   Eating a cookie increases the damage caused by the player's next attack (hit or stab) by XXXX percent.
                   One cookie can be used only once. After the use, it is removed from the inventory.
                   If the player has 3/5 cookies gained during the Soup Man chapter, they can bribe a soup man with them. If this is the case
                   , cookies are removed from the player's inventory.
                   If the character reaches home with some remaining cookies, each cookie increases the player's happiness level by XXX points.

   2. Warm Wool - is collected after the Sheep and Wolves chapter (not an optional item. Everyone who manages to kill wolves and sheep gets it).
                  It can be later used during the Soup Man chapter. The player can use it to keep the soup (another object) warm.
                  Reaching home with warm soup increases the player's happiness level by more points than reaching home with cold soup
                  (look, soup object).

   3. Soup      - can be collected during the Soup Man chapter. There are multiple ways to gain a soup:
                  a. Player can buy soup from a soup man. The player can either pay full price for the soup, that is XXX of their money.
                     The player can also trade with a soup man. If the correct dialog options are chosen, the price of the soup decreases by 50%.
                     Now, players can buy soup for a new price (but they still don't have to).
                  b. Player can bribe a soup man with 3 cookies.
                  c. Player can stab a soup man and still the soup (NOTE: THIS OPTION DECREASES THE PLAYER'S HAPPINESS LEVEL BY XXX)
                  d. Player can skip getting a soup.
                  Soup increases the player's happiness level by XXX. If the player puts soup in the Warm Wool, the happiness level
                  increases by XXX.

   4. Flowers   - can be collected on the Flower Field. Increase happiness level by XXX points.
   
   All objects have
   @param name        - Object's name
   @param description - Object's description */



CHARACTERS
/* Each character in the game can be separated into Enemy and NPC.
   All enemies and NPCs share: */

ENEMIES
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

NPC
/* In development */
