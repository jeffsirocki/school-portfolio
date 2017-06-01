package player.controllers;

import java.awt.Component;
import java.awt.event.MouseAdapter;

/**
 * Abstract controller for navigating between windows in the Player Application
 * @author Nicholas Panzarino
 *
 */
public abstract class AbstractPlayerController extends MouseAdapter {
	
	/**
	 * Used to set all windows in the Player application invisible.
	 */
	final void setAllInvisible(){
		for(Component c:PlayerAppReference.get().getLayeredPane().getComponents()){
			c.setVisible(false);
		}
	}
	
	/**
	 * Displays only the input Component in the Player Application. Must be already added to the application
	 * @param show
	 */
	final void showOnly(Component show){
		setAllInvisible();
		show.setVisible(true);
	}
}
