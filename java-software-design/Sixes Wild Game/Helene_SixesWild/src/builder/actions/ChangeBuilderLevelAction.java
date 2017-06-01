package builder.actions;

import builder.controllers.BuilderViewReference;
import common.model.AbstractLevel;

/**
 * Changes the level in the level Builder
 * adds the actions onto the undoStack, but
 * doesn't allow for undo capability.
 * @author rlamsal-jdoc
 *
 */

public class ChangeBuilderLevelAction implements IAction {

	/**
	 * initial level.
	 */
	AbstractLevel prev;
	
	/**
	 * new level.
	 */
	AbstractLevel next;
	
	/**
	 * Constructor that takes in a new abstractLevel.
	 * @param next
	 */
	public ChangeBuilderLevelAction(AbstractLevel next){
		prev=BuilderViewReference.get().getModel().getCurrentLevel();
		this.next=next;
	}
	
	/**
	 * sets the current level to the new level.
	 * adds all the actions to the stack.
	 */
	@Override
	public boolean doAction() {
		if(prev==next)
			return false;
		
		BuilderViewReference.get().setCurrentLevel(next);
		//System.out.println(next);
		return true;
	}

	/**
	 * returns false to disallow undo capability.
	 */
	@Override
	public boolean undoAction() {
		return false;
//		if(prev==next)
//			return false;
//		
//		BuilderViewReference.get().setCurrentLevel(prev);
//		//System.out.println(prev);
//		return true;
	}

}
