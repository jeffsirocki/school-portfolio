package gameModes.elimination;

import builder.actions.IAction;
import builder.controllers.BuilderViewReference;

/**
 * Allows for undo/redo actions of max move in level builder for elimination levels.
 * @author jasirocki
 */
public class EliminationMoveAction implements IAction {

	/** New value for max moves. */
	int value;
	
	/** Old value for max moves. */
	int prev;
	
	 /**
	  * Constructor for max move action. 
	  * @param value
	  */
	public EliminationMoveAction(int value) {
		this.value = value;
	}
	
	/**
	 * Set max moves for elimination level.
	 */
	@Override
	public boolean doAction() {
		
		EliminationLevel level = (EliminationLevel) BuilderViewReference.get().getModel().getCurrentLevel();
		
		this.prev = level.getMaxMoves();
		
		if(prev == value){
			return false;
		} else {
			level.setMaxMoves(value);
			return true;
		}
	}

	/**
	 * Undo set max moves for elimination level.
	 */
	@Override
	public boolean undoAction() {

		EliminationLevel level = (EliminationLevel) BuilderViewReference.get().getModel().getCurrentLevel();
		
		if(prev == value){
			return false;
		} else {
			level.setMaxMoves(prev);
			return true;
		}
	}

}
