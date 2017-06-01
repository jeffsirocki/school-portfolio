package player.controllers;

import player.PlayerApplication;

/**
 * Can be used by Boundary objects as a reference to the highest-level application.
 * @author Nicholas Panzarino
 *
 */
public final class PlayerAppReference{
	
	/**
	 * Static reference to a single application attribute.
	 */
	private static PlayerApplication app=null;
	
	/**
	 * Private default constructor so no instances of this class can be made.
	 */
	private PlayerAppReference(){
		
	}
	
	/**
	 * Static method to set the PlayerApplication. if an application already exists it will NOT override it.
	 * @param app the application to make the global reference
	 */
	public static void set(PlayerApplication app){
		if(PlayerAppReference.app==null){
			PlayerAppReference.app=app;
		}
	}
	
	/**
	 * Returns the Player Application.
	 * @return
	 */
	public static PlayerApplication get(){
		return PlayerAppReference.app;
	}
	
	/**
	 * calls the updateFields method in the playerApplication.
	 */
	public static void updateFields(){
		PlayerAppReference.app.updateFields();
	}
	
	/**
	 * Remove the Application reference so new ones can be added.
	 * Used for testing
	 */
	public static void clear(){
		PlayerAppReference.app=null;
	}
}
