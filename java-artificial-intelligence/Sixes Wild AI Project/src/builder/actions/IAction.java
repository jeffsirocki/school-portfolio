package builder.actions;

/**
 * Interface for Builder Actions to allow undo/redo capability.
 * @author Nicholas Panzarino
 */
public interface IAction {
	
	/**
	 * Complete current action.
	 * @return boolean
	 */
	public boolean doAction();
	
	/**
	 * Undo current action.
	 * @return boolean
	 */
	public boolean undoAction();
}
