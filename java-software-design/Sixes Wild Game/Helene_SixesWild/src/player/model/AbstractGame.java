package player.model;

import java.awt.Color;
import java.util.ArrayList;

import common.model.AbstractLevel;
import common.model.AbstractMove;

/**
 * Abstract class for playing Levels as a specific game mode.
 * Games keep a running count of the current score.
 * Games have an at least one end condition, and a function for ending themselves
 * @author Nicholas Panzarino
 *
 */
public abstract class AbstractGame {

	/**
	 * A reference to the level being played by this game.
	 */
	AbstractLevel level;
	
	/**
	 * The current score in this game session.
	 */
	protected int score;
	
	/**
	 * create a new game given an abstract level.
	 * @param level
	 */
	public AbstractGame(AbstractLevel level){
		this.level=level;
		score=0;
	}

	/**
	 * Returns the current level being played by this game.
	 */
	public AbstractLevel getLevel() {
		return level;
	}

	/**
	 * Returns the current score of this game
	 */
	public int getScore() {
		return score;
	}
	
	/**
	 * Performs the given move on this game.
	 * Updates the score based on the move.
	 * Can be overridden to increment a move counter in appropriate game modess
	 */
	public boolean doMove(AbstractMove move){
		if(move.doMove()==true){
			score+=move.getScore();
			return true;
		}
		return false;
	}
	
	/**
	 * Returns an arrayList of strings to be displayed on the left-hand side of the gameView window.
	 * @return
	 */
	public ArrayList<String> getFields(){
		return new ArrayList<String>();
	}
	
	/**
	 * Place to put any necessary "close game" functions. 
	 * Should be called any time a game is closed/ended
	 */
	public void endGame(){
		if(this.score>this.getLevel().getHighscore()){
			this.getLevel().setHighscore(score);
		}
	}
	
	/**
	 * Returns the color of the level this game is playing
	 * @return
	 */
	public final Color gameColor(){
		return this.getLevel().getColor();
	}
	
	/**
	 * Determines if a game has ended.
	 * @return
	 */
	public abstract boolean isOver();
}
