package o1.AwesomeGame.Enemies

import o1.AwesomeGame.{PlayerObject, Skill, Soup, World}

/*class SoupMan(renderable   : String,             // the form that is displayed to the World
                interactable : Boolean,            //returns if the objects can be interacted with
                 worldID     : Int,                // the ID of the world the object is located in 
                 name        : String,             // the name of the object
                 description : String,             // the description of the object
                 initialHealthLevel : Int,         // the initial health level of the enemy, aka how hard you have to hit it to kill it
                 demage      : Option[Int])        //damage caused by the enemy's attack if any
  extends Enemy( "", interactable, worldID, "The Soup Man", "It is the dirtman (https://www.youtube.com/shorts/Su4Kb-roLZE) but for soup, he really likes selling it as well. Also really likes to negotiate!", 20, None): */

class SoupMan(val soupWorld: World)
  extends Enemy(soupWorld, "the soup man", "Soup Man from your local town. He makes the greatest soup of all times.", 20):

  private var soupsPrice = 0.2
  private val soupsReducedPrice = soupsPrice / 2
  private var started = false
  private var count = 0
  
  val soup = Soup(this.soupWorld)
    
  def buySoup(player: PlayerObject) =
    if player.howHappy >= this.soupsPrice then
      player.changeHappines(-soupsPrice)
      player.addItem(soup) // SOUP DOESN'T EXIST YET. IMPLEMENT IT IN ITEMS
      this.started = true
      s"You bought Soup." 
    else 
      s"Sorry, you don't have enough happiness. Try something else."
  
  def negotiatePriceRound1(player: PlayerObject) =
    count += 1
    s"So, you want to negotiate? Hmph. The price is ${this.soupsPrice} happiness points, and it reflects the gravity of the broth!" +
    s"\nBefore you attempt to cheapen this masterpiece, tell me: Do you understand the value of a soup that has been gazed upon by a sentient swan?" +
    s"\nWhat do you answer?" + 
    s"\na. That sounds highly unsanitary. Swans carry diseases." + // Incerrect
    s"\nb. A swan's gaze! That must add at least .9 happiness to the value alone. I am in awe." + // Correct
    s"\nc. I have .9 happiness. Take it all, I'm starving, and let's skip the swan story." // Incorrect
  
  def negotiatePriceRound2(player: PlayerObject) =
    count += 1
    s"Your knowledge of Swan-gaze economics is noted. You possess some rare insight, I'll grant you that." +
    s"But I must consider my overhead! This broth simmers in a pot made from the melted-down regrets of a retired pirate. How can you justify a lower price?" +
    s"\nWhat do you answer?" + 
    s"\na. Regrets are free. Pirates have lots of them. You're trying to con me." + // Incerrect
    s"\nb. Melted metal costs money. I'll pay in attention (.1 happiness) for the soup, and you keep the pot." + // Incorrect
    s"\nc. While the pirate's remorse is valuable, true regret is lightweight. Therefore, the pot is a meager 100 gold, which I will not pay for soup." // Correct
    
  def negotiatePriceRound3(player: PlayerObject) =
    count += 1
    s"You're cutting me deep, friend. Deeper than I cut the carrots! You've successfully dismantled my pirate's premise!" +
    s"Tell me this: If you could magically make this soup any price you wanted, what would you make it, and why wouldn't you choose zero?" +
    s"Give me the final, perfectly reasonable answer for why this transaction must conclude at 0.1 happiness points." +
    s"\nWhat do you answer?" + 
    s"\na. Because 0.1 happiness points is all the money I currently possess." + // Incerrect
    s"\nb. Zero. I should get it for free because I complimented your beard." + // Incorrect
    s"\nc. Because 0.1 happiness points is a fair exchange for a cup of broth, and I will stop talking about the wall, the swan, and the pirate immediately." // Correct
    
  
  def bribe(player: PlayerObject) =
    if player.hasItem("cookies") then // FOR NOW PLAYER NEEDS ONLY ONE COOKIE. TRY TO CHANGE IT INTO 3, BUT IF THERE ISN'T ENOUGH TIME DON'T.
      player.removeItem("cookies")
      player.addItem(soup)
      player.changeHappines(0.1)
      this.started = true
      s"You bribed the Soup Man."
    else
      player.changeHappines(-0.1)
      s"You don't have a proper bribe! Try something else."

  
  override def interract(player: PlayerObject): String =
    if this.started then
      s"Sorry. You have already bought my soup. I don't hae more. Come back later."
    else
      s"Welcome! I have a delicious soup. It costs ${this.soupsPrice} happiness points. Do you want to buy it?\n What would you like to do?" +
          s"\n 1. Buy soup for ${this.soupsPrice} coins." +
          s"\n 2. Negotiate price." +
          s"\n 3. Bribe with cookies." +
          s"\n 4. Leave." +
          s"\n Please, choose a number 1-4."

  override def talkTo(player: PlayerObject, cont: String): String =
    if this.started then
      s"Sorry. You have already bought my soup. I don't have more. Come back later."
    else
      if cont == "1" then this.buySoup(player)
      else if cont == "2" then this.negotiatePriceRound1(player)
      else if cont == "3" then this.bribe(player)
      else if cont == "4" then ""
      else if count == 1 then
        if cont == "b" then this.negotiatePriceRound2(player)
        else s"No way I'm selling you this"
      else if count == 2 then
        if cont == "c" then this.negotiatePriceRound3(player)
        else s"No way I'm selling you this"
      else if count == 3 then
        if cont == "c" then
          this.soupsPrice = this.soupsReducedPrice
          this.buySoup(player)
        else s"No way I'm selling you this"
      else ""

end SoupMan
