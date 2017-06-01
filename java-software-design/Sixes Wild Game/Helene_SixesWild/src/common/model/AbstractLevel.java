package common.model;

import java.awt.Color;
import java.awt.Point;
import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;

import builder.view.ParameterPanel;
import player.model.AbstractGame;
import player.model.RemoveMove;
import player.model.ShuffleMove;
import player.model.Swap2Move;

/**
 * Defines a framework for all Level objects, which can be built and played.
 * Level objects contain information that can be stored on disk and loaded.
 * @author Nicholas Panzarino
 * @author jasirocki - jdoc
 */
public abstract class AbstractLevel extends AbstractType implements Comparable<AbstractLevel>, ICopy, Serializable{
	
	/**
	 * Serializable ID.
	 */
	private static final long serialVersionUID = -8303954416976988023L;
	
	/**
	 * Level parameters used to load level.
	 */
	protected LevelParameters params;
	
	/**
	 * High score from previous playthrough.
	 */
	protected int highscore=0;
	
	/**
	 * Boolean whether level unlocked.
	 */
	protected boolean unlocked=false;
	
	/**
	 * Level ID.
	 */
	protected int levelID;
	
	/**
	 * Constructor for level given level number/id.
	 * @param num
	 */
	public AbstractLevel(Integer num){
		params=new LevelParameters();
		//default all special moves allowed
		params.setMoves(AbstractLevel.getMoveTypes(this.getClass()));
		highscore=0;
		unlocked=false;
		levelID=num;
		setDefaultReferences(ReferenceMap.FROM_UP);
	}
	
	/**
	 * Constructor for level given abstract level.
	 * @param level
	 */
	public <T extends AbstractLevel> AbstractLevel(T level){
		params=level.getParams().makeCopy();
		highscore=level.getHighscore();
		unlocked=level.isUnlocked();
		levelID=level.getLevelID();
		//setDefaultReferences(level.getParams().getRef().getDefaultFill());
	}
	
	/**
	 * Set the default fill from references for a level.
	 * @param defaultFill
	 */
	public void setDefaultReferences(int[] defaultFill){
		params.getRef().setSize(params.board.getSize());
		params.getRef().setDefaultFill(defaultFill);
		
		for(Point p:params.getRef()){
			params.getRef().setRef(p,params.getRef().getDefaultFillPoint(p));
		}
		
		params.setRef(params.getRef());
		params.getBoard().update();
	}
	
	/**
	 * Make a game with the level.
	 * @return AbstractGame
	 */
	abstract public AbstractGame makeGame();
	
	/**
	 * Copy method without references.
	 */
	public abstract AbstractLevel makeCopy();
	
	/**
	 * Get level ID.
	 * @return levelID
	 */
	public int getLevelID() {
		return levelID;
	}

	/**
	 * Set level ID.
	 * @param levelID
	 */
	public void setLevelID(int levelID) {
		this.levelID = levelID;
	}

	/**
	 * Get Level Parameters.
	 * @return LevelParameters
	 */
	public LevelParameters getParams() {
		return params;
	}

	/**
	 * Set level parameters.
	 * @param params
	 */
	public void setParams(LevelParameters params) {
		this.params = params;
	}

	/**
	 * Get highscore.
	 * @return highscore
	 */
	public int getHighscore() {
		return highscore;
	}

	/**
	 * Set highscore.
	 * @param highscore
	 */
	public void setHighscore(int highscore) {
		this.highscore = highscore;
	}

	/**
	 * Get unlocked flag.
	 * @return unlocked
	 */
	public boolean isUnlocked() {
		return unlocked;
	}

	/**
	 * Set unlocked flag.
	 * @param unlocked
	 */
	public void setUnlocked(boolean unlocked) {
		this.unlocked = unlocked;
	}
	
	/**
	 * Static Method that Determines the String value of this type of level.
	 * This will be displayed in ComboBoxes.
	 * All subclasses should overwrite/hide this method.
	 * @return
	 */
	public static String type(){
		throw new IllegalArgumentException("A Subclass of AbstractLevel does not declare a 'public static String type()' Method.");
	}
	
	/**
	 * Returns an ArrayList containing the classes of allowable squares.
	 * These squares will appear in the builder and can be placed anywhere on the board.
	 * The default allowable types are: EmptySquares and Squares.
	 * A level class which extends this one can change the default by overriding/hiding this method.
	 * @return an ArrayList containing the classes of allowable squares
	 */
	public static ArrayList<Class<? extends AbstractSquare>> squareTypes(){
		ArrayList<Class<? extends AbstractSquare>> out=new ArrayList<Class<? extends AbstractSquare>>();
		out.add(EmptySquare.class);
		out.add(Square.class);
		
		return out;
	}
	
	/**
	 * Returns a list of all possible special moves for the input level class.
	 * Used to determine the special moves of an unknown level when it is known as a parameter during runtime.
	 * @param c some class which extends abstract level
	 * @return an ArrayList containing the classes of allowable squares
	 */
	@SuppressWarnings("unchecked")
	public static ArrayList<Class<? extends AbstractSquare>> getSquareTypes(Class<? extends AbstractLevel> c){
		ArrayList<Class<? extends AbstractSquare>> squares=new ArrayList<Class<? extends AbstractSquare>>();
		
		try {
			squares = (ArrayList<Class<? extends AbstractSquare>>) c.getMethod("squareTypes").invoke(null);
		} catch (IllegalAccessException | IllegalArgumentException
				| InvocationTargetException | NoSuchMethodException
				| SecurityException e) {
			System.err.println("Could not get square types from class :" + c.getSimpleName());
			//e.printStackTrace();
		}
		
		return squares;
	}

	/**
	 * Returns an ArrayList containing the classes of selectable moves.
	 * The default allowable types are: Regular(Add to 6), Reset, Swap, Remove.
	 * A level class which extends this one can change the default by overriding/hiding this method.
	 * @return an ArrayList containing the classes of selectable moves
	 */
	public static ArrayList<Class<? extends AbstractMove>> moveTypes(){
		ArrayList<Class<? extends AbstractMove>> out=new ArrayList<Class<? extends AbstractMove>>();
		out.add(RemoveMove.class);
		out.add(ShuffleMove.class);
		out.add(Swap2Move.class);
		
		return out;
	}
	
	/**
	 * @param c some class which extends abstract level
	 * @return an ArrayList containing the classes of selectable moves
	 */
	@SuppressWarnings("unchecked")
	public static ArrayList<Class<? extends AbstractMove>> getMoveTypes(Class<? extends AbstractLevel> c){
		ArrayList<Class<? extends AbstractMove>> moves=new ArrayList<Class<? extends AbstractMove>>();
		
		try {
			moves = (ArrayList<Class<? extends AbstractMove>>) c.getMethod("moveTypes").invoke(null);
		} catch (IllegalAccessException | IllegalArgumentException
				| InvocationTargetException | NoSuchMethodException
				| SecurityException e) {
			System.err.println("Could not get move types from class :" + c.getSimpleName());
			//e.printStackTrace();
		}
		
		return moves;
	}

	/**
	 * Hashcode for level object.
	 */
	@Override
	public final int hashCode() {
		return toString().hashCode();
	}

	/**
	 * Return level name.
	 */
	@Override
	public final String toString() {
		return getType()+": "+this.levelID;
	}
	
	/**
	 * removes all information associated with this level, but it will remain. 
	 * in existence and retain its ID number.
	 */
	public void clear(){
		params=new LevelParameters();
		highscore=0;
		unlocked=false;
	}
	
	public static ParameterPanel getAdditionalParameterPanel(){
		return null;
	}

	/**
	 * Compare to method.
	 */
	@Override
	public int compareTo(AbstractLevel o) {
		return Integer.compare(this.getLevelID(),o.getLevelID());
	}
	
	/**
	 * Get the color of a level.
	 * @return
	 */
	public Color getColor()
	{
		return new Color(51, 153, 255);
	}

	/**
	 * Copy the level.
	 */
	@Override
	public void copy(ICopy object) {
		AbstractLevel level=(AbstractLevel)object;
		params.copy(level.getParams());
		highscore=level.getHighscore();
		unlocked=level.isUnlocked();
		levelID=level.getLevelID();
	}
}
