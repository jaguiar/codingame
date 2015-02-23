import scala.collection.mutable.HashMap
import scala.collection.mutable.Map
import scala.collection.mutable.Queue
import scala.collection.parallel.mutable.ParHashSet
import scala.collection.parallel.mutable.ParSet
/**
 * Auto-generated code below aims at helping you parse
 * the standard input according to the problem statement.
 */
object Solution2 {
  
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
    Console.err.println("before partition "+ System.currentTimeMillis)
    val (nodes, leafs) = relationsMap.partition(item => item._2.size > 1);
    Console.err.println("after partition "+ System.currentTimeMillis)
  /*  if (leafs.size > nodes.size) {
      println(searchDepthByNodes(relationsMap, nodes.keySet, leafs.keySet))
    } else {*/
      println(searchDepthByLeafs(leafs.keySet, nodes))
  //  }
                  //val orderedRelations = relationsMap.toSeq.sortBy(_._2.size)(Ordering[Int].reverse)

                  // The minimal amount of steps required to completely propagate the advertisement
  }

  /*def searchDepthByLeafs(relationsMap: ParHashMap[Int, ParSet[Int]], leafs: scala.collection.parallel.ParSet[Int]): Int = {
      val maxDistance = leafs.reduceLeft((leaf1, leaf2) => {
        val val1 = searchPath(relationsMap, leaf1, leafs-leaf1, 0, new ParHashSet)
            val val2 = searchPath(relationsMap, leaf2, leafs-leaf2, 0, new ParHashSet)
            Math.max(val1, val2)
      })
      ((maxDistance: Double) / 2).ceil.toInt
  }*/
  def searchDepthByLeafs(/*leaf:Int, */ leafs: scala.collection.Set[Int], nodes: Map[Int, ParSet[Int]]): Int = {
    val mostDistantLeaf = searchPath(leafs.head, nodes.withDefaultValue(ParSet.empty))
    Console.err.println("mostDistantLeaf="+mostDistantLeaf+" "+ System.currentTimeMillis)
    val maxDistance = searchDepth(mostDistantLeaf, 0, new ParHashSet)
    //val maxDistance = searchDepth(mostDistantLeaf, nodes.withDefaultValue(ParSet.empty))
    Console.err.println("maxDistance="+maxDistance)
    ((maxDistance: Double) / 2).ceil.toInt
  }

    def searchDepth(from: Int, depth: Int, visitedNodes: ParSet[Int]): Int = {
      val children = relationsMap(from).filterNot(node => visitedNodes.contains(node));
      children.size match {
        case 0 => depth
        //case 1 => searchDepth(children.head, 1 + depth, visitedNodes)
          case _ =>
/*        children.reduce((child1, child2) => { 
          Math.max(
          searchDepth(child1, 1 + depth, visitedNodes),
          searchDepth(child2, 1 + depth, visitedNodes)
          )
        })*/
        children.foldLeft(0) { (currentMax, child) => {
              Math.max(
                currentMax,
                searchDepth(child, 1 + depth, visitedNodes+=from)
              )
        }}
 /*           children.maxBy { 
              child => 
                searchDepth(child, 1 + depth, visitedNodes) 
            }*/
      }
  }
    
 /* def searchDepth(from: Int, nodesMap: Map[Int, ParSet[Int]]): Int = {
    val nodesToVisit = new Queue[Int]
    nodesToVisit ++=relationsMap(from).seq;
    val visitedNodes = new ParHashSet[Int]
    visitedNodes+=from
    val time = System.currentTimeMillis
    var currentdepth = 1
    var nbNodesToChangeLevel = nodesToVisit.size
    while (nodesToVisit.nonEmpty){
      val current = nodesToVisit.dequeue()
      visitedNodes += current
      nbNodesToChangeLevel -=1
      if(nbNodesToChangeLevel == 0){
        currentdepth +=1
      }
      val children = (nodesMap(current) diff visitedNodes).seq 
      nodesToVisit ++= children // add not visited children
      nbNodesToChangeLevel += children.size
    }
    currentdepth+1
  }*/
  
  def searchPath(from: Int, nodesMap: Map[Int, ParSet[Int]]): Int = {
    val nodesToVisit = new Queue[Int]
    nodesToVisit.enqueue(from);
    val visitedNodes = new ParHashSet[Int] 
    val time = System.currentTimeMillis
    var last = from
    while (nodesToVisit.nonEmpty){
      last = nodesToVisit.dequeue()
      visitedNodes += last
      nodesToVisit ++= (nodesMap(last) diff visitedNodes).seq // add not visited children
    }
    last
  }
  
  /*def searchDepth(relationsMap: ParHashMap[Int, ParSet[Int]], node : Int, depth: Int, visitedNodes: ParSet[Int]):Int = {
      visitedNodes += node
      val children = relationsMap(node).diff(visitedNodes);
      children.size match {
        case 0 => depth
        case 1 => searchDepth(relationsMap, children.head, 1 + depth, visitedNodes)
        case _ =>
        children.reduce((child1, child2) => { 
          val val1 = searchDepth(relationsMap, child1, 1 + depth, visitedNodes)
          val val2 = searchDepth(relationsMap, child2, 1 + depth, visitedNodes)
          val1.max(val2)
        })
      }
  }*/

  //  def searchDepth(parent: (Int, Set[Int]), sortedNodesByRelationsCount: Seq[(Int, Set[Int])], visitedNodes: HashSet[Int], depth: Int): Int = {
  //    visitedNodes += parent._1 //add this node
  //    Console.err.println("visited nodes..." + visitedNodes + "depth =" + depth)
  //    var max = depth;
  //    parent._2.filter(item => !visitedNodes.contains(item)).foreach(child => {
  //      var subDepth = searchDepth((child, relationsMap(child)), sortedNodesByRelationsCount, visitedNodes, 1 + depth)
  //      Console.err.println("subdepth..." + subDepth)
  //      if (subDepth > max) {
  //        max = subDepth
  //      }
  //    })
  //    max;
  //  }
  
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
