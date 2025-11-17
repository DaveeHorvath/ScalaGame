package o1.game

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

sealed class Object(name: String, description: String):
  case object cookie   extends Object("grandma's chocolate chip cookie", "XXX")
  case object warmWool extends Object("sheep's warm wool", "XXX")
  case object Soup     extends Object("delicious chicken soup", "XXX"):
    var isWrapped = false // checks if the soup is wrapped in the warmWool. Soup can be wrapped if the player decides so. 
    var isWarm = this.isWrapped // checks if the soup is warm. Soup is warm if it is wrapped in the warmWool
  case object Flowers  extends Object("beautiful flowers", "XXX")

end Object

