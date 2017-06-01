package gameModes.puzzle;

import gameModes.lightning.LightningLevel;

import java.awt.Color;
import java.util.ArrayList;

import player.model.AbstractGame;
import common.model.AbstractMove;

/**
 * A puzzle game.
 * @author njpanzarino
 * @author tjbennett
 * @author rlamsal-jdoc
 *
 */
public class PuzzleGame extends AbstractGame{
	
	/**
	 * number of moves made.
	 */
	int moves=0;

	/**
	 * Constructot that takes in a puzzle level.
	 * @param level
	 */
	public PuzzleGame(PuzzleLevel level) {
		super(level);
		
	}
	
	/**
	 * performs the move if its valid and increases the amount of moves made. 
	 */
	@Override
	public boolean doMove(AbstractMove move) {
		if(moves < ((PuzzleLevel)this.getLevel()).getMaxMoves()){
			if(super.doMove(move)){
				moves++;
				return true;
			}
		}
		return false;
	}

	/**
	 * Labels that keep track of moves on the side of game view. 
	 */
	@Override
	public ArrayList<String> getFields() {
		ArrayList<String> out=super.getFields();
		out.add("Move Count: "+moves);
		out.add("Remaining: "+(((PuzzleLevel)this.getLevel()).getMaxMoves()-moves));
		return out;
	}

	/**
	 * Puzzle game ends when the moves available are over. 
	 */
	@Override
	public boolean isOver() {
		//game is over when the moves are all out 
		
		if(moves >= ((PuzzleLevel)this.getLevel()).getMaxMoves()){
			return true;
		}
		return false;
	}
	
}
