package o1.adventure.draft
trait GameObjects(var renderable: String, var interactable: Boolean, val worldId: Int):
  var posX: Int
  var posY: Int
  val lines = renderable.split(":").zipWithIndex
  def width: Int => 0
  def height: Int => 0
end GameObjects

