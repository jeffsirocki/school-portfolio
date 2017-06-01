package player.controllers;

import player.model.RemoveMove;
import common.controllers.BoardSelectionController;
import common.model.AbstractContainer;
import common.model.AbstractSquare;
import common.model.Selection;
import common.view.BoardView;

/**
 * Controller that removes a square from the board and is a special move
 * @author njpanzario
 * @author jasirocki - jdoc
 *
 */
public class RemoveSquareController extends BoardSelectionController {

	/**
	 * Constructor that initializes controller.
	 * @param board
	 */
	public RemoveSquareController(BoardView board) {
		super(board);
	}

	/**
	 * Validation of selection for removal.
	 */
	@Override
	public boolean isValid(AbstractSquare s) {
		if(super.isValid(s)==false)
			return false;
		
		//Only allow one square to be selected
		if(getSelection()!=null){
			return false;
		}
		
		if(s instanceof AbstractContainer){
			if(((AbstractContainer) s).peek()!=null)
				return true;
		}
		return false;
	}

	/**
	 * Remove the selected square(s).
	 */
	@Override
	public void doReleased(Selection selection) {
		if(selection.size()>0){
			RemoveMove m=new RemoveMove(this.board.getBoard(),selection.get(0));
			PlayerAppReference.get().getGameView().getTheGame().doMove(m);
			PlayerAppReference.get().getGameView().getSpecialMovesPanel().deselectAll();
			PlayerAppReference.updateFields();
		}
	}
	
	

}
