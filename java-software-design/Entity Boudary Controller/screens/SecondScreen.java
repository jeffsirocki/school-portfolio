package screens;

import javax.swing.JFrame;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.LayoutStyle.ComponentPlacement;

public class SecondScreen extends JFrame {
	JButton btnPrevious;
	
	public SecondScreen() {
		setTitle("Second Screen");
		setSize(600, 300);
		JLabel lblSecondScreen = new JLabel("Second Screen");
		
		// I made this a class attribute
		btnPrevious = new JButton("Previous");
		
		JButton btnNext = new JButton("Next...");
		btnNext.setEnabled(false);
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(26)
							.addComponent(lblSecondScreen))
						.addGroup(groupLayout.createSequentialGroup()
							.addContainerGap()
							.addComponent(btnPrevious)
							.addPreferredGap(ComponentPlacement.RELATED, 252, Short.MAX_VALUE)
							.addComponent(btnNext)))
					.addContainerGap())
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(23)
					.addComponent(lblSecondScreen)
					.addPreferredGap(ComponentPlacement.RELATED, 191, Short.MAX_VALUE)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnPrevious)
						.addComponent(btnNext))
					.addContainerGap())
		);
		getContentPane().setLayout(groupLayout);
	}

	// I added this
	public JButton getPreviousButton() {
		return btnPrevious;
	}

}
