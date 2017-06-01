import java.util.Collection;
import java.util.PriorityQueue;

public class AStar {

	static long nodesExpanded = 0;
	
	private AStar(){};
	
	@SuppressWarnings("unchecked")
	public static <T extends AbstractNode<?>> AbstractNode<?> Search(T start){
		nodesExpanded = 0;
		PriorityQueue<T> fringe = new PriorityQueue<T>(50,new NodeEvaluationComparator());
		fringe.add(start);
		
		while(!fringe.isEmpty()){
			AbstractNode<?> node = fringe.poll();
			if(node.isGoal()){return node;};
			fringe.addAll((Collection<? extends T>)node.expand());
			nodesExpanded++;
		}
		return null;
	}
	
	// MAIN
	public static void main (String args[]) {
		Map map = new Map(args[0]);
		int heuristic = Integer.valueOf(args[1]);
		
		
	}
	
	
}
