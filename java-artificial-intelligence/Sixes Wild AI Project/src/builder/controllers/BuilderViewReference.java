package builder.controllers;

import builder.view.BuilderView;

/**
 * This Class is used to maintain a static link to a single BuilderView object.
 * This allows controllers with no direct reference to the BuilderView to modify fields and cause an update.
 * @author njpanzarino
 * @author jasirocki - jdoc
 */
public final class BuilderViewReference {

	/**
	 * BuilderView object.
	 */
	private static BuilderView view=null;
	
	/**
	 * Constructor for the builderview referencable object.
	 */
	private BuilderViewReference(){
		
	}
	
	/**
	 * Used to set the BoardView Reference. Can only be done ONCE. After the reference is first
	 * set it cannot be changed.
	 * @param view
	 */
	public static void set(BuilderView view){
		if(BuilderViewReference.view==null){
			BuilderViewReference.view=view;
		}
	}
	
	/**
	 * Can be used by any class or object at any time to get a reference to the BoardView
	 * and all it's accessible fields.
	 * @return
	 */
	public static BuilderView get(){
		return BuilderViewReference.view;
	}
	
	/**
	 * Can be used by any class or object at any time to trigger an update to the BoardView.
	 */
	public static void updateFields(){
		BuilderViewReference.view.updateFields();
	}
	
	/**
	 * resets the buillderViewReference to null so it can be modified again.
	 */
	public static void clear(){
		BuilderViewReference.view=null;
	}
}
