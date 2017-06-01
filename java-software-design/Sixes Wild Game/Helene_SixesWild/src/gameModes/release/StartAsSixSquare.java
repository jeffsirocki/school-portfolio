package gameModes.release;

import common.model.AbstractContainer;
import common.model.Square;
import common.model.Tile;

/**
 * A square that starts as a six. 
 * @author rlamsal-jdoc 
 * @author tjbennett
 *  @author njpanzarino
 */
public class StartAsSixSquare extends Square {

	/**
	 * Serializable id.
	 */
	private static final long serialVersionUID = 2549786961672948322L;

	/**
	 * Constructor that takes in a container.
	 * @param s
	 */
	public StartAsSixSquare(AbstractContainer s) {
		super(s);
	}

	/**
	 * Constructor that takes in a row and a column.
	 * @param row
	 * @param column
	 */
	public StartAsSixSquare(int row, int column) {
		super(row, column);
		initialize();
	}

	/**
	 * makes a copy of a StartAsSixSquare object.
	 */
	@Override
	public StartAsSixSquare makeCopy() {
		return new StartAsSixSquare(this);
	}

	/**
	 * sets the tile to be a six with bonus one. 
	 */
	@Override
	public void initialize() {
		super.initialize();
		
		this.setTile(new Tile(6, 1));
	}
	
	/**
	 * returns a string with the type of sqaure. 
	 */
	public static String type() {
		return "Six Square";
	}
}
