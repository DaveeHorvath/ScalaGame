package o1.AwesomeGame.Enemies

import scala.collection.mutable.Map
import o1.AwesomeGame.{Item, PlayerObject, Skill, World}


class Grandma(val GrandmaWorld: World)
  extends Enemy(GrandmaWorld, "grandma", "Your crazy and cute grandma, who is a bit unstable", 20):

  // determines if the initiial task of collecting objects was successfull
  private var fail: Boolean = false
  private var isStarted = false
  
  // stors player's inventory before they start the level. Is used later to determin if player collected correct items.
  private var playerInventoryBeforeGrandma = Map[String, Item]()
  
  //stores player's inventory after the level, excluding items player had before starting the level.
  private var playerInventoryAfterGrandma = Map[String, Item]()
  
  private var requiredObjects = Map[String, Item]()

  def addrequired(item: Item) = this.requiredObjects += (item.name -> item)
  
  def takeInventory(player: PlayerObject) = player.whatInInventory
  
 // def inventoryAfter(player: PlayerObject) = this.playerInventoryBeforeGrandma.keys.foreach( name => this.playerInventoryAfterGrandma.remove(name) )
    
  
  def bakeCookies =
    this.isStarted = true
    s"Hello darling! I'm preparing cookies. Do you think you could help me?" +
    s"\nIt's very simple . You'll just need to collect:\n-flower\n-sugar\n-eggs" + 
    s"\nI'm sure you can make it darling! Otherwise... there will be consequences :)" +
    s"\nWhen you are done come back to me."

  // checks if player collected correct objects
  def playerSuccessInObjects(player: PlayerObject): Boolean =
    val before = this.playerInventoryBeforeGrandma
    val after  = this.takeInventory(player)
    val gainedItems = after.filter((key, x) => !before.contains(key))
    gainedItems.keys.toList.sorted  == this.requiredObjects.keys.toList.sorted
    
  def ifSuccessfullCookies(player: PlayerObject) =
    val cookies = Item(this.world, "cookies", "delicious cookies from your grandma")
    player.addItem(cookies)
    player.changeHappines(0.2)
    s"Thank you darling! You are amazing. Come here, give me a hug.\nYou gain: ${cookies.name}"

  override def interract(player: PlayerObject): String =
    if this.isStarted then
      if this.playerSuccessInObjects(player) then
        this.ifSuccessfullCookies(player)
      else
        player.changeHappines(-0.2)
        "YOU HAVE FAILED. NO MORE TRIES"
    // this is called if player menaged to fuck up baking cookies
    else
      this.takeInventory(player).keys.toList.foreach(x => requiredObjects.remove(x))
      if requiredObjects.keys.toList.length == 0 then
        s"How did you know! Are you a mind reader???????" + ifSuccessfullCookies(player)
      else
        playerInventoryBeforeGrandma = this.takeInventory(player).clone()
        this.bakeCookies
      
end Grandma
