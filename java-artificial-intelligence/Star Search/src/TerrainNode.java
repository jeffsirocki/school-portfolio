import java.util.Collection;
import java.util.LinkedList;


public class TerrainNode extends AbstractNode<AgentState> {
	
	public TerrainNode(AbstractNode<AgentState> parent, int costFromStart,AgentState state) {
		super(parent, costFromStart, state);
	}

	@Override
	public Collection<AbstractNode<AgentState>> expand() {
		LinkedList<AbstractNode<AgentState>> children = new LinkedList<AbstractNode<AgentState>>();
		
		if(state.map.isGoal(state.position)||state.map.isCliff(state.position)){
			children.add(new TerminateAction().getChild(this));
			return children;
		}
		if(!state.bashing){
			//TODO: Add all actions besides forward
			//ex: children.add(Actions.Bash(this));
			children.add(new BashAction().getChild(this));
			children.add(new TurnRightAction().getChild(this));
			children.add(new TurnLeftAction().getChild(this));
			children.add(new DemolishAction().getChild(this));
		}
		children.add(new ForwardAction().getChild(this));
		return children;
	}

	@Override
	public float getHeuristic() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean isGoal() {
		return parent.state.map.isGoal(state.position)||parent.state.map.isCliff(state.position);
	}

}
