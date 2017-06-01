package builder.controllers;

import java.awt.event.MouseAdapter;

import builder.actions.ChangeBuilderLevelAction;
import common.model.AbstractLevel;

/**
 * Adds a new level to the level list.
 * @author tjbennett
 * @author jasirocki - jdoc
 *
 */
public class AddNewLevelController extends MouseAdapter {
	
	/**
	 * Constructor for controller.
	 */
	public AddNewLevelController(){
		
	}
	
	/**
	 * Mouse Press handler that adds level to level list.
	 */
	public void mousePressed (java.awt.event.MouseEvent me) {
		
		//Type of Level in the ComboBox
		Class<? extends AbstractLevel> c = BuilderViewReference.get().selectedLevelClass();
		
		// Number
		Integer n = BuilderViewReference.get().selectedLevelNumber();
				
		if((c==null)){
			return;
		}
		
		AbstractLevel l = BuilderViewReference.get().getModel().getAllLevels().addLevel(c,n);
		BuilderViewReference.get().updateLevelNums();
		
		ChangeBuilderLevelAction act=new ChangeBuilderLevelAction(l);
		BuilderViewReference.get().getModel().doAction(act);
		
		BuilderViewReference.updateFields();
	}
	
}
