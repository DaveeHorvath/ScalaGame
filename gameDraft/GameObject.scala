package o1.gameDraft

trait GameObjects(var renderable  : String,  // the form that is displayed to the World
                  var interactable: Boolean, // rereturns if the objects can be interracted with
                  val worldID     : Int,     // the id of the world the object is located in 
                  val name        : String,  // the name of the object
                  val description : String): // the description of the object
  
  /*                    POSITION                            */
  
  var posX: Int       // the position of the object on the x-axis
  var posY: Int       // the position of the object on the y-axis
  var pos: (Int, Int) // the position of the object in two dimensions
  
  def lines = renderable.split(":").zipWithIndex
  def width: Int => 0
  def height: Int => 0
  
  // returns the short description of the object
  override def toString = s"${this.name}: ${this.description}"
  
  // allows the player to interact with the object
  def interract(player: PlayerObject) =
    if !this.interactable then
      s"You cannot interact with this object."
    else 
      "Hello!"
  
end GameObjects
