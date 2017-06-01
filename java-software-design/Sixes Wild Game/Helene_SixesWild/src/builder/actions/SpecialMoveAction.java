package builder.actions;

import java.util.ArrayList;

import builder.controllers.BuilderViewReference;
import common.model.AbstractMove;

/**
 * Allows change of whether a level can make certain special moves. 
 * Do, Redo, and Undo capability.
 * @author jasirocki
 */
public class SpecialMoveAction implements IAction{
	
	/** Special move that is being modified. */
	Class<? extends AbstractMove> move;
	
	/** Original boolean for special move. */
	boolean allow;
	
	/**
	 * Constructor for special move action that takes in a special move and flag.
	 * @param move
	 * @param allow
	 */
	public SpecialMoveAction(Class<? extends AbstractMove> move, boolean allow) {
		this.move = move;
		this.allow = allow;
	}
	
	/**
	 * Add or Remove the special move from the moves list.
	 */
	@Override
	public boolean doAction() {
		
		ArrayList<Class<? extends AbstractMove>> allowedMoves=BuilderViewReference.get().getModel().getCurrentLevel().getParams().getMoves();
		
		boolean prev = allowedMoves.contains(move);
		
		if(prev == allow){
			return false;
		} else if(allow)  {
			allowedMoves.add(move);
			return true;
		} else {
			allowedMoves.remove(move);
			return true;
		}
	}

	/**
	 * Undo the add or removal of a move from the moves list. 
	 */
	@Override
	public boolean undoAction() {
		
		ArrayList<Class<? extends AbstractMove>> allowedMoves=BuilderViewReference.get().getModel().getCurrentLevel().getParams().getMoves();
		
		boolean prev = allowedMoves.contains(move);
		
		if(prev != allow){
			return false;
		} else if(allow)  {
			allowedMoves.remove(move);
			return true;
		} else {
			allowedMoves.add(move);
			return true;
		}
	}
	
}
