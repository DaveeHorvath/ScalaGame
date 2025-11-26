package o1.AwesomeGame

class Action(input: String):

  private val commandText = input.trim.toLowerCase
  private val verb        = commandText.takeWhile( _ != ' ' )
  private val modifiers   = commandText.drop(verb.length).trim

  val defaultHelpFields: Array[String] =
    Array[String](
      "help - produces this message",
      "go <direction> - moves the player to the room in that direction if it exists",
      "happiness - mental health check",
      "inventory - list the items that are in your bag of holding",
      "get <item name> - pick up items from the ground",
      "drop <item name> - littering, just putting stuff on the ground",
      "examine <item name> - make the game describe what you are looking at",
      "talk_to <name> - initiate conversation",
      "talk_to <name>: <your opinion> - continue conversation with a specified text",
      "quit - leaves the game, but why would you do that?"
    )

  def execute(actor: PlayerObject): Option[String] =
    this.verb match
      case "go"        => Some(actor.go(this.modifiers))
      case "happiness" => Some(actor.getHappiness())
      case "quit"      => Some(actor.quit())
      case "inventory" => Some(actor.getInventoryString)
      case "get"       => Some(actor.get(this.modifiers))
      case "examine"   => Some(actor.examine(this.modifiers))
      case "drop"      => Some(actor.removeItem(this.modifiers))
      case "talk_to"  => Some(actor.interact(this.modifiers))
      case "help"      => Some(defaultHelpFields.mkString("\n"))
      case other       => None

  /** Returns a textual description of the action object, for debugging purposes. */
  override def toString = s"$verb (modifiers: $modifiers)"

end Action