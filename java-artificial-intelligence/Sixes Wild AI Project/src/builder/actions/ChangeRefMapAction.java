package builder.actions;

import java.awt.Point;
import java.util.ArrayList;

import builder.controllers.BuilderViewReference;
import common.model.ReferenceMap;
import common.model.Selection;

/**
 * Changes the fillForm references for all the points; allows undo and redo.
 * @author rlamsal-jdoc
 *
 */
public class ChangeRefMapAction implements IAction{

	/**
	 * Map that stores information about fillForm references.
	 */
	ReferenceMap map;
	
	/**
	 * Arraylist of actions.
	 */
	ArrayList<IAction> acts;
	
	/**
	 * Constructor that takes in a Reference map and a selection.
	 * @param map
	 * @param select
	 */
	public ChangeRefMapAction(ReferenceMap map,Selection select){
		this.map=map;
		acts=new ArrayList<IAction>();
		
		if(select.size()==0)
			return;
		
		Point current=null,prev=new Point(-1,-1);
		
		if(select.size()>1){
			prev=map.getRef(new Point(select.get(0).getRow(),select.get(0).getColumn()));
		}
		
		for(int i=0;i<select.size();i++){
			current=new Point(select.get(i).getRow(),select.get(i).getColumn());
			acts.add(new ChangeRefAction(map,current,prev));
			prev=current;
		}
	}
	
	/**
	 * Temporarily performs the action produced by the dragging mouse and undoes it when the mouse is released.
	 */
	public void tempDo(){
		for(IAction act:acts){
			act.doAction();
		}
		BuilderViewReference.get().getModel().getCurrentLevel().getParams().updateRef();
		for(IAction act:acts){
			act.undoAction();
		}
	}
	
	/**
	 * Performs the action.
	 */
	@Override
	public boolean doAction() {
		for(IAction act:acts){
			act.doAction();
		}
		BuilderViewReference.get().getModel().getCurrentLevel().getParams().updateRef();
		return true;
	}

	/**
	 * Undo the action performed.
	 */
	@Override
	public boolean undoAction() {
		for(IAction act:acts){
			act.undoAction();
		}
		BuilderViewReference.get().getModel().getCurrentLevel().getParams().updateRef();
		return true;
	}

}
