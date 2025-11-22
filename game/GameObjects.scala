package o1.game

trait GameObjects(var renderable  : String,
                  var interactable: Boolean,
                  val world       : World):
  
  var posX: Int
  var posY: Int
  def lines = renderable.split(":").zipWithIndex
  def width: Int => 0
  def height: Int => 0

  def interract(playerObject: PlayerObject) = "Hello"
  
end GameObjects
