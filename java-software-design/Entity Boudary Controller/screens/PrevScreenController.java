package screens;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PrevScreenController implements ActionListener {

	MainScreen main;
	SecondScreen second;
	
	public PrevScreenController(MainScreen ms, SecondScreen sc) {
		this.main = ms;
		this.second = sc;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		main.setVisible(true);
		second.setVisible(false);
	}

}
