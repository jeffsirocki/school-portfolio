import java.awt.Point;


public class AgentState {
	
	public IMap map;
	public Point position;
	public Direction direction;
	public boolean bashing;

	public AgentState(IMap map, Point position,Direction direction,boolean bashing){
		this.map = map;
		this.position = position;
		this.direction = direction;
		this.bashing = bashing;
	}
	
	public AgentState(AgentState prevState){
		this.map = prevState.map.copy();
		this.position = new Point(prevState.position.x,prevState.position.y);
		this.direction = prevState.direction;
		this.bashing = prevState.bashing;
	}
}
