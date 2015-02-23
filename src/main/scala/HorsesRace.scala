import math._
import scala.util._
import scala.collection.mutable.TreeSet

/**
 * Auto-generated code below aims at helping you parse
 * the standard input according to the problem statement.
 **/
object SolutionHorses {

    def main(args: Array[String]) {
		val n = readInt
		val powersSet = new TreeSet[Int]()
    var continue = true
		for(i <- 0 until n) {
		  val pi = readInt
				if(powersSet(pi)){
          println(0)
          continue = false
					sys.exit()
				}
				powersSet += pi
		}

    var minDist = Int.MaxValue
    if(continue){
    powersSet.toSeq.sliding(2).foreach { case Seq(val1,val2) => val dist = (val2-val1).abs 
      if(dist < minDist) minDist= dist
      }
    }
		 

		// Write an action using println
		// To debug: Console.err.println("Debug messages...")

		println(minDist)
	}
}