package screens;

/** Class to simply launch the GUI. */
public class MainScreensDemo {
	
	/** Launch GUI by installing window controller on exit. */ 
	public static void main (String args[]) {
		MainScreen ms = new MainScreen();
		
		// launch everything and go!
		ms.setVisible (true);
	}
}
