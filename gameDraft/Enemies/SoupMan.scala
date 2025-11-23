package o1.gameDraft.Enemies

import o1.gameDraft.PlayerObject

class SoupMan(renderable   : String,             // the form that is displayed to the World
                interactable : Boolean,            //returns if the objects can be interacted with
                 worldID     : Int,                // the ID of the world the object is located in 
                 name        : String,             // the name of the object
                 description : String,             // the description of the object
                 initialHealthLevel : Int,         // the initial health level of the enemy, aka how hard you have to hit it to kill it
                 demage      : Option[Int])        //damage caused by the enemy's attack if any
  extends Enemy( "XXX", interactable, worldID, "The Soup Man", "XXX", 20, None):

  private val soupsPrice = 10.0
  private val soupsReducedPrice = soupsPrice / 2
  private val bribeInCookies = 3
  
  def buySoup(player: PlayerObject) =
    if player.howMuchMoney >= 10.0 then
      player.addMoney(-soupsPrice)
      player.addItem(Soup) // SOUP DOESN'T EXIST YET. IMPLEMENT IT IN ITEMS
      s"You bought Soup." 
    else 
      s"Sorry, you don't have enough money. Try something else." 
  
  def negotiatePrice(player: PlayerObject) = ???
  
  def bribe(player: PlayerObject) =
    var n = 0
    if player.hasItem(cookie x 3) then // has to check if player's inventory contains 3 cookies (FIX IT!!!!)
      for n < 3 do
        player.removeItem(cookie)
        n += 1
      player.addItem(Soup)
      s"You bribed the Soup Man."
    else
      s"You don't have a proper bribe! Try something else."
  
  def attack(player: PlayerObject) =
    if player.hasSkill(Stab) then
      this.takeDemmage(Stab)
      
      if this.isDead then
        this.die
        player.addItem(Soup) // SOUP DOESN'T EXIST YET. IMPLEMENT IT IN ITEMS
        s"You killed the Soup Man. You get Soup. But for what price..."
      else
        s"You did not succeed."
    else
      s"You don't have a required skill."
  
  override def interract(player: PlayerObject): String = 
    s"Welcome! I have a delicious soup. It costs ${this.soupsPrice}. Do you want to buy it?\n What would you like to do?" +
        s"\n 1. Buy soup for ${this.soupsPrice} coins." +
        s"\n 2. Negotiate price." +
        s"\n 3. Bribe with ${this.bribeInCookies} cookies." +
        s"\n 4. Attack Soup Man." +
        s"\n 5. Leave." +
        s"\n Please, choose a number 1-5."

end SoupMan
