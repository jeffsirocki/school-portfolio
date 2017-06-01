package player.view;

import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.SpringLayout;
import java.awt.Font;
import java.awt.Color;

import javax.swing.JButton;

import player.PlayerApplication;
import player.controllers.PlayerAppReference;
import player.controllers.ToLevelSelectController;

/**
 * Window to be displayed at the start of the application.
 * @author Nicholas Panzarino
 *
 */
public class StartScreenView extends JPanel {
	private static final long serialVersionUID = 4839288745352557403L;
	
	/**
	 * reference to the highest level application.
	 */
	PlayerApplication app;
	
	/**
	 * Create the panel.
	 */
	public StartScreenView(PlayerApplication app) {
		setBackground(new Color(51, 153, 255));
		setLayout(new SpringLayout());
		
		JLabel label = new JLabel("sixes");
		label.setForeground(Color.WHITE);
		label.setFont(new Font("Dialog", Font.PLAIN, 32));
		add(label);
		((SpringLayout)getLayout()).putConstraint(SpringLayout.EAST, label, PlayerAppReference.get().getFrame().getWidth()/2, SpringLayout.WEST, PlayerAppReference.get().getFrame().getContentPane());
		((SpringLayout)getLayout()).putConstraint(SpringLayout.NORTH, label, PlayerAppReference.get().getFrame().getHeight()/5, SpringLayout.NORTH, PlayerAppReference.get().getFrame().getContentPane());
		
		JLabel label_1 = new JLabel("wild");
		label_1.setForeground((Color) null);
		label_1.setFont(new Font("Dialog", Font.PLAIN, 32));
		add(label_1);
		((SpringLayout)getLayout()).putConstraint(SpringLayout.WEST, label_1, 0, SpringLayout.EAST, label);
		((SpringLayout)getLayout()).putConstraint(SpringLayout.NORTH, label_1, 0, SpringLayout.NORTH, label);
		
		JButton button = new JButton("Play Game");
		button.addMouseListener(new ToLevelSelectController());
		button.setFont(new Font("Dialog", Font.PLAIN, 13));
		add(button);
		((SpringLayout)getLayout()).putConstraint(SpringLayout.HORIZONTAL_CENTER, button, -7, SpringLayout.EAST, label);
		((SpringLayout)getLayout()).putConstraint(SpringLayout.NORTH, button, 10, SpringLayout.SOUTH, label);
		
		JButton button_1 = new JButton("Help");
		button_1.setFont(new Font("Dialog", Font.PLAIN, 13));
		add(button_1);
		((SpringLayout)getLayout()).putConstraint(SpringLayout.HORIZONTAL_CENTER, button_1, 0, SpringLayout.HORIZONTAL_CENTER, button);
		((SpringLayout)getLayout()).putConstraint(SpringLayout.NORTH, button_1, 10, SpringLayout.SOUTH, button);
		button_1.setVisible(false);
		
		JLabel label_2 = new JLabel("Helene Inc.");
		label_2.setForeground(Color.WHITE);
		label_2.setFont(new Font("Dialog", Font.PLAIN, 14));
		add(label_2);
		((SpringLayout)getLayout()).putConstraint(SpringLayout.HORIZONTAL_CENTER, label_2, 0, SpringLayout.HORIZONTAL_CENTER, button);
		((SpringLayout)getLayout()).putConstraint(SpringLayout.NORTH, label_2, 130, SpringLayout.SOUTH, button_1);
	}
}
