package common.model;

import common.view.AbstractSquareDrawer;
import common.view.EmptySquareDrawer;

/**
 * Empty square in the board. 
 * @author njpanzarino
 * @author jasirocki - jdoc
 *
 */
public class EmptySquare extends AbstractSquare{

	/**
	 * Constructor for empty square given (row,column).
	 * @param row
	 * @param column
	 */
	public EmptySquare(int row, int column) {
		super(row, column);
	}

	/**
	 * Constructor for empty square given an AbstractSquare.
	 * @param s
	 */
	public EmptySquare(AbstractSquare s) {
		super(s);
	}

	/**
	 * Update method.
	 */
	@Override
	public boolean update() {
		return false;
	}

	/**
	 * Get empty square drawer.
	 */
	@Override
	public EmptySquareDrawer getDrawer() {
		return new EmptySquareDrawer(this);
	}
	
	/**
	 * Copy empty square.
	 */
	@Override
	public EmptySquare makeCopy() {
		return new EmptySquare(this);
	}

	/**
	 * Get square type.
	 * @return String
	 */
	public static String type() {
		return "Empty Square";
	}

	/**
	 * Return empty string for empty square.
	 */
	@Override
	public String toString() {
		return " ";
	}

	/**
	 * Copy object.
	 */
	@Override
	public <T extends ICopy> void copy(T object) {
		// TODO Auto-generated method stub
		
	}
}
