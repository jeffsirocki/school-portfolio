import java.awt.Point;


public class ForwardAction implements IAction<TerrainNode> {

	@Override
	public TerrainNode getChild(TerrainNode parent) {
		
		AgentState state = new AgentState(parent.state);
		int cost = parent.costFromStart;
		
		state.position = new Point(state.position.x+state.direction.toPoint().x,state.position.y+state.direction.toPoint().y);
		state.bashing = false;
		cost += state.map.getComplexity(state.position);
		
		return new TerrainNode(parent,cost,state);
	}


}
