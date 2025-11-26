package o1.AwesomeGame

import scala.collection.mutable.{ArrayStack, Map}
import o1.AwesomeGame.Skill
import o1.AwesomeGame.Item
import o1.AwesomeGame.Enemies.Enemy


class Area(name: String, description: String):
  
  /* Lists all the objects that the player can collect in this level in the form of a map, where the key is the name of the object. for some levels 
     objects is going to be empty */
  private val items     :Map[String, Item]    = Map[String, Item]()
  /* Lists all the characters that the player can interact with in this level in the form of a map, where the key is the name of the character.
     For some levels, objects is going to be empty */
  private val enemies  :Map[String, Enemy] = Map[String, Enemy]()
  /* Lists all the neighbouring levels, where the key is a direction, fx. north, east, etc. */
  private val neighbours  :Map[String, Area]     = Map[String, Area]()
  
  /*              METHODS ABOUT NEIGHTBOUR                 */

  /* Creates a reference from the key value, that is, direction fx. north, east, etc., and the level to which it's pointing. */
  def setNeighbor(direction: String, neighbor: Area) =
    this.neighbours += direction -> neighbor
  /* Adds all the directions in the key, pointer form, to the private var neighbours. */
  def setNeighbors(exits: Vector[(String, Area)]) =
    this.neighbours ++= exits
   /* returns the neighbor in the given direction. */
  def neighbor(direction: String) = this.neighbours.get(direction)

  /*              METHODS ABOUT ITEMS                 */
   
  def addItem(item: Item) = this.items += item.name -> item
  
    // Determines if the area contains an item of the given name.
  def containsItem(itemName: String): Boolean =
    this.items.contains(itemName)
  
  // Removes the item of the given name from the area, assuming an item with that name was there to begin with.
  // Returns the removed item wrapped in an Option or None in the case there was no such item present.
  def removeItem(itemName: String): Option[Item] =
    this.items.remove(itemName)
    
  /*              METHODS ABOUT ENEMIES                 */
    
  def addEnemy(enemy: Enemy) = this.enemies += enemy.name -> enemy

  def removeEnemy(enemyName: String) = this.enemies.remove(enemyName)

  def getEnemyWithName(enemyName: String) = enemies.get(enemyName)

  def fullDescription =
    val listOfContents = if this.items.isEmpty then "" else "\nYou see here: " + this.items.values.mkString(" ")
    val listOfEnemies = if this.enemies.isEmpty then "" else "\nYou can meet here: " + this.enemies.values.mkString(" ")
    val listOfExits = "\n\nExits available: " + this.neighbours.keys.mkString(" ")
    this.description + listOfContents + listOfEnemies + listOfExits
    
    
  def getName = name
  
end Area