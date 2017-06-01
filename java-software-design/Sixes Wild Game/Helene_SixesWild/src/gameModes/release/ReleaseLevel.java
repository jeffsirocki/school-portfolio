package gameModes.release;

import gameModes.elimination.EliminationLevel;
import gameModes.puzzle.PuzzleParameterPanel;

import java.awt.Color;
import java.io.Serializable;
import java.util.ArrayList;

import builder.view.ParameterPanel;
import player.model.AbstractGame;
import common.model.AbstractLevel;
import common.model.AbstractSquare;
import common.model.EmptySquare;
import common.model.ICopy;
import common.model.Square;

/**
 * Foundation that contains elements of a release level.
 * @author rlamsal-jdoc
 * @author njpanzarino
 * @author tjbennett
 */
public class ReleaseLevel extends AbstractLevel implements Serializable{
	
	/**
	 * Serializable id.
	 */
	private static final long serialVersionUID = -8303954416976988023L;
	
	/**
	 * maximum moves allowed.
	 */
	int maxMoves = 10;
	
	/**
	 * Constructor that takes in a level number.
	 * @param num
	 */
	public ReleaseLevel(Integer num) {
		super(num);
		
	}

	/**
	 * Constructor that takes in a level number.
	 * @param num
	 */
	public <T extends AbstractLevel> ReleaseLevel(T level) {
		super(level);
	}
	
	/**
	 * creates a copy of a release level.
	 */
	public ReleaseLevel makeCopy() {
		ReleaseLevel copy = new ReleaseLevel(this);
		copy.setMaxMoves(maxMoves);
		return copy;
	}
	
	/**
	 * makes a release game.
	 */
	@Override
	public ReleaseGame makeGame() {
		return new ReleaseGame(this);
	}
	
	/**
	 * Returns an ArrayList containing the classes of allowable squares
	 * These squares will appear in the builder and can be placed anywhere on the board
	 * @return
	 */
	public static ArrayList<Class<? extends AbstractSquare>> squareTypes(){
		ArrayList<Class<? extends AbstractSquare>> out=new ArrayList<Class<? extends AbstractSquare>>();
		out.add(EmptySquare.class);
		out.add(Square.class);
		out.add(ReleaseSquare.class);
		out.add(StartAsSixSquare.class);
		
		return out;
	}
	
	/**
	 * returns a string of the game type.
	 * @return
	 */
	public static String type() {
		return "Release";
	}
	
	/**
	 * gets maximum moves.
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
	 * gets a new release parameter panel.
	 * @return
	 */
	public static ParameterPanel getAdditionalParameterPanel(){
		return new ReleaseParameterPanel();
	}

	/**
	 * creates a copy of the level object.
	 */
	@Override
	public void copy(ICopy object)
	{
		super.copy(object);
		setMaxMoves(((ReleaseLevel)object).getMaxMoves());
	}
	
	/**
	 * gets a color for the level.
	 */
	@Override
	public Color getColor() {
		return new Color(153, 50, 204);
	}
}
