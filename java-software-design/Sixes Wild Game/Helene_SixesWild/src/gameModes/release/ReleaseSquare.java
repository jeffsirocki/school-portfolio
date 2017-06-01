package gameModes.release;

import common.model.AbstractContainer;
import common.model.AbstractSquare;
import common.model.ICopy;
import common.model.Tile;
import common.view.AbstractSquareDrawer;

/**
 * Class that contains elements of a release square.
 * Release square is a square that can take in a six.
 * @author rlamsal-jdoc
 *
 */
public class ReleaseSquare extends AbstractContainer {

	/**
	 * Constructor that takes in a row and a column. 
	 * @param row
	 * @param column
	 */
	public ReleaseSquare(int row, int column) {
		super(row, column);
	}

	/**
	 * Constructor that takes in a container. 
	 * @param s
	 */
	public ReleaseSquare(AbstractContainer s) {
		super(s);
	}

	/**
	 * takes in a six tile. Every other tiles work as it should. 
	 */
	@Override
	public boolean update() {
		//Tile t=getFillFrom().peek();
		Tile t=getFillFrom().takeTile();
		if(t.getVal()==6){
			update();
			return true;
		}
		else{
			getFillFrom().push(t);
		}
		return false;
	}

	/**
	 * creates a copy of a release square.
	 */
	@Override
	public ReleaseSquare makeCopy() {
		return new ReleaseSquare(this);
	}

	/**
	 * gets a release square drawer.
	 */
	@Override
	public ReleaseSquareDrawer getDrawer() {
		return new ReleaseSquareDrawer(this);
	}
	
	/**
	 * returns a string of the square type.
	 * @return
	 */
	public static String type() {
		return "Release Square";
	}

	/**
	 * doesn't implement anything. 
	 */
	public void copy(ICopy object)
	{
		
	}
}
