package o1.game

/* World of the game groups all existing levels together and defines their positions with respect to each other and the position of the player.
   It also defines the condition of the game. */

class World:

  val title = "The exemplary title" // change it later
  private val nothingField     = Level("Nothing Field", "The place where it all begins...\nYou have no other option than to go forward.")

  /* adds the player to the game in the initial position, nothingField. */
  val player = PlayerObject(nothingField, this)

  private val crossRoads       = Level("Cross Roads", "The place where the roads are crossing...\nThe sign marks directions\nNorth -> The Great Ball of Wisdom\nEast -> Grandma's house")
  private val glassBall        = RiddlesLevel("The Great Ball of Wisdom", "XXX", player)
  private val grandmasHouse    = Level("Grandma's House", "XXX")
  private val sheepAndWolf     = Level("Bloody Battlefield", "XXX")
  private val parkWithSoundMan = Level("Park. There is a Soup Man stand in the middle", "XXX")
  private val flowerField      = Level("The field full of flowers", "XXX")
  private val home             = Level("Home", "The end of the game")

  private val allLevels = Vector(nothingField, crossRoads, glassBall, grandmasHouse, sheepAndWolf, parkWithSoundMan, flowerField, home)

  /* Sets all the neighbors for all the levels. */
  nothingField      .setNeighbors( Vector(                           "east" -> crossRoads                                                               ) )
  crossRoads        .setNeighbors( Vector(  "north" -> glassBall,    "east" -> grandmasHouse,                               "west" -> nothingField      ) )
  glassBall         .setNeighbors( Vector(                                                     "south" -> crossRoads                                    ) )
  grandmasHouse     .setNeighbors( Vector(  "north" -> sheepAndWolf,                                                        "west" -> crossRoads        ) )
  sheepAndWolf      .setNeighbors( Vector(                                                                                  "west" -> parkWithSoundMan  ) )
  parkWithSoundMan  .setNeighbors( Vector(  "north" -> flowerField,  "east" -> grandmasHouse                                                            ) )
  flowerField       .setNeighbors( Vector(                           "east" -> home,           "south" -> parkWithSoundMan                              ) )
  home              .setNeighbors( Vector(                                                                                  "west" -> flowerField       ) )

  /* Gives riddles to the glassBall level */
  this.glassBall.addRiddle("What walks on four legs in the morning, two legs in the afternoon, and three legs in the evening? "                                                                                                    ,"man")
  this.glassBall.addRiddle("I am built without bricks, wood, or stone, and offer a refuge when you feel alone. I can be empty, full of sorrow, or full of light. I grow bigger as you get older, and you only own one. What am I? ", "memory")
  this.glassBall.addRiddle("I am weightless and can be seen, but you can only truly feel me when I'm gone. I am the great equalizer, yet no one can own me. I steal your moments, but give you experience in return. What am I? "  , "time")
  this.glassBall.addRiddle("I have no voice, but I can imitate yours perfectly. I follow you in the day, yet vanish when the sun sets completely. I am your constant companion, yet hold no substance. What am I? "                , "shadow")
  this.glassBall.addRiddle("I travel without a single step and create pictures you cannot see. I have no hands, but I can break a house into dust. I am feared, but I am necessary. Who am I? "                                    , "wind")
  this.glassBall.addRiddle("I am always hungry, I must always be fed. The finger I lick will soon turn red. I start small, but I can consume the entire world. Who am I? "                                                         , "fire")

  /* REQUIREMENTS AND SITUATIONS TO COMPLETE THE GAME */

  def playerIsAtHome = this.player.position == home
  /* Returns different strings depending on whether the player lost or won. To win, the player must complete all the levels and be happy. */
  def ending: String =
    if this.playerIsAtHome && this.allLevels.forall( _.completionState) then
      if this.player.howHappy == 0.5 then
        "You are kinda happy and kinda unhappy. Very average, you are. You don't lose today, but you also don't win.\nBe better next time.\nOr worse. idk."
      else if this.player.howHappy > 0.5 then
        "Congratulation! You are ultra super happy. That means that you are winning."
      else
        "You are unhappy. You lose"
    else
      "You lost. Kwa Kwa :( "