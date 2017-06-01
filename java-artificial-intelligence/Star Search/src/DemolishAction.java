import java.awt.Point;


public class DemolishAction implements IAction<TerrainNode> {

	@Override
	public TerrainNode getChild(TerrainNode parent) {
		AgentState state = new AgentState(parent.state);
		int cost = parent.costFromStart;
		
		Demolish(state.map,state.position);
		
		cost += 4;
		
		return new TerrainNode(parent,cost,state);
	}

	private void Demolish(IMap map,Point position){
		for(int r=-1;r<=1;r++){
			for(int c=-1;c<=1;c++){
				if(!(r==0&&c==0)){
					if(map.getComplexity(position)!=null)
						map.setComplexity(position, 3);
				}
			}
		}
	}
}
