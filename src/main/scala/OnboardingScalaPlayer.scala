import math._
import scala.util._

/**
 * Instructions at http://www.codingame.com/ide/1079529ef1154e59c21f6da6d732d9622e9f0fc
 * The code below will read all the game information for you.
 * On each game turn, information will be available on the standard input, you will be sent:
 * -> the total number of visible enemies
 * -> for each enemy, its name and distance from you
 * The system will wait for you to write an enemy name on the standard output.
 * Once you have designated a target:
 * -> the cannon will shoot
 * -> the enemies will move
 * -> new info will be available for you to read on the standard input.
 **/
object Player {

    def main(args: Array[String]) {

        // game loop
        while(true) {
            val count = readInt // The number of current enemy ships within range
            var minDist = ("",Int.MaxValue)
            for(i <- 0 until count) {
                // enemy: The name of this enemy
                // dist: The distance to your cannon of this enemy
                val Array(enemy, _dist) = readLine split " "
                if(_dist.toInt < minDist._2){
                    minDist = (enemy, _dist.toInt)
                }
            }
            
            println(minDist._1) // The name of the most threatening enemy
        }
    }
}
