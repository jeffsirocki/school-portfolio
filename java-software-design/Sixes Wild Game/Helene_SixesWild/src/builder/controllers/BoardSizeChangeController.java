package builder.controllers;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import common.model.Board;

import builder.actions.CopyStateAction;
import builder.view.BuilderView;

/** Controller for changing size of boards in builderView.
 * @author rlamsal-jdoc
 * @author Jmmckinney
 */
public class BoardSizeChangeController extends MouseAdapter implements ChangeListener{
	/**
	 * Object that holds the levelBuilder application.
	 */
	BuilderView builderView;
	
	/**
	 * checks if size is changed or not.
	 */
	boolean sizeChanged;
	
	/**
	 * initial size.
	 */
	int startSize;
	
	/**
	 * stores action to allow undo/redo.
	 */
	CopyStateAction<Board> action;
	
	/**
	 * Constructor that takes in a builder view. 
	 * @param builderView
	 */
	public BoardSizeChangeController(BuilderView builderView)
	{
		this.builderView = builderView;
		this.sizeChanged = false;
	}
	
	/**
	 * controls the mouse pressed action by initializing the start size and creating an action. 
	 */
	@Override
	public void mousePressed(MouseEvent e) {
		if(BuilderViewReference.get().getModel().getCurrentLevel() != null)
		{
			action = new CopyStateAction<Board>(BuilderViewReference.get().getModel().getCurrentLevel().getParams().getBoard());
			this.startSize = BuilderViewReference.get().getModel().getCurrentLevel().getParams().getBoard().getSize();
		}
	}
	
	/**
	 * At mouse release, stores all the actions to allow redo capability. 
	 * Performs action if the size is changed. 
	 */
	@Override
	public void mouseReleased(MouseEvent e) {
		if(BuilderViewReference.get().getModel().getCurrentLevel() != null)
		{
			Board fin = action.getFinal();
			fin.copy(BuilderViewReference.get().getModel().getCurrentLevel().getParams().getBoard());
			action.setFinal(fin);
			if(sizeChanged)
				BuilderViewReference.get().getModel().doAction(action);
			
			//System.out.println("ActionEvent");
			sizeChanged = false;
		}
	}
	
	/**
	 * sets the size changed to true if the state of the slider has been changed.
	 */
	public void stateChanged(ChangeEvent arg0) {
		if(BuilderViewReference.get().getModel().getCurrentLevel()==null)
			return;
		if(BuilderViewReference.get().listen==false)
			return;
		if(startSize != builderView.getSizeSliderValue())
			sizeChanged = true;
		
		BuilderViewReference.get().getModel().getCurrentLevel().getParams().getBoard().setSize(builderView.getSizeSliderValue());
		BuilderViewReference.updateFields();
	}
}
