package builder.actions;

import builder.controllers.BuilderViewReference;
import common.model.ScoreThresholds;

/**
 * Allows the change of star thresholds and stoes the index, value, and previous value.
 * Do, undo, and redo for setting star thresholds.
 * @author jasirocki
 */
public class StarThresholdAction implements IAction{
	
	/** The index in the ScoreThreshold object. */
	int index;
	
	/** The new value for the index. */
	int value;
	
	/** The old value for the index. */
	int prev;

	/**
	 * Constructor that takes in an index and new value.
	 * @param index
	 * @param value
	 */
	public StarThresholdAction(int index, int value) {
		this.index = index;
		this.value = value;
	}
	
	/**
	 * Complete the star threshold change.
	 */
	@Override
	public boolean doAction() {
		
		ScoreThresholds thresholds=BuilderViewReference.get().getModel().getCurrentLevel().getParams().getThresholds();
		
		this.prev = thresholds.getScoreThresh(index);
		
		if(prev == value){
			return false;
		} else {
			thresholds.setThreshold(index, value);
			return true;
		}
	}

	/**
	 * Undoes the star threshold change.
	 */
	@Override
	public boolean undoAction() {
		
		ScoreThresholds thresholds=BuilderViewReference.get().getModel().getCurrentLevel().getParams().getThresholds();
		
		if(prev == value){
			return false;
		} else {
			thresholds.setThreshold(index, prev);
			return true;
		}
	}

}
