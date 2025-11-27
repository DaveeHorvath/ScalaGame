package o1.AwesomeGame

import o1.AwesomeGame.Enemies.{GlassBall, Grandma, SoupMan}

/* World of the game groups all existing levels together and defines their positions with respect to each other and the position of the player.
   It also defines the condition of the game. */

class World:

  val title = "The exemplary title" // change it later
  private val nothingField     = Area("Nothing Field", "The place where it all begins...\nYou have no other option than to go forward.")

  /* adds the player to the game in the initial position, nothingField. */
  val player = PlayerObject(this, nothingField)

  private val crossRoads                 = Area("Cross Roads", "The place where the roads are crossing...\nThe sign marks directions\nNorth -> The Great Ball of Wisdom\nEast -> Grandma's house")
  private val glassBall                  = Area("The Great Ball of Wisdom", "A dark room, with a slight glow in the middle, probably forgot to change the lightbulb.")
  private val grandmasHouseLivingRoom    = Area("Grandma's House: Living Room", "Lively 90's old person home with a bright green couch and a lovely old lady.")
  private val grandmasHouseKitchen       = Area("Grandma's House: Kitchen", "Tiled floors and cooking equipment give this room almost an industrial feel.")
  private val grandmasHouseCorridor      = Area("Grandma's House: Corridor", "Its just like, you know, a corridor?")
  private val grandmasHouseGarden        = Area("Grandma's House: Garden", "The smell of greens are hitting your nose, something has to keep the old people smiling...")
  private val grandmasHouseBedroom1     = Area("Grandma's House: Bedroom", "Grandmas bedroom is a place where few will go and even less returned unharmed")
  private val grandmasHouseBathroom      = Area("Grandma's House: Bathroom", "*Toilet noises*")
  private val parkWithSoundMan           = Area("Park. There is a Soup Man stand in the middle", "Very grassy place with road and benches for the homeless")
  private val home                        = Area("Home", "The end of the game")

  /* Sets all the neighbors for all the levels. */
  nothingField      .setNeighbors( Vector(  "north" -> crossRoads                                                                                      ) )
  crossRoads        .setNeighbors( Vector(  "north" -> glassBall,    "east" -> grandmasHouseLivingRoom,                               "south" -> nothingField      ) )
  glassBall         .setNeighbors( Vector( "south" -> crossRoads                                    ) )
  grandmasHouseLivingRoom     .setNeighbors( Vector(  "north" -> grandmasHouseGarden, "east" -> grandmasHouseCorridor, "south" -> grandmasHouseKitchen, "west" -> crossRoads ) )
  grandmasHouseKitchen.setNeighbors( Vector ( "north" -> grandmasHouseLivingRoom, "east" -> grandmasHouseBathroom ) )
  grandmasHouseCorridor.setNeighbors( Vector( "east" -> grandmasHouseBedroom1, "west" -> grandmasHouseLivingRoom) )
  grandmasHouseGarden.setNeighbors( Vector(  "north" -> parkWithSoundMan, "south" -> grandmasHouseLivingRoom) )
  grandmasHouseBedroom1.setNeighbors( Vector( "west" -> grandmasHouseCorridor ) )
  grandmasHouseBathroom.setNeighbors( Vector( "west" -> grandmasHouseKitchen ) )
  parkWithSoundMan  .setNeighbors( Vector( "east" -> home, "south" -> grandmasHouseGarden ) )
  home              .setNeighbors( Vector( "west" -> parkWithSoundMan) )

  val destination = this.home

/* GLASS BALL */
  private val enemy1 = new GlassBall(this)
  this.glassBall.addEnemy(enemy1)

  enemy1.addRiddle("I am built without bricks, wood, or stone, and offer a refuge when you feel alone. I can be empty, full of sorrow, or full of light. I grow bigger as you get older, and you only own one. What am I? ", "memory")
  enemy1.addRiddle("I am weightless and can be seen, but you can only truly feel me when I'm gone. I am the great equalizer, yet no one can own me. I steal your moments, but give you experience in return. What am I? ", "time")
  enemy1.addRiddle("I have no voice, but I can imitate yours perfectly. I follow you in the day, yet vanish when the sun sets completely. I am your constant companion, yet hold no substance. What am I? ", "shadow")
  enemy1.addRiddle("I travel without a single step and create pictures you cannot see. I have no hands, but I can break a house into dust. I am feared, but I am necessary. What am I?" , "wind")
  enemy1.addRiddle("I am always hungry, I must always be fed. The finger I lick will soon turn red. I start small, but I can consume the entire world. What am I?" , "fire")
  enemy1.addRiddle("I have no life, but I can die. I am not a clock, but I measure your day. I have a face, but no body. What am I?" , "candle")
  enemy1.addRiddle("I am full of holes, yet still hold water. I am always waiting to be broken. I can be worn, but I am not clothing. What am I?" , "promise")
  enemy1.addRiddle("I have no voice, but when I break, I make a loud noise. I am strongest when I am new. I let you see the world, but I am sometimes held prisoner by wood. What am I?" , "glass")
  enemy1.addRiddle("I am always coming, but never arrive. I can be planned, but never possessed. I start when the current day ends. What am I?" , "tomorrow")

/* GRANDMAS HOUSE */

  private val enemy2 = new Grandma(this)
  this.grandmasHouseLivingRoom.addEnemy(enemy2)

  // REQUIRED ITEMS
  private val flower = new Item(this, "flower", "Flower lying down on the grandma's floor in the bathroom, for some reason.")
  this.grandmasHouseBathroom.addItem(this.flower)
  private val eggs = new Item(this, "eggs", "Eggs from your grandma's garden.")
  this.grandmasHouseBedroom1.addItem(this.eggs)
  private val sugar = new Item(this, "sugar", "Crystal white sugar.")
  this.grandmasHouseKitchen.addItem(this.sugar)
  
  enemy2.addrequired(this.flower)
  enemy2.addrequired(this.eggs)
  enemy2.addrequired(this.sugar)

  // TRAP OTEMS
  private val potatoes = new Item(this, "potatoes", "Potatoes from your grandma's garden.")
  this.grandmasHouseBedroom1.addItem(this.potatoes)
  private val cheese = new Item(this, "cheese", "Delicious blue cheese.")
  this.grandmasHouseCorridor.addItem(this.cheese)
  private val pancakes = new Item(this, "pancakes", "Fresh and warm pancakes. You wonder what they are doing here.")
  this.grandmasHouseGarden.addItem(pancakes)
  private val chocolate = new Item(this, "chocolate", "Dark chocolate.")
  this.grandmasHouseGarden.addItem(chocolate)

/* PARK */

  private val enemy3 = new SoupMan(this)
  this.parkWithSoundMan.addEnemy(enemy3)


/* CONDITIONS TO WIN */

  def isOver = this.player.location == this.destination || this.player.hasQuit

  def goodbyeMessage =
    if this.player.location == this.destination && this.player.howHappy > 0.5 then
      "You made it home and you are happy! Isn't that awesome? You can chill now and watch TV."
    else if this.player.location == this.destination && this.player.howHappy == 0.5 then
      "You aren't happy, but you are also not unhappy. You come back home thinking about the life without mining you have. You stare at the wall for hours."
    else if this.player.location == this.destination && this.player.howHappy < 0.5 then
      "You are very miserable :("
    else  // game over due to player quitting
      "Quitter!"

/* OPENING */
  def welcomeMessage = "You are in the middle of nothing. You are looking for the way back home.\nYou are scared. Remember to take care of your happiness.\nShould we start walking?"

  def playTurn(command: String): String =
    val action = Action(command)
    val outcomeReport = action.execute(this.player)
    outcomeReport.getOrElse(s"""Unknown command: "$command".""")

