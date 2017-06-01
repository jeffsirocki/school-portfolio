package builder.view;

import javax.swing.JPanel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLayeredPane;
import javax.swing.JComboBox;

import java.awt.Component;

import javax.swing.SpringLayout;

import common.view.IUpdateFields;

import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;

/**
 * This class holds, displays and manages Paramater panel types.
 * @author Nicholas Panzarino
 */
public class LevelParameterView extends JPanel implements IUpdateFields{

	/**
	 * Serializable ID.
	 */
	private static final long serialVersionUID = 7806719089274726806L;
	
	/**
	 * Combo box which holds and displays all parameters. 
	 */
	JComboBox<ParameterPanel> comboBox;
	
	JLayeredPane layeredPane;
	SpringLayout sl_layeredPane;
	
	/**
	 * Create the panel.
	 */
	public LevelParameterView() {
		
		layeredPane = new JLayeredPane();
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addComponent(layeredPane, GroupLayout.DEFAULT_SIZE, 639, Short.MAX_VALUE)
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addComponent(layeredPane)
		);
		
		setLayout(groupLayout);
		
		sl_layeredPane = new SpringLayout();
		layeredPane.setLayout(sl_layeredPane);
		
		//comboBox = new JComboBox(new Object[]{});
		comboBox = new JComboBox<ParameterPanel>(new ParameterPanel[]{});
		comboBox.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				updateView();
			}
		});
		sl_layeredPane.putConstraint(SpringLayout.EAST, comboBox, -30, SpringLayout.EAST, layeredPane);
		//comboBox.setModel(new DefaultComboBoxModel(new String[] {"ParameterTypeSelections", "apple"}));
		comboBox.setEditable(false);
		sl_layeredPane.putConstraint(SpringLayout.NORTH, comboBox, 13, SpringLayout.NORTH, layeredPane);
		sl_layeredPane.putConstraint(SpringLayout.WEST, comboBox, 30, SpringLayout.WEST, layeredPane);
		layeredPane.add(comboBox);
		
		//TODO: Add panels AFTER the object has been initialized, to allow more flexibility as to which panels are avaliable
		addParameterPanel(new TileFrequencyPanel());
		addParameterPanel(new BonusFrequencyPanel());
		addParameterPanel(new StarThresholdPanel());
		addParameterPanel(new SpecialMovePanel());
		addParameterPanel(new FillFromPanel());
		
	}
	
	/**
	 * Adds a new ParameterPanel object to this Panel.
	 * Sets the constraints and constrains it to the size of this panel.
	 * Updates the ComboBox and makes the new Panel selectable.
	 * @param p
	 */
	public void addParameterPanel(ParameterPanel p){
		if(p==null){
			return;
		}
		//p.setBackground(this.getBackground());
		p.setOpaque(false);
		sl_layeredPane.putConstraint(SpringLayout.NORTH, p, 12, SpringLayout.SOUTH, comboBox);
		sl_layeredPane.putConstraint(SpringLayout.WEST, p, 0, SpringLayout.WEST, layeredPane);
		sl_layeredPane.putConstraint(SpringLayout.SOUTH, p, 0, SpringLayout.SOUTH, layeredPane);
		sl_layeredPane.putConstraint(SpringLayout.EAST, p, 0, SpringLayout.EAST, layeredPane);
		layeredPane.add(p);
		comboBox.addItem(p);
		
		updateView();
	}
	
	/**
	 * Remove the input ParameterPanel from the parameterView.
	 * @param p
	 */
	public void removeParameterPanel(ParameterPanel p){
		if(p==null){
			return;
		}
		
		for(int i=0;i<comboBox.getItemCount();i++){
			ParameterPanel panel=comboBox.getItemAt(i);
			if(panel.getPanelName().equals(p.getPanelName())){
				comboBox.removeItem(panel);
			}
		}
		
		updateView();
	}
	
	/**
	 * Makes only the input ParameterPanel visible.
	 * @param c: the component to show
	 */
	public void showOnly(Component c){
		for(int i=0;i<comboBox.getItemCount();i++){
			comboBox.getItemAt(i).setVisible(false);
		}
		c.setVisible(true);
	}
	
	/**
	 * Makes visible only the Panel selected in the comboBox.
	 */
	public void updateView(){
		showOnly(comboBox.getItemAt(comboBox.getSelectedIndex()));
	}

	/**
	 * Updates the internal fields of all ParameterPanel objects in the comboBox.
	 */
	@Override
	public void updateFields() {
		//Update fields of all inner sub panels
		for(int i=0;i<comboBox.getItemCount();i++){
			comboBox.getItemAt(i).updateFields();
		}
	}
}
