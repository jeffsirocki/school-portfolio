package gameModes.lightning;

import builder.actions.IAction;
import builder.controllers.BuilderViewReference;

/**
 * Allows for undo/redo actions of max time in level builder for lightning levels.
 * @author jasirocki
 *
 */
public class LightningTimeAction implements IAction{

	/** New time for max time. */
	int value;
	
	/** Old time for max time. */
	int prev;
	
	/**
	 * Constructor for max time actions.
	 * @param value
	 */
	public LightningTimeAction(int value) {
		this.value = value;
	}
	
	/**
	 * Set max time for lightning level.
	 */
	@Override
	public boolean doAction() {
		
		LightningLevel level = (LightningLevel) BuilderViewReference.get().getModel().getCurrentLevel();
		
		this.prev = level.getMaxSeconds();
		
		if(prev == value){
			return false;
		} else {
			level.setMaxSeconds(value);
			return true;
		}
	}

	/**
	 * Undo set max time for lightning level.
	 */
	@Override
	public boolean undoAction() {

		LightningLevel level = (LightningLevel) BuilderViewReference.get().getModel().getCurrentLevel();
		
		if(prev == value){
			return false;
		} else {
			level.setMaxSeconds(prev);
			return true;
		}
	}
}
