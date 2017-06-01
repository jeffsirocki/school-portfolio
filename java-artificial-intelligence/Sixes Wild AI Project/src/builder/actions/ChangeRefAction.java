package builder.actions;

import java.awt.Point;

import common.model.ReferenceMap;

/**
 * Changes the fillForm references from a point to another point; allows undo and redo capability.
 * @author rlamsal-jdoc
 *
 */
public class ChangeRefAction implements IAction{

	/**
	 * Map that stores information about fillForm references.
	 */
	ReferenceMap map;
	
	/**
	 * Point used to store the original reference.
	 */
	Point oldRef;
	
	/**
	 * Point that represents the original reference.
	 */
	Point point;
	
	/**
	 * Point that refers to the new reference.
	 */
	Point newRef;
	
	/**
	 * Constructor for ChangeRefAction that takes in a map, a point, and a new reference to the point.
	 * @param map
	 * @param point
	 * @param newRef
	 */
	public ChangeRefAction(ReferenceMap map,Point point,Point newRef){
		this.map=map;
		this.point=point;
		this.newRef=newRef;
		
		this.oldRef=map.getRef(point);
	}
	
	/**
	 * perform current action.
	 * overrides the function doAction() in IAction.
	 */
	@Override
	public boolean doAction() {
		if(map.getRef(point)!=newRef){
			map.setRef(point, newRef);
			return true;
		}
		return false;
	}

	/**
	 * Undo current action.
	 * overrides the function undoAction() in IAction.
	 */
	@Override
	public boolean undoAction() {
		if(map.getRef(point)!=oldRef){
			map.setRef(point, oldRef);
			return true;
		}
		return false;
	}

}
