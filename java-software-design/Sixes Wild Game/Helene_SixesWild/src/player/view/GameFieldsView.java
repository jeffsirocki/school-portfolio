package player.view;

import java.awt.Component;
import java.util.ArrayList;

import javax.swing.JPanel;
import javax.swing.SpringLayout;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

/**
 * JPanel for displaying String fields specific to each game type.
 * @author Nicholas Panzarino
 *
 */
public class GameFieldsView extends JPanel {
	
	final int Y_SPACING=10;
	final int RIGHT_OFFSET=0;
	
	/**
	 * Maintain a reference to the layout so it can be added to.
	 */
	SpringLayout springLayout;
	
	/**
	 * List of all labels displayed in this panel
	 */
	ArrayList<JLabel> labels;

	/**
	 * Create the panel.
	 */
	public GameFieldsView() {
		springLayout = new SpringLayout();
		setLayout(springLayout);
		
		labels= new ArrayList<JLabel>();
	}
	
	/**
	 * re-initialize this panel to display the given Strings.
	 */
	public void initialize(ArrayList<String> str){
		this.removeAll();
		labels=new ArrayList<JLabel>();
		
		springLayout = new SpringLayout();
		setLayout(springLayout);
		
		for(String s:str){
			addField(s);
		}
	}
	
	/**
	 * Adds the string as a label centered underneath the previous label
	 */
	public void addField(String str){
		Component c=this;
		String dir=SpringLayout.NORTH;
		
		if(labels.size()>0){
			c=labels.get(labels.size()-1);
			dir=SpringLayout.SOUTH;
		}
		
		JLabel lblAny = new JLabel(str);
		lblAny.setHorizontalAlignment(SwingConstants.CENTER);
		springLayout.putConstraint(SpringLayout.NORTH, lblAny, Y_SPACING, dir, c);
		springLayout.putConstraint(SpringLayout.WEST, lblAny, 0, SpringLayout.WEST, this);
		springLayout.putConstraint(SpringLayout.EAST, lblAny, RIGHT_OFFSET, SpringLayout.EAST, this);
		add(lblAny);
		
		labels.add(lblAny);
	}

}
