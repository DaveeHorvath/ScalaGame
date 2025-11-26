package o1.AwesomeGame.Enemies

import o1.AwesomeGame.{PlayerObject, Skill, Soup, World}

/*class SoupMan(renderable   : String,             // the form that is displayed to the World
                interactable : Boolean,            //returns if the objects can be interacted with
                 worldID     : Int,                // the ID of the world the object is located in 
                 name        : String,             // the name of the object
                 description : String,             // the description of the object
                 initialHealthLevel : Int,         // the initial health level of the enemy, aka how hard you have to hit it to kill it
                 demage      : Option[Int])        //damage caused by the enemy's attack if any
  extends Enemy( "XXX", interactable, worldID, "The Soup Man", "XXX", 20, None): */

class SoupMan(val soupWorld: World)
  extends Enemy(soupWorld, "The Soup Man", "Soup Man from your local town. He makes the greatest soup of all times.", 20):

  private val soupsPrice = 25.0
  private val soupsReducedPrice = soupsPrice / 2
  
  val soup = Soup(this.soupWorld)
    
  def buySoup(player: PlayerObject) =
    if player.howMuchMoney >= this.soupsPrice then
      player.addMoney(-soupsPrice)
      player.addItem(soup) // SOUP DOESN'T EXIST YET. IMPLEMENT IT IN ITEMS
      s"You bought Soup." 
    else 
      s"Sorry, you don't have enough money. Try something else." 
  
  def negotiatePriceRound1(player: PlayerObject) =
    s"So, you want to negotiate? Hmph. The price is ${this.soupsPrice} coins, and it reflects the gravity of the broth!" +
    s"\nBefore you attempt to cheapen this masterpiece, tell me: Do you understand the value of a soup that has been gazed upon by a sentient swan?" +
    s"\nWhat do you answer?" + 
    s"\n1. That sounds highly unsanitary. Swans carry diseases." + // Incerrect
    s"\n2. A swan's gaze! That must add at least 450 gold to the value alone. I am in awe." + // Correct
    s"\n3. I have 500 gold. Take it, I'm starving, and let's skip the swan story." // Incorrect
  
  def negotiatePriceRound2(player: PlayerObject) =
    s"Your knowledge of Swan-gaze economics is noted. You possess some rare insight, I'll grant you that." +
    s"But I must consider my overhead! This broth simmers in a pot made from the melted-down regrets of a retired pirate. How can you justify a lower price?" +
    s"\nWhat do you answer?" + 
    s"\n1. Regrets are free. Pirates have lots of them. You're trying to con me." + // Incerrect
    s"\n2. Melted metal costs money. I'll pay 50 gold for the soup, and you keep the pot." + // Incorrect
    s"\n3. While the pirate's remorse is valuable, true regret is lightweight. Therefore, the pot is a meager 100 gold, which I will not pay for soup." // Correct
    
  def negotiatePriceRound3(player: PlayerObject) =
    s"You're cutting me deep, friend. Deeper than I cut the carrots! You've successfully dismantled my pirate's premise!" +
    s"Tell me this: If you could magically make this soup any price you wanted, what would you make it, and why wouldn't you choose zero?" +
    s"Give me the final, perfectly reasonable answer for why this transaction must conclude at 10 coins." +
    s"\nWhat do you answer?" + 
    s"\n1. Because 10 coins is all the money I currently possess." + // Incerrect
    s"\n2. Zero. I should get it for free because I complimented your beard." + // Incorrect
    s"\n3. Because 10 coins is a fair exchange for a cup of broth, and I will stop talking about the wall, the swan, and the pirate immediately." // Correct
    
  
  def bribe(player: PlayerObject) =
    if player.hasItem("cookies") then // FOR NOW PLAYER NEEDS ONLY ONE COOKIE. TRY TO CHANGE IT INTO 3, BUT IF THERE ISN'T ENOUGH TIME DON'T.
      player.removeItem("cookies")
      player.addItem(soup)
      player.changeHappines(0.1)
      s"You bribed the Soup Man."
    else
      player.changeHappines(-0.1)
      s"You don't have a proper bribe! Try something else."
      
      if this.isDead then
        this.die()
        player.changeHappines(-0.2)
        player.addItem(soup) // SOUP DOESN'T EXIST YET. IMPLEMENT IT IN ITEMS
        s"You killed the Soup Man. You get Soup. But for what price..."
      else
        s"You did not succeed."
  
  override def interract(player: PlayerObject): String = 
    s"Welcome! I have a delicious soup. It costs ${this.soupsPrice}. Do you want to buy it?\n What would you like to do?" +
        s"\n 1. Buy soup for ${this.soupsPrice} coins." +
        s"\n 2. Negotiate price." +
        s"\n 3. Bribe with cookies." +
        s"\n 4. Leave." +
        s"\n Please, choose a number 1-4."

end SoupMan
