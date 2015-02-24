import math._
import scala.util._

/**
 * Auto-generated code below aims at helping you parse
 * the standard input according to the problem statement.
 **/
object BatmanPlayer {

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
        Console.err.println("x="+x)
                Console.err.println("y="+y)
          val bomb_dir = readLine // the direction of the bombs from batman's current location (U, UR, R, DR, D, DL, L or UL)
          bomb_dir match {
            case "U" => y = (computeMove(y._1,y._2),y._2,y._1)
            case "D" => y = (computeMove(y._1,y._3),y._1,y._3)
            case "R" => x = (computeMove(x._1,x._3), x._1,x._3)
            case "L" => x = (computeMove(x._1,x._2), x._2,x._1)
            case "UR" => {
                Console.err.println("ur")
              y = (computeMove(y._1,y._2),y._2,y._1)
              x = (computeMove(x._1,x._3), x._1,x._3)
            }
            case "UL" => {
              y = (computeMove(y._1,y._2),y._2,y._1)
              x = (computeMove(x._1,x._2), x._2,x._1)
            }
            case "DR" => {
                Console.err.println("dr")
              y = (computeMove(y._1,y._3),y._1,y._3)
                  x = (computeMove(x._1,x._3), x._1,x._3)
            }
            case "DL" => {
              y = (computeMove(y._1,y._3),y._1,y._3)
              x = (computeMove(x._1,x._2), x._2,x._1)
            }
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