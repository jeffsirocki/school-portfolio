package builder.controllers;

import java.awt.event.MouseAdapter;
import java.lang.reflect.InvocationTargetException;

import builder.actions.CopyStateAction;
import common.model.AbstractLevel;

/**
 * Controller for resetting a level to a brand new state.
 * The level is replaced with a completely new default level of the same type/number.
 * @author jasirocki
 */
public class ResetBoardController extends MouseAdapter {
	
	/**
	 * Empty Constructor.
	 */
	public ResetBoardController() {

	}
	
	/**
	 * Mouse Press handler that resets the board to its original state based on its level parameters on disk.
	 */
	public void mousePressed (java.awt.event.MouseEvent me) {
		//Type
		Class<? extends AbstractLevel> c = BuilderViewReference.get().selectedLevelClass();
		// Number
		Integer n = BuilderViewReference.get().selectedLevelNumber();
		
		if((n==null)||(c==null)){
			return;
		}
		
		// Make level
		
		try {
			AbstractLevel level =c.getDeclaredConstructor(Integer.class).newInstance(n);
			//BuilderViewReference.get().setCurrentLevel(level);
			//BuilderViewReference.get().getModel().getCurrentLevel().copy(level);
			CopyStateAction<AbstractLevel> act=new CopyStateAction<AbstractLevel>(BuilderViewReference.get().getModel().getCurrentLevel());
			act.setFinal(level);
			BuilderViewReference.get().getModel().doAction(act);
		} catch (InstantiationException | IllegalAccessException
				| IllegalArgumentException | InvocationTargetException
				| NoSuchMethodException | SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}			
		
		BuilderViewReference.updateFields();
		BuilderViewReference.get().resetBoard();
	}
	
}
