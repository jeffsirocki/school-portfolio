package ebc.view;

import java.awt.Frame;
import ebc.model.Model;

/**
 * The containing Frame for the application. This class is nothing more 
 * than a GUI shell, and the real logic happens in the ApplicationPanel.
 */
public class Application extends Frame implements IModelUpdated {
	
	/** Inner GUI panel containing everything. */
	ApplicationPanel panel;
	
	/**
	 * This is the default constructor
	 */
	public Application(Model m) {
		super();
		
		setTitle("Sample Application");
		setSize(633, 490);
		
		panel = new ApplicationPanel(m);
		// Eliminate this *Second* call to initialize, otherwise multiple controllers instantiated
		// and double-events are received...
		//panel.initialize();
		
		add (panel);
	}

	/** Retrieve the inner panel. */
	public ApplicationPanel getInnerPanel() {
		return panel;
	}
	
	/** Delegate this request to the inner panel. */
	public void modelChanged() {
		panel.modelChanged();
	}	
} 
