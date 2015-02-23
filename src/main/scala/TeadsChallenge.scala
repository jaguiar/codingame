import scala.collection.mutable.HashMap
import scala.collection.mutable.Map
import scala.collection.mutable.Queue
import scala.collection.parallel.mutable.ParHashSet
import scala.collection.parallel.mutable.ParSet
/**
 * Auto-generated code below aims at helping you parse
 * the standard input according to the problem statement.
 */
object TeadsSolution {
  
  val relationsMap = new HashMap[Int, ParSet[Int]]
	
  def main(args: Array[String]) {
		val n = readInt // the number of adjacency relations
		for (i <- 0 until n) {
			// xi: the ID of a person which is adjacent to yi
			// yi: the ID of a person which is adjacent to xi
			val Array(xi, yi) = for (i <- readLine split " ") yield i.toInt
            addBinding(relationsMap,xi, yi)
             addBinding(relationsMap,yi, xi)
		}
		val (nodes, leafs) = relationsMap.partition(item => item._2.size > 1);
		println(searchDepthByLeafs(leafs.keySet, nodes))
		// The minimal amount of steps required to completely propagate the advertisement
	}

	def searchDepthByLeafs(leafs: scala.collection.Set[Int], nodes: Map[Int, ParSet[Int]]): Int = {
		val mostDistantLeaf = searchPath(leafs.head, nodes.withDefaultValue(ParSet.empty))
		val maxDistance = searchDepth(mostDistantLeaf, 0, new ParHashSet)
		((maxDistance: Double) / 2).ceil.toInt
	}

    def searchDepth(from: Int, depth: Int, visitedNodes: ParSet[Int]): Int = {
			val children = relationsMap(from).filterNot(node => visitedNodes.contains(node));
			children.size match {
				case 0 => depth
			    case _ =>
        children.foldLeft(0) { (currentMax, child) => {
              Math.max(
                currentMax,
                searchDepth(child, 1 + depth, visitedNodes+=from)
              )
        }}
	}
	}
    
	def searchPath(from: Int, nodesMap: Map[Int, ParSet[Int]]): Int = {
    val nodesToVisit = new Queue[Int]
    nodesToVisit.enqueue(from);
    val visitedNodes = new ParHashSet[Int] 
    var last = from
    while (nodesToVisit.nonEmpty){
      last = nodesToVisit.dequeue()
    	visitedNodes += last
      nodesToVisit ++= (nodesMap(last) diff visitedNodes).seq // add not visited children
    }
    last
	}
	
    // utils
  def addBinding(map : HashMap[Int, ParSet[Int]], key: Int, value: Int) = {
    map.get(key) match {
    case None =>
    val set = new ParHashSet[Int]
        set += value
        map(key) = set
    case Some(set) =>
    set += value
    }
  }

}
