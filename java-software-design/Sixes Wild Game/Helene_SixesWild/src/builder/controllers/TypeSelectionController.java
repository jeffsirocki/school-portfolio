package builder.controllers;

import builder.actions.CopyStateAction;
import common.controllers.BoardSelectionController;
import common.model.AbstractSquare;
import common.model.Board;
import common.model.Selection;
import common.view.BoardView;

/**
 * Controller that modifies the types of squares on the board.
 * @author njpanzarino
 * @author jasirocki - jdoc
 *
 */
public class TypeSelectionController extends BoardSelectionController {
	
	CopyStateAction<Board> act;
	
	/**
	 * Controller that awaits input to change type.
	 * @param boardView
	 */
	public TypeSelectionController(BoardView board) {
		super(board);
	}
	
	public void doPressed(){
		act=new CopyStateAction<Board>(board.getBoard());
	}
	
	/**
	 * Changes all squares in the selection to the selected type.
	 */
	public void doReleased(Selection selection){
		Board b=act.getFinal();
		
		for(AbstractSquare s:selection){
			b.changeType(s,BuilderViewReference.get().selectedSquareClass());
		}
		BuilderViewReference.get().getModel().getCurrentLevel().getParams().updateRef(b);
		b.update();
		act.setFinal(b);
		BuilderViewReference.get().getModel().doAction(act);
		
		board.updateBoard();
		BuilderViewReference.updateFields();
	}
}
