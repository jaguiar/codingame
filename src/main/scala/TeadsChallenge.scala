import scala.collection.mutable.HashMap
import scala.collection.mutable.Map
import scala.collection.mutable.Queue
import scala.collection.parallel.mutable.ParHashSet
import scala.collection.parallel.mutable.ParSet
import scala.collection.Set;
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
		val (nodes, leafs) = relationsMap.partition(item => item._2.size > 1) //=> a node has at least 2 relations, a leaf has just one
		println( // The minimal amount of steps required to completely propagate the advertisement
				new TreeDepthComputer(
            leafs.keySet, 
            nodes.withDefaultValue(ParSet.empty)).searchDepthByLeafs() // defaultValue empty => we will work with nodes map since it smaller than the whole relations map
				)
	}

	class TreeDepthComputer(leafs: Set[Int], nodesMap: Map[Int, ParSet[Int]]){

		def searchDepthByLeafs(): Int = {
			val mostDistantLeaf = searchLongestPath(
					relationsMap(leafs.head).head //"optimization" we will begin at the node just before the "chosen leaf" (aka the first one)
			)

			//val maxDistance = findDiameter(mostDistantLeaf, new ParHashSet, 0) // simple version to compute the tree diameter

			// "optimized" version
			val maxDistance = 
            2 + //we add "2" because we just want to traverse the "inner tree" (aka without leafs) and we know that the diameter will just include two more leafs
            findInnerTreeDiameter( 
								relationsMap(mostDistantLeaf).head, // the node just "before" the farthest leaf found
								new ParHashSet++=leafs, // we add all leafs to already visited elements
								0)// initial depth
								
      // we know that the tree depth is the tree diameter divided by 2
			((maxDistance: Double) / 2).ceil.toInt
		}

		/* CONCURRENT DFS that use the whole relationsMap */
		private def findDiameter(from: Int, visitedElements: ParSet[Int], depth: Int): Int = {
			val children = relationsMap(from).filterNot(node => visitedElements.contains(node))
			children.size match {
					case 0 => depth
					case _ =>
			  		children.foldLeft(0) { (currentMax, child) => {
						  Math.max(
								currentMax,
								findDiameter(child, visitedElements+=from, 1 + depth)
							)
						}}
				}
		}

		// CONCURRENT DFS that only uses the nodesMap, we begin with the first node linked to the farthest leaf found, and we will find the farthest "node" from (aka the one before the farthest leaf)
		//so that we can compute the tree diameter
		private def findInnerTreeDiameter(from: Int, visitedElements: ParSet[Int], depth: Int): Int = {
			val children = nodesMap(from).filterNot(node => visitedElements.contains(node));
			children.size match {
			  case 0 => depth
				case _ =>
				  children.foldLeft(0) { (currentMax, child) => {
					  Math.max(
							currentMax,
							findInnerTreeDiameter(child, visitedElements+=from, 1 + depth)
						)
				  }}
				}
		}
    
		// BFS to look for the "last" leaf (aka the farthest one from the given "from" node)
    // "optimization" we just need the nodes relations (a node has at least 2 relations aka is not a leaf)
		private def searchLongestPath(from: Int): Int = {
		  val elementsToVisit = new Queue[Int]
			elementsToVisit.enqueue(from);
			val visitedElements = new ParHashSet[Int] 
			var last = from
			while (elementsToVisit.nonEmpty){
				last = elementsToVisit.dequeue()
				visitedElements += last
				elementsToVisit ++= (nodesMap(last) filterNot (node => visitedElements.contains(node))).seq // add not visited children
			}
			last
		}
	}
  
	// utils
	private def addBinding(map : HashMap[Int, ParSet[Int]], key: Int, value: Int) = {
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
