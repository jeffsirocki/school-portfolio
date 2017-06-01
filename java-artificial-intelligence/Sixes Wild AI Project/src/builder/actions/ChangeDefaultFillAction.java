package builder.actions;

import java.awt.Point;
import java.util.ArrayList;

import builder.controllers.BuilderViewReference;

import common.model.AbstractLevel;
import common.model.ReferenceMap;


/**
 * Changes the default fillFrom reference for all the squares on the board.
 * allows undo and redo. 
 * @author rlamsal-jdoc
 *
 */
public class ChangeDefaultFillAction implements IAction {
	
	/**
	 * Framework for level objects that can be built and played.
	 */
	AbstractLevel level;
	
	/**
	 * Stores information about the initial fillForm direction.
	 */
	int[] oldDir;
	
	/**
	 * Stores information about the new fillForm direction.
	 */
	int[] newDir;
	
	/**
	 * Arraylist of actions.
	 */
	ArrayList<IAction> acts;

	/**
	 * Constructor that takes in a abstractLevel and a new fillFrom direction.
	 * @param level
	 * @param newDir
	 */
	public ChangeDefaultFillAction(AbstractLevel level,int[] newDir){
		this.level=level;
		this.newDir=newDir;
		acts=new ArrayList<IAction>();
		
		ReferenceMap ref=level.getParams().getRef();
		this.oldDir=ref.getDefaultFill();
		
		ref.setDefaultFill(newDir);
		for(Point p:ref){
			acts.add(new ChangeRefAction(ref, p,ref.getDefaultFillPoint(p)));
		}
		ref.setDefaultFill(oldDir);
	}
	
	/**
	 * sets the new default direction.
	 * performs doAction for all the action to allow for undoAction.
	 */
	@Override
	public boolean doAction() {
		
		level.getParams().getRef().setDefaultFill(newDir);
		
		for(IAction act:acts){
			act.doAction();
		}
		BuilderViewReference.get().getModel().getCurrentLevel().getParams().updateRef();
		return true;
	}

	/**
	 * undoes the actions performed by doAction.
	 */
	@Override
	public boolean undoAction() {
		
		level.getParams().getRef().setDefaultFill(oldDir);
		
		for(IAction act:acts){
			act.undoAction();
		}
		BuilderViewReference.get().getModel().getCurrentLevel().getParams().updateRef();
		return true;
	}

}
