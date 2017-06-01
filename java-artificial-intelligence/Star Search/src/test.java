import java.awt.Point;

enum Direction{
	NORTH,
	EAST,
	SOUTH,
	WEST;
	
	private Point north = new Point(0,-1) ;
	private Point south = new Point(0,1) ;
	private Point east = new Point(1,0) ;
	private Point west = new Point(-1,0) ;
	
	public Point toPoint(){
		switch(this){
		case NORTH:
			return north;
		case SOUTH:
			return south;
		case EAST:
			return east; 
		case WEST:
			return west;
		default:
			return null;
		}
	}
	
	public Direction right(){
		switch(this){
		case NORTH:
			return EAST;
		case SOUTH:
			return WEST;
		case EAST:
			return SOUTH; 
		case WEST:
			return NORTH;
		default:
			return null;
		}
	}
	public Direction left(){
		switch(this){
		case NORTH:
			return WEST;
		case SOUTH:
			return EAST;
		case EAST:
			return NORTH; 
		case WEST:
			return SOUTH;
		default:
			return null;
		}
	}
}

public class test {
	
	public void apple(){
		
		Point p = Direction.NORTH.toPoint();
		p.translate(Direction.EAST.toPoint().x, Direction.EAST.toPoint().y);
		
	}
}
