package common.model;

import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;

/**
 * Allows Classes to be represented by a user defined String.
 * -Used to distinguish between object types in a way that can be Displayed in Fields and understood by a user.
 * -All subclasses must have a method that hides 'public static string type()'.
 * -All other functionality is automatic based on that single static function.
 * @author Nicholas Panzarino
 */
public class AbstractType implements Serializable {
	
	/**
	 * Serializable ID.
	 */
	private static final long serialVersionUID = -8303954416976988023L;
	
	/**
	 * Allows instances of objects to return the String representation of their class type.
	 * @return
	 */
	public final String getType(){
		try {
			return (String) this.getClass().getMethod("type").invoke(null);
		} catch (IllegalAccessException | IllegalArgumentException
				| InvocationTargetException | NoSuchMethodException
				| SecurityException e) {
			System.err.println("Problem with Class: "+this.getClass().getSimpleName());
			//e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * Allows a user to determine the String type() of a parameterized class during runtime.
	 * @param c
	 * @return
	 */
	public static final String type(Class<? extends AbstractType> c){
		try {
			return (String)c.getMethod("type").invoke(null);
		} catch (IllegalAccessException | IllegalArgumentException
				| InvocationTargetException | NoSuchMethodException
				| SecurityException e) {
			
			System.err.println("Problem with Class: "+c.getSimpleName());
			//e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * Static Method that Determines the String value of this type of Class.
	 * This will be displayed in ComboBoxes or in String fields.
	 * All subclasses should override/hide this method.
	 * @return
	 */
	public static String type(){
		throw new IllegalArgumentException("A Subclass of AbstractType does not declare a 'public static String type()' Method.");
	}
}
