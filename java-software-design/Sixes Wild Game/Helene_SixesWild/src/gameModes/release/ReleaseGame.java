package gameModes.release;

import gameModes.elimination.EliminationLevel;

import java.awt.Color;
import java.util.ArrayList;

import player.model.AbstractGame;
import common.model.AbstractContainer;
import common.model.AbstractMove;
import common.model.AbstractSquare;
import common.model.Tile;

/**
 * Release game.
 * @author rlamsal-jdoc
 * @author njpanzarino
 * @author tjbennett
 */
public class ReleaseGame extends AbstractGame {

	/**
	 * moves made. 
	 */
	int currentMoves = 0;
	
	/**
	 * Constructor that takes in a release level.
	 * @param level
	 */
	public ReleaseGame(ReleaseLevel level) {
		super(level);
	}
	
	/**
	 * performs a move if it is valid and increments the amount of move.
	 */
	@Override
	public boolean doMove(AbstractMove move) {
		if(remainingMoves()>0){
			if(super.doMove(move)){
				currentMoves++;
				return true;
			}
		}
		return false;
	}

	/**
	 * Labels that keep track of moves and sixes. 
	 */
	@Override
	public ArrayList<String> getFields() {
		ArrayList<String> out = super.getFields();
		out.add("Remaining Moves: " + remainingMoves());
		out.add("Remaining Sixes: " + sixesRemaining());
		return out;
	}
	
	/**
	 * Release levels end if Move count run out OR if all Six tiles are gone.
	 * If Six tiles are all gone, 100 points are added per remaining move.
	 * If move count runs out, no score is recorded unless all sixes are gone.
	 * 
	 */
	@Override
	public boolean isOver() {
		if(remainingMoves()<=0){
			return true;
		}
		if(sixesRemaining()==0){
			return true;
		}
		return false;
	}

	/**
	 * if the game is over, calculates the score and ends the game.
	 */
	@Override
	public void endGame() {
		if(sixesRemaining()==0){
			score+=(100*remainingMoves());
			super.endGame();
		}
	}
	
	/**
	 * calculates the remaining moves.
	 * @return
	 */
	public int remainingMoves(){
		return ((ReleaseLevel)this.getLevel()).getMaxMoves()-currentMoves;
	}

	/**
	 * calculates the amount of sixes remaining.
	 * @return
	 */
	public int sixesRemaining(){
		int sixesRemaining=0;
		for(AbstractSquare s:this.getLevel().getParams().getBoard()){
			if(s instanceof AbstractContainer){
				Tile t = ((AbstractContainer) s).peek();
				if(t != null){
					if(t.getVal() == 6){
						sixesRemaining++;
					}
				}
			}
		}
		return sixesRemaining;
	}
}
