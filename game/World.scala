package o1.game

/* World of the game groups all existing levels together and defines their positions with respect to each other and the position of the player.
   It also defines the condition of the game. */

class World:

  val title = "The exemplary title" // change it later

  private val nothingField     = Level("Nothing Field", "The place where it all begins...\nYou have no other option than to go forward.")
  private val crossRoads       = Level("Cross Roads", "The place where the roads are crossing...\nThe sign marks directions\nNorth -> The Great Ball of Wisdom\nEast -> Grandma's house")
  private val glassBall        = Level("The Great Ball of Wisdom", "XXX")
  private val grandmasHouse    = Level("Grandma's House", "XXX")
  private val sheepAndWolf     = Level("Bloody Battlefield", "XXX")
  private val parkWithSoundMan = Level("Park. There is a Soup Man stand in the middle", "XXX")
  private val flowerField      = Level("The field full of flowers", "XXX")
  private val home             = Level("Home", "The end of the game")

  private val allLevels = Vector(nothingField, crossRoads, glassBall, grandmasHouse, sheepAndWolf, parkWithSoundMan, flowerField, home)
  
  /* adds the player to the game in the initial position, nothingField. */
  val player = Player1(nothingField)

  nothingField      .setNeighbors( Vector(                           "east" -> crossRoads                                                               ) )
  crossRoads        .setNeighbors( Vector(  "north" -> glassBall,    "east" -> grandmasHouse,                               "west" -> nothingField      ) )
  glassBall         .setNeighbors( Vector(                                                     "south" -> crossRoads                                    ) )
  grandmasHouse     .setNeighbors( Vector(  "north" -> sheepAndWolf,                                                        "west" -> crossRoads        ) )
  sheepAndWolf      .setNeighbors( Vector(                                                                                  "west" -> parkWithSoundMan  ) )
  parkWithSoundMan  .setNeighbors( Vector(  "north" -> flowerField,  "east" -> grandmasHouse                                                            ) )
  flowerField       .setNeighbors( Vector(                           "east" -> home,           "south" -> parkWithSoundMan                              ) )
  home              .setNeighbors( Vector(                                                                                  "west" -> flowerField       ) )


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