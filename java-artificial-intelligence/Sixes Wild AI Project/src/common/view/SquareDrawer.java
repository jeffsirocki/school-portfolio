package common.view;

import java.awt.Color;

import common.model.Square;

/**
 * Object that knows how to draw each regular square.
 * @author npanzarino
 * @author jasirocki - jdoc
 *
 * @param <T>
 */
public class SquareDrawer<T extends Square> extends AbstractSquareDrawer<T> {

	/**
	 * Constructor for drawer takes in a square.
	 * @param square
	 */
	public SquareDrawer(T square){
		super(square);
	}

	/**
	 * Gets tile bonus value.
	 */
	@Override
	public String getSubText() {
		if(square.hasTile()){
			int b=square.peek().getBonus();
			if(b!=1){
				return "x"+b;
			}
		}
		return "";
	}

	/**
	 * Gets the tile value.
	 */
	@Override
	public String getText() {
		if(square.hasTile())
			return String.valueOf(square.peek().getVal());
		return "";
	}

	/**
	 * Foreground is black.
	 */
	@Override
	public Color getForeground() {
		return Color.BLACK;
	}

	/**
	 * Background depends on tile value.
	 */
	@Override
	public Color getBackground() {
		if(square.hasTile())
			return Square.SQUARE_COLOR[square.peek().getVal()];
		return Color.LIGHT_GRAY;
	}
	
}
