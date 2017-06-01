package screens;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class NextScreenController implements ActionListener {

	MainScreen main;
	
	public NextScreenController(MainScreen ms) {
		this.main = ms;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		main.setVisible(false);
		
		// create screen
		SecondScreen secondScreen = new SecondScreen();
		secondScreen.setVisible(true);

		// set controller for previous button
		secondScreen.getPreviousButton().addActionListener(new PrevScreenController(main, secondScreen));
		
	}

}
