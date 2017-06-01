package gameModes.elimination;

import common.model.AbstractContainer;
import common.model.Square;

/**
 * Foundation class that contains elements about an elimination square.
 * @author rlamsal-jdoc
 *
 */
public class EliminationSquare extends Square {
	
	protected boolean marked=false;
	
	/**
	 * Constructor that takes in the row and column of an elimination square.
	 * @param row
	 * @param column
	 */
	public EliminationSquare(int row, int column) {
		super(row, column);
		marked=true;
	}

	/**
	 * Constructor that takes in a container.
	 * @param s
	 */
	public EliminationSquare(AbstractContainer s) {
		super(s);
		marked=true;
	}

	/**
	 * gets the elimination square drawer.
	 */
	@Override
	public EliminationSquareDrawer getDrawer(){
		return new EliminationSquareDrawer(this);
	}

	/**
	 * updates to show if a square is eliminated.
	 */
	@Override
	public boolean update() {
		Boolean b=super.update();
		if(b==true)
			setMarked(true);
		return b;
	}
	
	/**
	 * initializes square to be not eliminated yet.
	 */
	@Override
	public void initialize() {
		super.initialize();
		setMarked(false);
	}
	
	/**
	 * marks the square.
	 * @param marked
	 */
	public void setMarked(boolean marked) {
		this.marked=marked;
	}

	/**
	 * checks if a square is marked or not.
	 * @return
	 */
	public boolean isMarked() {
		return marked;
	}

	/**
	 * makes a copy of the square.
	 */
	@Override
	public EliminationSquare makeCopy() {
		EliminationSquare copy = new EliminationSquare(this);
		copy.setMarked(marked);
		return copy;
	}
	
	/**
	 * returns the type of sqaure in string.
	 * @return
	 */
	public static String type() {
		return "Elimination Square";
	}
	
}
