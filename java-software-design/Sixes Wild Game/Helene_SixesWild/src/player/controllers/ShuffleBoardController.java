package player.controllers;

import player.model.ShuffleMove;
import common.controllers.BoardSelectionController;
import common.model.AbstractContainer;
import common.model.AbstractSquare;
import common.model.Selection;
import common.view.BoardView;

/**
 * Shuffle board controller shuffles the board and is a special move.
 * @author Nicholas Panzarino
 * @author jasirocki
 *
 */
public class ShuffleBoardController extends BoardSelectionController {

	/**
	 * Constructor of controller.
	 * @param board
	 */
	public ShuffleBoardController(BoardView board) {
		super(board);
	}
	
	/**
	 * Validate that the user only clicks one square on the board
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
	 * If valid, complete the shuffle action
	 */
	@Override
	public void doReleased(Selection selection) {
		if(selection.size()>0){
			ShuffleMove m=new ShuffleMove(this.board.getBoard());
			PlayerAppReference.get().getGameView().getTheGame().doMove(m);
			PlayerAppReference.get().getGameView().getSpecialMovesPanel().deselectAll();
			PlayerAppReference.updateFields();
		}
	}

}
