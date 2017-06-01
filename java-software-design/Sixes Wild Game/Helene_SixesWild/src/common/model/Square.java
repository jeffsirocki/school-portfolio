package common.model;

import java.io.Serializable;

import common.view.AbstractSquareDrawer;
import common.view.SquareDrawer;

/**
 * Square contains whether it is selected is a regular square that can hold tiles.
 * @author njpanzarino
 * @author jasirocki - jdoc
 *
 */
public class Square extends AbstractContainer implements ISource, Serializable{
	
	/**
	 * Serializable ID.
	 */
	private static final long serialVersionUID = -8303954416976988023L;
	
	/**
	 * Selected flag.
	 */
	Boolean selected=false;
	
	/**
	 * Constructor for square given (row,column).
	 * @param row
	 * @param column
	 */
	public Square(int row, int column) {
		super(row, column);
	}

	/**
	 * Constructor for Square given an abstract container.
	 * @param s
	 */
	public Square(AbstractContainer s) {
		super(s);
	}

	/**
	 * Take tile and put it in square.
	 */
	@Override
	public Tile takeTile() {
		if(tile==null)
			update();
		
		Tile t=remove();
		tile=fillFrom.takeTile();
			
		return t;
	}

	/**
	 * Move tile out of square.
	 */
	@Override
	public void push(Tile tile) {
		if(tile==null)
			this.tile=tile;
		else{
			Tile t=remove();
			fillFrom.push(t);
			this.tile=tile;
		}
	}

	/**
	 * Return a string representation of the square.
	 */
	@Override
	public String toString() {
		if(tile==null)
			return " ";
		return String.valueOf(tile.getVal());
	}

	/**
	 * Return the drawer for the square.
	 */
	@Override
	public SquareDrawer getDrawer() {
		return new SquareDrawer<Square>(this);
	}

	/**
	 * Copy the square without references.
	 */
	public Square makeCopy() {
		return new Square(this);
	}

	/**
	 * Return the type of square.
	 * @return type
	 */
	public static String type() {
		return "Square";
	}

	/**
	 * Copy the square.
	 */
	@Override
	public void copy(ICopy object)
	{
		
	}
}
