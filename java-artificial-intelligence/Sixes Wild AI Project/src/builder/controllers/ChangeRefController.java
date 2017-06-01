package builder.controllers;

import builder.actions.ChangeRefMapAction;
import common.controllers.BoardSelectionController;
import common.model.AbstractSquare;
import common.model.Selection;
import common.view.BoardView;

/**
 * Controller class that handles the mouse dragging and released events in the fillFrom Panel.
 * @author rlamsal-jdoc
 *
 */
public class ChangeRefController extends BoardSelectionController {

	/**
	 * Constructor for ChangeRefController that takes in a BoardView.
	 * @param board
	 */
	public ChangeRefController(BoardView board) {
		super(board);
	}

	/**
	 * Controls the mouse drag event by displaying the new fillForm reference action.
	 */
	@Override
	public void doDragged() {
		ChangeRefMapAction act=new ChangeRefMapAction(BuilderViewReference.get().getModel().getCurrentLevel().getParams().getRef(),this.selection);
		act.tempDo();
		BuilderViewReference.get().getBoardView().redraw();
	}

	/**
	 * Controls the mouse released event by changing the fillForm action
	 */
	@Override
	public void doReleased(Selection selection) {
		ChangeRefMapAction act=new ChangeRefMapAction(BuilderViewReference.get().getModel().getCurrentLevel().getParams().getRef(),this.selection);
		BuilderViewReference.get().getModel().doAction(act);
		BuilderViewReference.get().getModel().getCurrentLevel().getParams().updateRef();
		BuilderViewReference.updateFields();
		BuilderViewReference.get().getBoardView().redraw();
	}

	/**
	 * Checks if the squares selected are valid or not.
	 * returns true if its valid.
	 */
	@Override
	public boolean isValid(AbstractSquare s) {
		if(super.isValid(s)==false)
			return false;
		
		if(selection==null)
			return true;
		
		//Only allow squares adjacent to last selected square to be selected
		if(isAdjacent(s,selection.get(selection.size()-1))){
			return true;
		}
		return false;
	}
	
	/**
	 * Checks if the selected squares are adjacent to each other or not.
	 * returns true if they are adjacent.
	 * @param s1
	 * @param s2
	 * @return
	 */
	boolean isAdjacent(AbstractSquare s1,AbstractSquare s2){
		if(s1.getRow()==s2.getRow()){
			if(s1.getColumn()==s2.getColumn()+1)
				return true;
			else if(s1.getColumn()==s2.getColumn()-1)
				return true;
		}
		else if(s1.getColumn()==s2.getColumn()){
			if(s1.getRow()==s2.getRow()+1)
				return true;
			else if(s1.getRow()==s2.getRow()-1)
				return true;
		}
		return false;
	}
}
