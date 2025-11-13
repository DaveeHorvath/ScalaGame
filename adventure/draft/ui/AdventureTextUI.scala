package o1.adventure.draft.ui

import java.io.{FileInputStream, FileOutputStream, InputStream, PrintStream}
import scala.concurrent.Future
import scala.sys.process.*

/** The singleton object `AdventureTextUI` represents a fully text-based version of the
  * Adventure game application. The object serves as an entry point for the game,
  * and can be run to start up a user interface that operates in the text console.
  *
  * NOTE: The AdventureDraft module is not even close to being well designed.
  * See Chapter 9.3 in the course materials. */
object AdventureTextUI:
  def main(args: Array[String]): Unit = {
    var posX = 0
    var posY = 0
    var character =
      "------:"+
      " #### :"+
      " #  # :"+
      " #  # :"+
      " #### :"+
      "      "
    val z = character.split(":").zipWithIndex
    val clear = "\u001b[2J"
    val up = "\u001b[2E"
    val down = "\u001b[2F"
    val erase = "\u001b[2K"

    val col_1 = "\u001b[31;41m"
    val col_2 = "\u001b[32;42m"
    val col_3 = "\u001b[33;43m"
    val colors = Seq(col_1, col_2, col_3)
    "rm -f sillyNamedPipe".!
    "mkfifo sillyNamedPipe".!
    "rm -f inputPipe".!
    "mkfifo inputPipe".!
    "gnome-terminal --geometry=102x5 --hide-menubar --zoom=1 -- bash -c 'tput civis; while true; do cat ./inputPipe; done'".!
    "gnome-terminal --geometry=202x62 --hide-menubar --zoom=0.7 -- bash -c 'tput civis; while true; do cat ./sillyNamedPipe; done'".!
    System.setOut(new PrintStream(new FileOutputStream("./sillyNamedPipe")))
    System.setIn(new FileInputStream("./inputPipe"))
    var buffer = scala.collection.mutable.Map[Int, String]() // list of rows


    // set up empty space
    for y <- 0 to 60 do
      var row = ""
      for x <- 0 to 200 do
        row += "█"
      buffer += (y -> row)
      println(buffer(y))

    var updateQueue = Array[Int]()
    var currentRow = 0
    for i <- 0 to 59 do
      print(up)

    while true do
      // read updates

      // compute changed rows
      z.foreach((line, row) =>
        updateQueue = updateQueue :+ row
        buffer(row) = "█" * posX + line + "█" * (200 - (posX + 5))
      )

      // single thread print
      updateQueue.foreach(target =>
        print(down)
        print(erase)
        println(buffer(currentRow))
        currentRow += 1
        Console.flush()
      )
      updateQueue.foreach(target =>
        currentRow -= 1
        print(up)
      )
      updateQueue = Array[Int]()
      // move back up
      Thread.sleep(200)
  }
