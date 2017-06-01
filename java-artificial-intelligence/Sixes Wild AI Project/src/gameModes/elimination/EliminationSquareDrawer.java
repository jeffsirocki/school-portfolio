package gameModes.elimination;

import common.view.SquareDrawer;

/**
 * Objects that know how to draw elimination squares.
 * @author rlamsal-jdoc
 *
 */
public class EliminationSquareDrawer extends SquareDrawer<EliminationSquare> {

	/**
	 * Constructor that takes in a elimination square object.
	 * @param square
	 */
	public EliminationSquareDrawer(EliminationSquare square) {
		super(square);
	}

	/**
	 * checks and returns the number of eliminated squares.
	 */
	@Override
	public int getNumBorders() {
		int out=1;
		if(square.isMarked())
			out++;
		return out;
	}
	
}
