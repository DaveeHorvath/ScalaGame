package o1.game.ui

import scala.swing.*
import scala.swing.event.*
import javax.swing.UIManager
import o1.game.World
import java.awt.{Point, Insets, Dimension}
import scala.language.adhocExtensions // enable extension of Swing classes 

object GameGUI extends SimpleSwingApplication:
  UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName)

  def top = new MainFrame:

    // Access to the application’s internal logic:

    val game = World()
    val player = game.player

end GameGUI
