package gameModes.release;

import java.awt.Color;

import common.view.AbstractSquareDrawer;

/**
 * Contains elements to draw a release square.
 * @author rlamsal-jdoc
 *
 */
public class ReleaseSquareDrawer extends AbstractSquareDrawer<ReleaseSquare> {

	/**
	 * Constructor that takes in a release square.
	 * @param square
	 */
	public ReleaseSquareDrawer(ReleaseSquare square) {
		super(square);
		// TODO Auto-generated constructor stub
	}

	/**
	 * returns the text in the square. 
	 */
	@Override
	public String getText() {
		return "6";
	}

	/**
	 * gets the foreground color.
	 */
	@Override
	public Color getForeground() {
		return Color.WHITE;
	}

	/**
	 * gets the background color.
	 */
	@Override
	public Color getBackground() {
		return Color.BLACK;
	}

}
