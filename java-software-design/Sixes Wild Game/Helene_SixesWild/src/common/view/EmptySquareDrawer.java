package common.view;

import java.awt.Color;

import common.model.AbstractSquare;
import common.model.EmptySquare;

/**
 * Drawer for empty squares.
 * @author npanzarino
 * @author jasirocki - jdoc
 *
 */
public class EmptySquareDrawer extends AbstractSquareDrawer<EmptySquare> {

	/**
	 * Constructor for drawer takes in a empty.
	 * @param square
	 */
	public EmptySquareDrawer(EmptySquare square) {
		super(square);
	}

	/**
	 * Return empty text.
	 */
	@Override
	public String getText() {
		return "";
	}

	/**
	 * Foreground is white.
	 */
	@Override
	public Color getForeground() {
		return Color.WHITE;
	}

	/**
	 * Background is white.
	 */
	@Override
	public Color getBackground() {
		return Color.WHITE;
	}

	/**
	 * Border is white unless selected.
	 */
	@Override
	public Color getBorderColor() {
		if(square.isSelected()){
			return AbstractSquare.SELECTED_COLOR;
		}
		return Color.WHITE;
	}	
	
}
