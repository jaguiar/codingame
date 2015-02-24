import math._
import scala.util._

/**
 * Auto-generated code below aims at helping you parse
 * the standard input according to the problem statement.
 **/
object BatmanPlayer {

	val moveRegex = "([U|D]){0,1}([L|R]){0,1}".r

  // a little more clever way to parse input
	object MoveString {
		def apply(str: String): (Option[String],Option[String]) = str match {
		case moveRegex(upDown, null) => (Some(upDown), None)
		case moveRegex(null, leftRight) => (None, Some(leftRight))
		case moveRegex(upDown, leftRight) => (Some(upDown),Some(leftRight))
		case _ => throw new IllegalArgumentException
		}
	}
  
  //the main idea is to "cut in half" the area to look into (aka (minX, maxX) and (minY,minX)), so that we finally found the bomb position
	def main(args: Array[String]) {
		// w: width of the building.
		// h: height of the building.
		val Array(w, h) = for(i <- readLine split " ") yield i.toInt
				val n = readInt // maximum number of turns before game over.
				val Array(x0, y0) = for(i <- readLine split " ") yield i.toInt

				// game loop
				var x=(x0, 0, w)
				var y=(y0, 0, h)
				while(true) {
					val (updown, leftRight) = MoveString(readLine) // the direction of the bombs from batman's current location (U, UR, R, DR, D, DL, L or UL)
					updown match {
					  case Some("U") => y = (computeMove(y._1,y._2),y._2,y._1)
            case Some("D") => y = (computeMove(y._1,y._3),y._1,y._3)
            case _ => {}
					}
          leftRight match {
            case Some("R") => x = (computeMove(x._1,x._3), x._1,x._3)
            case Some("L") => x = (computeMove(x._1,x._2), x._2,x._1)
            case _ => {}
          }

					// Write an action using println
					// To debug: Console.err.println("Debug messages...")
					println(x._1 + " " + y._1) // the location of the next window Batman should jump to.
				}
	}

	def computeMove(position:Int, edge:Int):Int = {
			(position+edge)/2
	}
}