package builder.controllers;

import java.awt.event.MouseAdapter;
import java.util.ArrayList;
import java.util.Collections;

import common.model.AbstractLevel;

/**
 * Deletes level from the level list.
 * @author tjbennett
 * @author njpanzarino
 * @author jasirocki - jdoc
 */
public class DeleteLevelController extends MouseAdapter {

	/**
	 * Empty Constructor.
	 */
	public DeleteLevelController(){
		
	}
	
	/**
	 * Mouse Press handler that deletes selected level from the level list.
	 */
	public void mousePressed (java.awt.event.MouseEvent me) {
		
		AbstractLevel l = BuilderViewReference.get().getModel().getCurrentLevel();
		ArrayList<AbstractLevel> list=BuilderViewReference.get().getModel().getAllLevels().getType(l.getClass());
		Collections.sort(list);
		int i=list.indexOf(l);
		
		BuilderViewReference.get().getModel().getAllLevels().removeLevel(l);
		
		if(i<1){
			BuilderViewReference.get().setCurrentLevel(null);
		}
		else
			BuilderViewReference.get().setCurrentLevel(list.get(i-1));
		
		BuilderViewReference.get().updateLevelNums();
		
//		BuilderViewReference.get().loadLevelFields();
		BuilderViewReference.get().loadFields();
	}
}
