
public class TerminateAction implements IAction<TerrainNode> {

	@Override
	public TerrainNode getChild(TerrainNode parent) {
		AgentState state = new AgentState(parent.state);
		int cost = parent.costFromStart;
		
		/* 
		 * Need to make this 200, so heuristic for finding goal remains always >0, 
		 * while keeping the behavior that jumping off a cliff is 200 points worse than finding the goal
		 */
		if(state.map.isCliff(state.position))
			cost+=200;
		
		return new TerrainNode(parent,cost,state);
	}

	
}
