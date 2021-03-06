package screens;

import javax.swing.JFrame;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JButton;

public class MainScreen extends JFrame {
	private JTextField textField;
	
	public MainScreen () {
		setTitle("Main Screen");
		setSize(600, 300);
		textField = new JTextField();
		textField.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Input:");
		
		JButton btnDoesNothing = new JButton("Does Nothing");
		
		JButton btnNextScreen = new JButton("Next Screen");
		
		// I typed NONE of this. This was all generated by Window Builder. Try to ignore it...
		
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblNewLabel)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addComponent(btnDoesNothing)
					.addContainerGap(197, Short.MAX_VALUE))
				.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
					.addContainerGap(335, Short.MAX_VALUE)
					.addComponent(btnNextScreen)
					.addContainerGap())
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(37)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNewLabel)
						.addComponent(btnDoesNothing))
					.addPreferredGap(ComponentPlacement.RELATED, 168, Short.MAX_VALUE)
					.addComponent(btnNextScreen)
					.addContainerGap())
		);
		getContentPane().setLayout(groupLayout);
		
		/////// NOW BELOW IS WHAT I TYPED
		btnNextScreen.addActionListener(new NextScreenController(this));
		
	}
}
