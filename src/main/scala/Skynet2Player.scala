
import scala.collection.mutable.MultiMap
import scala.collection.mutable.Set
import scala.collection.mutable.HashMap
import scala.collection.mutable.HashSet
import scala.collection.mutable.Queue

/**
 * http://www.codingame.com/ide/107952604253317df996a69310cf87ffbe6bae3
 **/
object SkyNet2Player {

    val networkMap = new HashMap[Int, Set[(Int,Int)]] with MultiMap[Int,(Int,Int)]
 		val exitSet = new HashSet[Int]
    
    def main(args: Array[String]) {
        // n: the total number of nodes in the level, including the gateways
        // l: the number of links
        // e: the number of exit gateways
        val Array(n, l, e) = for(i <- readLine split " ") yield i.toInt
        for(i <- 0 until l) {
            // n1: N1 and N2 defines a link between these nodes
            val Array(n1, n2) = for(i <- readLine split " ") yield i.toInt
            networkMap.addBinding(n1, (n1,n2))
            networkMap.addBinding(n2, (n1,n2))
        }
        for(i <- 0 until e) {
            val ei = readInt // the index of a gateway node
            exitSet.add(ei)
        }

        // game loop
        while(true) {
            val si = readInt // The index of the node on which the Skynet agent is positioned this turn
            
            // Write an action using println
            // To debug: Console.err.println("Debug messages...")
            val (n1,n2) = findClosestExitLink(si)
            println(s"$n1 $n2") // Example: 0 1 are the indices of the nodes you wish to sever the link between
        }
    }
    
    def findClosestExitLink(agentPosition: Int): (Int,Int) = {
      val linksToVisit = new Queue[(Int, Int)]
      linksToVisit++=networkMap(agentPosition);
      val visitedElements = new HashSet[(Int,Int)] 
      var current:(Int,Int)=null;
      do{
        current = linksToVisit.dequeue()
        visitedElements += current
        linksToVisit ++= networkMap(current._2) filterNot (node => visitedElements.contains(node)) // add not visited children
      } while (!exitSet.contains(current._1) && !exitSet.contains(current._2)  && linksToVisit.nonEmpty)
      current
    }
}
