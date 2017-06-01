package builder.actions;

import common.model.ICopy;

/**
 * A generic save/load state action that can be used for undo/redo.
 * @author Nicholas Panzarino
 * @author jmmckinney
 *
 */
public class CopyStateAction<T extends ICopy> implements IAction {

	/**
	 * Reference to Original state.
	 */
	T start;
	
	/**
	 * Reference to actual object.
	 */
	T current;
	
	/**
	 * Reference to final object state.
	 */
	T end;
	
	/**
	 * Constructor for action.
	 * @param current
	 */
	public CopyStateAction(T current){
		start = current.makeCopy();
		this.current=current;
		end = current.makeCopy();
	}
	
	/**
	 * Get final object.
	 * @return
	 */
	public T getFinal(){
		return end;
	}
	
	public T getCurrent()
	{
		return current;
	}
	
	public T getStart()
	{
		return start;
	}
	
	/**
	 * Set final object.
	 * @param end
	 */
	public void setFinal(T end) {
		this.end = end;
		this.current.copy(start);
	}

	/**
	 * Do action.
	 */
	@Override
	public boolean doAction(){
		current.copy(end);
		return true;
	}

	/**
	 * Undo action.
	 */
	@Override
	public boolean undoAction() {
		current.copy(start);
		return true;
	}
	
	/**
	 * resets the action back to its original state.
	 */
	public void reset()
	{
		start = current.makeCopy();
		end = current.makeCopy();
	}
}
