
public class TurnLeftAction implements IAction<TerrainNode> {

	@Override
	public TerrainNode getChild(TerrainNode parent) {
		AgentState state = new AgentState(parent.state);
		int cost = parent.costFromStart;
		
		state.direction = state.direction.left();
		cost += Math.ceil(state.map.getComplexity(state.position)/3.0);
		
		return new TerrainNode(parent,cost,state);
	}

}
