package gameModes.puzzle;

import builder.actions.IAction;
import builder.controllers.BuilderViewReference;

/**
 * Allows for undo/redo actions of max move in level builder for puzzle levels.
 * @author jasirocki
 *
 */
public class PuzzleMoveAction implements IAction{
	
	/** New value for max moves. */
	int value;
	
	/** Old value for max moves. */
	int prev;
	
	/**
	 * Constructor for max move action.
	 * @param value
	 */
	public PuzzleMoveAction(int value) {
		this.value = value;
	}
	
	/**
	 * Set max move for puzzle level.
	 */
	@Override
	public boolean doAction() {
		
		PuzzleLevel level = (PuzzleLevel) BuilderViewReference.get().getModel().getCurrentLevel();
		
		this.prev = level.getMaxMoves();
		
		if(prev == value){
			return false;
		} else {
			level.setMaxMoves(value);
			return true;
		}
	}

	/**
	 * Undo set max move for puzzle level.
	 */
	@Override
	public boolean undoAction() {

		PuzzleLevel level = (PuzzleLevel) BuilderViewReference.get().getModel().getCurrentLevel();
		
		if(prev == value){
			return false;
		} else {
			level.setMaxMoves(prev);
			return true;
		}
	}
}
