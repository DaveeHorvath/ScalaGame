package o1.adventure.draft.ui
import o1.adventure.draft.GameObjects

import java.io.{FileInputStream, FileOutputStream, InputStream, PrintStream}
import scala.concurrent.{ExecutionContext, Future}
import java.util.concurrent.ConcurrentHashMap
import java.util.concurrent.atomic.AtomicReference
import scala.jdk.CollectionConverters.*
import java.util.concurrent.Executors
import scala.collection.concurrent.TrieMap
import scala.concurrent.*

class LineBuffer(size: Int):
  private val buf = new Array[Char](size)
  private var pos = 0

  def emplace(s: String, start: Int): Unit =
    s.getChars(start, math.min(start + s.length, size), buf, pos)

  override def toString: String =
    new String(buf, 0, pos)

enum ErrorCode:
  case Success
  case Error

object Engine {
  // io operations related
  private val mainOutputScreenName: String = "./sillyNamedPipe"
  private val mainOutputScreen: PrintStream = new PrintStream(new FileOutputStream(mainOutputScreenName))

  // screen related info
  private val mainScreenWidth: Int = 200
  private val mainScreenHeight: Int = 60

  var frontBuffer = new ConcurrentHashMap[Int, String]().asScala
  var backBuffer = new ConcurrentHashMap[Int, String]().asScala

  // gameplay related info
  var environmentObject = new TrieMap[Int, AtomicReference[GameObjects]]() // zindex + atomic readable object

  // private engine related info
  val clear = "\u001b[2J"
  val frameRate = 30
  implicit val frameTime: Long = (frameRate/60.0 * 1000000).toLong
  implicit val context: ExecutionContext = ExecutionContext.fromExecutor(Executors.newFixedThreadPool(8))
  private def E_Setup: ErrorCode = {
    return ErrorCode.Success
  }

  private def E_Place(zIndex: Int, obj: AtomicReference[GameObjects]): ErrorCode = {

    ErrorCode.Success
  }

  private def E_Present: Future[ErrorCode] = Future {
    var temp = frontBuffer
    frontBuffer = backBuffer
    mainOutputScreen.write((clear + "\n" + frontBuffer.values.mkString("\n")).getBytes("UTF-8"))
    backBuffer = temp
    ErrorCode.Success
  }

  private def E_ComputeState = {
    environmentObject.foreach((zIndex, ref) => Future{ E_Place(zIndex, ref) }
      .onComplete
      {
        case scala.util.Success(errorCode) => {}
        case scala.util.Failure(e) => {}
      })
  }

  // external available
  def run = {
    E_Setup
    while true do
    {
      val frameStart = System.nanoTime()
      E_ComputeState
      E_Present.onComplete{
         case scala.util.Success(value) => {
           val frameEnd = System.nanoTime()
           if (frameEnd - frameStart > 0) then {
             // chill while we get ready for next frame
            val timeToSleep = frameTime - (frameEnd - frameStart)
            Thread.sleep(timeToSleep / 1000000, timeToSleep.toInt % 1000000)
           }
         }
         case scala.util.Failure(e) => {}
      }
    }
  }
}
