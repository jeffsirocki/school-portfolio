package gameModes.puzzle;

import java.awt.Color;
import java.io.Serializable;

import builder.view.ParameterPanel;

import common.model.AbstractLevel;
import common.model.ICopy;

/**
 * Foundation class for the elements in the puzzle level.
 * @author rlamsal-jdoc
 * @author njpanzarino
 * @author tjbennett
 */
public class PuzzleLevel extends AbstractLevel implements Serializable{
	
	/**
	 * Serializable id.
	 */
	private static final long serialVersionUID = -8303954416976988023L;
	
	/**
	 * maximum moves available.
	 */
	int maxMoves=10;
	
	/**
	 * Constructor that takes in the level number.
	 * @param num
	 */
	public PuzzleLevel(Integer num) {
		super(num);
	}

	/**
	 * Constructor that takes in a level.
	 * @param level
	 */
	public <T extends AbstractLevel> PuzzleLevel(T level) {
		super(level);
	}
	
	/**
	 * creates a copy of the puzzle level. 
	 */
	public PuzzleLevel makeCopy() {
		PuzzleLevel copy = new PuzzleLevel(this);
		copy.setMaxMoves(maxMoves);
		return copy;
	}

	/**
	 * makes a puzzle level. 
	 */
	@Override
	public PuzzleGame makeGame() {
		return new PuzzleGame(this);
	}	

	/**
	 * returns the string type of the level.
	 * @return
	 */
	public static String type() {
		return "Puzzle";
	}

	/**
	 * gets the maximum moves.
	 * @return
	 */
	public int getMaxMoves() {
		return maxMoves;
	}
	
	/**
	 * sets maximum moves.
	 * @param maxMoves
	 */
	public void setMaxMoves(int maxMoves) {
		this.maxMoves=maxMoves;
	}
	
	/**
	 * gets the puzzle parameter panel.
	 * @return
	 */
	public static ParameterPanel getAdditionalParameterPanel(){
		return new PuzzleParameterPanel();
	}

	/**
	 * creates a copy of the puzzle level object.
	 */
	@Override
	public void copy(ICopy object)
	{
		super.copy(object);
		setMaxMoves(((PuzzleLevel)object).getMaxMoves());
	}
	
	/**
	 * gets a color for the level.
	 */
	@Override
	public Color getColor() {
		return new Color(51, 153, 255);
	}
}
