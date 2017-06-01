package player.controllers;

import player.model.Swap2Move;
import common.controllers.BoardSelectionController;
import common.model.AbstractContainer;
import common.model.AbstractSquare;
import common.model.Selection;
import common.view.BoardView;

/**
 * Swap 2 squares in game as a special move
 * @author njpanzarino
 * @author jasirocki - jdoc
 *
 */
public class Swap2Controller extends BoardSelectionController {

	/**
	 * Constructor for controller
	 * @param board
	 */
	public Swap2Controller(BoardView board) {
		super(board);
	}

	/**
	 * Validate that selected squares can be swapped
	 */
	@Override
	public boolean isValid(AbstractSquare s) {
		if(super.isValid(s)==false)
			return false;
		
		//Only allow two squares to be selected
		if((getSelection()!=null)&&(getSelection().size()>1)){
			return false;
		}
		
		if(s instanceof AbstractContainer){
			if(((AbstractContainer) s).peek()!=null)
				return true;
		}
		return false;
	}

	/**
	 * If valid, swap the two squares on the board
	 */
	@Override
	public void doReleased(Selection selection) {
		if(selection.size()>0){
			Swap2Move m=new Swap2Move(this.board.getBoard(),selection.get(0),selection.get(1));
			PlayerAppReference.get().getGameView().getTheGame().doMove(m);
			PlayerAppReference.get().getGameView().getSpecialMovesPanel().deselectAll();
			PlayerAppReference.updateFields();
		}
	}
	
	
	
}
