package gameModes.release;

import builder.actions.IAction;
import builder.controllers.BuilderViewReference;

/**
 * Allows for undo/redo actions of max move in level builder for release levels.
 * @author jasirocki
 *
 */
public class ReleaseMoveAction implements IAction{

	/** New value for max moves. */
	int value;
	
	/** Old value for max moves. */
	int prev;
	
	/**
	 * Constructor for max move action. 
	 * @param value
	 */
	public ReleaseMoveAction(int value) {
		this.value = value;
	}
	
	/**
	 * Set max move in level builder.
	 */
	@Override
	public boolean doAction() {
		
		ReleaseLevel level = (ReleaseLevel) BuilderViewReference.get().getModel().getCurrentLevel();
		
		this.prev = level.getMaxMoves();
		
		if(prev == value){
			return false;
		} else {
			level.setMaxMoves(value);
			return true;
		}
	}

	/**
	 * Undo set max move in level builder.
	 */
	@Override
	public boolean undoAction() {

		ReleaseLevel level = (ReleaseLevel) BuilderViewReference.get().getModel().getCurrentLevel();
		
		if(prev == value){
			return false;
		} else {
			level.setMaxMoves(prev);
			return true;
		}
	}
	
}
