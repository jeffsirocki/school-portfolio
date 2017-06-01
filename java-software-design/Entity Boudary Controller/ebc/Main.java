package ebc;

import java.awt.event.WindowEvent;
import java.awt.event.WindowAdapter;

import ebc.model.Model;
import ebc.view.Application;

/** Class to simply launch the GUI. */
public class Main {
	
	/** Launch GUI by installing window controller on exit. */ 
	public static void main (String args[]) {
		// Create the entity objects that form the basis of our model
		Model m = new Model();
		
		// In some cases we can only construct the view once the model is available.
		// In other cases, we can construct the View object and then configure it
		// later with the model object. In this example, we show the former. 
		final Application pr = new Application(m);
		
		// This controller is an anonymous class who responds to closing events.
		// by exiting the application.
		pr.addWindowListener (new WindowAdapter() {

			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
			
			/** Once window is activated, start off by forcing views to update. */
			public void windowActivated(WindowEvent e) {
				pr.modelChanged();
			}
		});
			
		// launch everything and go!
		pr.setVisible (true);
	}
}
