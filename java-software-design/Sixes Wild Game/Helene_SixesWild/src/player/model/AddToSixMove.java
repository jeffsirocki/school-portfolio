package player.model;

import common.model.AbstractContainer;
import common.model.AbstractMove;
import common.model.AbstractSquare;
import common.model.Selection;
import common.model.Tile;

/**
 * Move class used to remove a selection of squares whose tiles add to six.
 * @author Nicholas Panzarino
 *
 */
public class AddToSixMove extends AbstractMove {

	private static final long serialVersionUID = -3258989203756052150L;

	/**
	 * The selection containing squares to perform the move on.
	 */
	Selection selection;
	
	/**
	 * The point value of this move.
	 */
	int score;
	
	/**
	 * Constructor for this move using a selection object.
	 */
	public AddToSixMove(Selection s){
		this.selection = s;
	}
	
	/**
	 * Perform this move, remove tiles, and update score.
	 */
	@Override
	public boolean doMove() {
		if(isValid() == false){
			return false;
		}
		
		score = selection.size();
		
		for(AbstractSquare s:selection){
			if(s instanceof AbstractContainer){
				Tile t = ((AbstractContainer) s).remove();
				score *= t.getBonus();
			}
		}
		
		score *= 10;
		
		return true;
	}
	
	/**
	 * return the score value of this move.
	 */
	@Override
	public int getScore() {
		return score;
	}
	
	/**
	 * makes sure that the move is valid.
	 * @return
	 */
	public boolean isValid(){
		for(AbstractSquare s:selection){
			if(!(s instanceof AbstractContainer)){
				return false;
			}
			if(((AbstractContainer) s).peek() == null){
				return false;
			}
			if(((AbstractContainer) s).peek().getVal() == 6){
				return false;
			}
		}
		
		int sum = 0;
		
		for(AbstractSquare s:selection){
			AbstractContainer c = (AbstractContainer) s;
			sum += c.peek().getVal();
		}
		
		if(sum == 6){
			return true;
		}
		
		return false;
	}

}

