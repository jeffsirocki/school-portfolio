package gameModes.elimination;

import java.awt.Color;
import java.io.Serializable;
import java.util.ArrayList;
import builder.view.ParameterPanel;
import player.model.AbstractGame;
import common.model.AbstractLevel;
import common.model.AbstractSquare;
import common.model.EmptySquare;
import common.model.ICopy;

/**
 * Foundation class for the elements in the elimination level.
 * @author rlamsal-jdoc
 *
 */
public class EliminationLevel extends AbstractLevel implements Serializable{
	
	/**
	 * default maximum moves that the player can use.
	 */
	int maxMoves = 10;
	
	/**
	 * serializable id.
	 */
	private static final long serialVersionUID = -8303954416976988023L;
	
	/**
	 * Constructor that takes in the level number.
	 * @param num
	 */
	public EliminationLevel(Integer num) {
		super(num);
		getParams().getBoard().setDefaultType(EliminationSquare.class);
		getParams().updateRef();
		getParams().getBoard().initialize();
	}

	/**
	 * Make a game with elimination level.
	 */
	@Override
	public AbstractGame makeGame() {
		return new EliminationGame(this);
	}

	/**
	 * Constructor that takes in a level.
	 * @param level
	 */
	public <T extends AbstractLevel> EliminationLevel(T level) {
		super(level);
	}

	/**
	 * returns the type of level.
	 * @return
	 */
	public static String type() {
		return "Elimination";
	}
	
	/**
	 * creates a copy of the level.
	 */
	public EliminationLevel makeCopy() {
		EliminationLevel copy = new EliminationLevel(this);
		copy.setMaxMoves(maxMoves);
		return copy;
	}
	
	/**
	 * Returns an ArrayList containing the classes of allowable squares.
	 * These squares will appear in the builder and can be placed anywhere on the board.
	 * @return
	 */
	public static ArrayList<Class<? extends AbstractSquare>> squareTypes(){
		ArrayList<Class<? extends AbstractSquare>> out=new ArrayList<Class<? extends AbstractSquare>>();
		out.add(EmptySquare.class);
		out.add(EliminationSquare.class);
		
		return out;
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
	 * gets the additional parameter panel. In this case, it's an elimination parameter panel.
	 * @return
	 */
	public static ParameterPanel getAdditionalParameterPanel(){
		return new EliminationParameterPanel();
	}
	
	/**
	 * creates a copy of the level.
	 */
	@Override
	public void copy(ICopy object)
	{
		super.copy(object);
		setMaxMoves(((EliminationLevel)object).getMaxMoves());
	}
	
	/**
	 * gets the color of the level.
	 */
	@Override
	public Color getColor() {
		return new Color(255, 120, 0);
	}
}
