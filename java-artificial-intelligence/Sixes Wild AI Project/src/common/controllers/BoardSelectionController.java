package common.controllers;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import common.model.AbstractSquare;
import common.model.Selection;
import common.view.BoardView;

/**
 * Creates a foundation for controllers which make act on BoardView boundary objects.
 * -Automatically handles selection, highlighting, and deselection.
 * -Subclasses can override the isValid() and doAction() methods to develop unique behaviors.
 * @author Nicholas Panzarino
 * @author jasirocki - jdoc
 *
 */
public class BoardSelectionController extends MouseAdapter {
	
	/** The selection of squares. */
	protected Selection selection=null;
	
	/** The board view that displays the board. */
	protected BoardView board;
	
	/**
	 * Constructor that takes in a BoardView.
	 * @param board
	 */
	public BoardSelectionController(BoardView board){
		this.board=board;
		enable(true);
	}
	
	/**
	 * Attaches/detaches this controller to/from its original boardView.
	 * @param b
	 */
	public final void enable(boolean b){
		
		board.removeMouseListener(this);
		board.removeMouseMotionListener(this);
		
		if(b){
			board.addMouseListener(this);
			board.addMouseMotionListener(this);
		}
	}
	
	/**
	 * Mouse press event on a square makes a selection.
	 */
	@Override
	public final void mousePressed(MouseEvent arg0) {
		AbstractSquare s=square(arg0);
		if(!isValid(s)){
			return;
		}
		
		if(selection==null)
			selection=new Selection(s);
		else
			selection.add(s);
		
		doPressed();
		
		arg0.getComponent().setVisible(false);
		arg0.getComponent().setVisible(true);
	}
	
	/**
	 * Called at the end of every mousePressed Event.
	 */
	public void doPressed() {
		
	}

	/**
	 * Mouse drag event adds squares to the selection.
	 */
	@Override
	public final void mouseDragged(MouseEvent e) {
		AbstractSquare s=square(e);
		if(!isValid(s)){
			//mouseReleased(e);
			return;
		}
		
		if(selection == null)
			selection=new Selection(s);
		else
			selection.add(s);
		
		doDragged();
		
		e.getComponent().setVisible(false);
		e.getComponent().setVisible(true);
	}
	
	/**
	 * Called at the end of every mouseDragged Event.
	 */
	public void doDragged() {
		
	}

	/**
	 * Mouse release event is handled by completing desired action.
	 */
	@Override
	public final void mouseReleased(MouseEvent arg0) {
		if(selection==null)
			return;
		
		doReleased(selection);
		
		selection.clear();
		selection=null;
		arg0.getComponent().setVisible(false);
		arg0.getComponent().setVisible(true);
	}
	
	/**
	 * Returns true if the square can be added to the selection, false otherwise.
	 * -Overridden by subclasses to modify behavior.
	 * @param s
	 * @return boolean
	 */
	public boolean isValid(AbstractSquare s){
		if(s==null)
			return false;
		return true;
	}
	
	/**
	 * This method will be called at the start of every MouseReleased event.
	 * Can be overridden to add custom actions to the end of a Selection.
	 * @param selection
	 */
	public void doReleased(Selection selection){
		
	}
	
	/**
	 * returns the current selection held by this controller.
	 * @return Selection
	 */
	public final Selection getSelection() {
		return selection;
	}

	/**
	 * returns the square at the location of the current mouseEvent.
	 * @param arg0
	 * @return AbstractSquare
	 */
	public final AbstractSquare square(MouseEvent arg0){
		try{
			return ((BoardView)arg0.getComponent()).squareAt(arg0.getX(),arg0.getY());
		}catch(Exception e){
			System.err.println("could not get square from mouse event");
		}
		return null;
	}

}
