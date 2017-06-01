
package gameModes.elimination;

import java.awt.Color;
import java.util.ArrayList;

import common.model.AbstractMove;
import common.model.AbstractSquare;
import player.model.AbstractGame;

/**
 * Elimination game.
 * @author njpanzarino
 * @author tjbennett
 * @author rlamsal-jdoc
 *
 */

public class EliminationGame extends AbstractGame {

	/**
	 * stores the number of moves made.
	 */
	int currentMoves = 0;
	
	/**
	 * Constructor that takes in a elimination level.
	 * @param level
	 */
	public EliminationGame(EliminationLevel level) {
		super(level);
	}

	/**
	 * performs a move if its valid and increments the number of moves.
	 */
	@Override
	public boolean doMove(AbstractMove move) {
		// if there are unmarked remaining, do move
		if(currentMoves < ((EliminationLevel)this.getLevel()).getMaxMoves()){
			if(super.doMove(move)){
				currentMoves++;
				return true;
			}
		}
		return false;
	}

	/**
	 * the labels that keep track of the moves on the side in the game view.
	 */
	@Override
	public ArrayList<String> getFields() {
		ArrayList<String> out = super.getFields();
		out.add("Move Count: " + currentMoves);
		out.add("Remaining Moves: "+remainingMoves());
		out.add("Remaining Unmarked: " + countUnmarked());
		return out;
	}

	/**
	 * Elimination Games end when the maximum number of moves has been reached, or all squares are marked.
	 */
	@Override
	public boolean isOver() {
		//game is over when the maxmoves has been reached
		if(remainingMoves()<=0){
			return true;
		}
		//OR when all squares are elimination squares
		if(countUnmarked()==0){
			return true;
		}
		return false;
	}
	
	/**
	 * When the game ends, add points based on the number of moves remaining.
	 * If Not all tiles are marked, then don't record high score.
	 */
	@Override
	public void endGame() {
		if(this.countUnmarked()==0){
			score+=100*remainingMoves();
			super.endGame();
		}
	}
	
	/**
	 * amount of moves left before the game ends.
	 * @return
	 */
	public int remainingMoves(){
		return ((EliminationLevel)this.getLevel()).getMaxMoves()-currentMoves;
	}

	/**
	 * counts the number of squares that aren't eliminated yet.
	 * @return
	 */
	public int countUnmarked(){
		int total=0;
		int marked=0;
		for(AbstractSquare s:this.getLevel().getParams().getBoard()){
			if(s instanceof EliminationSquare){
				if(((EliminationSquare) s).isMarked()){
					marked++;
				}
				total++;
			}
		}
		return total-marked;
	}
	
}
