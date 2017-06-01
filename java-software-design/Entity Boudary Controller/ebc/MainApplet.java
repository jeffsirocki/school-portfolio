package ebc;

import java.applet.Applet;
import ebc.model.Model;
import ebc.view.ApplicationPanel;

/** Class to simply launch the GUI. */
public class MainApplet extends Applet {
	
	/** Applets overrider init method functionality. */
	@Override
	public void init () {
		setSize(633, 490);
		// Create the entity objects that form the basis of our model
		Model m = new Model();
		
		// Only need to insert ApplicationPanel
		ApplicationPanel panel = new ApplicationPanel(m);
		add(panel);
		panel.modelChanged();
	}
}
