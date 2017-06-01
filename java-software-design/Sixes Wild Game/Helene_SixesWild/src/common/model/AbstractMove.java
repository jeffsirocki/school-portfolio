package common.model;

import java.lang.reflect.InvocationTargetException;

import common.controllers.BoardSelectionController;

/**
 * Defines Move objects/classes. Moves can be created, performed and scored. 
 * They also must have a controller type capable of performing them.
 * @author Nicholas Panzarino
 *
 */
public abstract class AbstractMove extends AbstractType{
	
	/**
	 * Performs this move on some entity object(s). 
	 * Returns true if the move was made successfully, or false if
	 * the move was unsuccessful. If unsuccessful, no states should have been modified.
	 * @return
	 */
	public abstract boolean doMove();
	
	/**
	 * Retruns whether or not this move is classified as a special move.
	 * @return
	 */
	public boolean isSpecial(){
		return true;
	}
	
	/**
	 * Static Method that Determines the String value of this type of Square.
	 * This will be displayed in ComboBoxes.
	 * All subclasses should overwrite/hide this method.
	 * @return
	 */
	public static String type(){
		throw new IllegalArgumentException("A Subclass of AbstractMove does not declare a 'public static String type()' Method.");
	}
	
	/**
	 * Allows instances of objects to return a controller that can perform their move.
	 * @return
	 */
	public final BoardSelectionController getController(){
		try {
			return (BoardSelectionController) this.getClass().getMethod("controller").invoke(null);
		} catch (IllegalAccessException | IllegalArgumentException
				| InvocationTargetException | NoSuchMethodException
				| SecurityException e) {
			System.err.println("Could not get controller from Class: "+this.getClass().getSimpleName());
			//e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * Allows a user to get a controller of a parameterized move class during runtime.
	 * @param c
	 * @return
	 */
	public static final BoardSelectionController controller(Class<? extends AbstractMove> c){
		try {
			return (BoardSelectionController)c.getMethod("controller").invoke(null);
		} catch (IllegalAccessException | IllegalArgumentException
				| InvocationTargetException | NoSuchMethodException
				| SecurityException e) {
			
			System.err.println("Could not get controller from Class: "+c.getSimpleName());
			//e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * returns a controller object that can perform this move.
	 * all subclasses should override/hide this method.
	 * @return
	 */
	public static BoardSelectionController controller(){
		throw new IllegalArgumentException("A Subclass of AbstractMove does not declare a 'public static MouseAdapter getController()' Method.");
	}
	

	/**
	 * Return the score change resulting from this move.
	 * @return
	 */
	public int getScore() {
		return 0;
	}
}
