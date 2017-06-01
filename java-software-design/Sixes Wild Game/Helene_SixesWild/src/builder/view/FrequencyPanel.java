package builder.view;

import java.awt.Component;
import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JSlider;
import javax.swing.JTextField;
import javax.swing.SpringLayout;

import builder.actions.CopyStateAction;
import builder.controllers.BuilderViewReference;
import builder.controllers.FrequencyChangeController;
import common.model.Frequency;
import common.model.FrequencyList;
import common.model.ICopy;

import javax.swing.SwingConstants;
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * Allows the creation of ParameterPanels which can control FrequencyList Objects.
 * Each Field has a Label, Slider, and text field.
 * Fields can be added one at a time or the object can be initialized from a FrequencyList.
 * If initialized with a frequencyList, the Panel will automatically assign controllers to each field.
 * 
 * @author Nicholas Panzarino
 * @author tjbennett - windowbuilder
 * @author jasirocki - jdoc
 * @author jmmckinney
 */
public class FrequencyPanel extends ParameterPanel{
	
	final int EDGE_GAP=12;
	final int X_SPACING=7;
	final int Y_SPACING=12;
	final int LABEL_WIDTH=32;
	final int TEXT_WIDTH=32;
	final int HEIGHT=26;
	
	/**
	 * Name of the panel.
	 */
	String panelName="Panel Name";
	
	/**
	 * Label prefix.
	 */
	String labelPrefix="";
	
	/**
	 * Frequencies set in view.
	 */
	FrequencyList freqs;
	
	/**
	 * Text field for inputting frequency.
	 */
	protected ArrayList<JTextField> textFields=new ArrayList<JTextField>();
	
	/**
	 * Slider for choosing frequency.
	 */
	protected ArrayList<JSlider> sliders=new ArrayList<JSlider>();
	
	/**
	 * Value of frequency.
	 */
	protected ArrayList<Integer> values=new ArrayList<Integer>();
	
	/**
	 * Layout of Panel.
	 */
	SpringLayout springLayout;

	/**
	 * Create the panel.
	 */
	public FrequencyPanel() {
		
		springLayout = new SpringLayout();
		setLayout(springLayout);
		addField("a");
	}
	
	/**
	 * Update reference and reinitialize all components to match it.
	 * @param freqs
	 */
	public void initialize(FrequencyList freqs){
		this.removeAll();
		textFields=new ArrayList<JTextField>();
		sliders=new ArrayList<JSlider>();
		values=new ArrayList<Integer>();
		
		springLayout = new SpringLayout();
		setLayout(springLayout);
		
		this.freqs=freqs;
		
		int i=0;
		for(Frequency f:this.freqs.getList()){
			final int n=i;
			addField(""+f.getValue());
			values.add(f.getValue());
			
			//Handles the live update of sliders
			sliders.get(n).addChangeListener(new ChangeListener() {
				public void stateChanged(ChangeEvent arg0) {
					
					if(FrequencyPanel.this.freqs==null)
						return;
					
					int old=(int) FrequencyPanel.this.freqs.getFreq(FrequencyPanel.this.values.get(n));
					int current=FrequencyPanel.this.sliders.get(n).getValue();
					if(old!=current){
						FrequencyPanel.this.textFields.get(n).setText(""+FrequencyPanel.this.sliders.get(n).getValue());
						FrequencyPanel.this.freqs.setFreq(FrequencyPanel.this.values.get(n),current);
						FrequencyPanel.this.updateFields();
						FrequencyChangeController.changed=true;
					}
				}
			});
			
			sliders.get(n).addMouseListener(new FrequencyChangeController(FrequencyPanel.this, n));
			
			textFields.get(n).addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					if(FrequencyPanel.this.freqs==null)
						return;
					
					CopyStateAction<FrequencyList> action = new CopyStateAction<FrequencyList>(FrequencyPanel.this.freqs);
					FrequencyList fin = action.getFinal();
					fin.setFreq(FrequencyPanel.this.values.get(n), Integer.parseInt(FrequencyPanel.this.textFields.get(n).getText()));
					action.setFinal(fin);
					
					BuilderViewReference.get().getModel().doAction(action);
					FrequencyPanel.this.updateFields();
					
				}
			});
			i++;
		}
		updateFields();
	}
	
	/**
	 * Adds a JLabel, JSlider and JTextField to the panel.
	 * @param str the String to display in the label.
	 */
	public void addField(String str){
		Component c=this;
		String dir=SpringLayout.NORTH;
		
		if(sliders.size()>0){
			c=sliders.get(sliders.size()-1);
			dir=SpringLayout.SOUTH;
		}
		
		JLabel label = new JLabel(getLabelPrefix()+str);
		label.setHorizontalAlignment(SwingConstants.RIGHT);
		
		JSlider slider = new JSlider();
		slider.setMinimum(Frequency.MIN_FREQ);
		slider.setMaximum(Frequency.MAX_FREQ);
		slider.setOpaque(false);
		
		JTextField textField = new JTextField();
		textField.setColumns(10);
		
		springLayout.putConstraint(SpringLayout.NORTH, label, 0, SpringLayout.NORTH, slider);
		springLayout.putConstraint(SpringLayout.SOUTH, label, 0, SpringLayout.SOUTH, slider);
		springLayout.putConstraint(SpringLayout.NORTH, textField, 0, SpringLayout.NORTH, slider);
		springLayout.putConstraint(SpringLayout.SOUTH, textField, 0, SpringLayout.SOUTH, slider);
		springLayout.putConstraint(SpringLayout.WEST, textField, -EDGE_GAP-TEXT_WIDTH, SpringLayout.EAST, this);
		springLayout.putConstraint(SpringLayout.EAST, textField, -EDGE_GAP, SpringLayout.EAST, this);
		springLayout.putConstraint(SpringLayout.WEST, slider, X_SPACING, SpringLayout.EAST, label);
		springLayout.putConstraint(SpringLayout.EAST, slider, -X_SPACING, SpringLayout.WEST, textField);
		springLayout.putConstraint(SpringLayout.NORTH, slider, Y_SPACING, dir, c);
		springLayout.putConstraint(SpringLayout.SOUTH, slider, Y_SPACING+HEIGHT, dir, c);
		springLayout.putConstraint(SpringLayout.WEST, label, 0, SpringLayout.WEST, this);
		springLayout.putConstraint(SpringLayout.EAST, label, LABEL_WIDTH, SpringLayout.WEST, this);
		
		add(label);
		add(slider);
		add(textField);
		
		sliders.add(slider);
		textFields.add(textField);
	}
	
	/**
	 * The name to display when this panel is placed in a JComboBox.
	 * Subclasses should override this method.
	 */
	@Override
	public String getPanelName() {
		return panelName;
	}

	/**
	 * Set the default name to display when this panel is placed in a text box.
	 * If a subclass overrides the getPanelName() function, this will do nothing.
	 * Useful for creating a unique instance of this class without needing to extend and make a subclass.
	 */
	public void setPanelName(String panelName) {
		this.panelName = panelName;
	}

	/**
	 * Similar to getPanelName. 
	 * This String will be added to the front of the JLabel text.
	 * @return
	 */
	public String getLabelPrefix() {
		return labelPrefix;
	}

	/**
	 * Similar to setPanelName. 
	 * Set default String to be added to the front of the JLabel text.
	 * @param labelPrefix
	 */
	public void setLabelPrefix(String labelPrefix) {
		this.labelPrefix = labelPrefix;
	}

	/**
	 * Update the panel, validate frequencies.
	 */
	@Override
	public void updateFields() {
		if(freqs==null)
			return;
		
		for(Frequency f:freqs){
			int i=values.indexOf(f.getValue());
			float val=f.getFrequency();
			
			if(i<sliders.size())
				sliders.get(i).setValue((int) val);
			if(i<textFields.size())
				textFields.get(i).setText(""+(int)val);
			i++;
		}
	}

	/**
	 * Removes all associations and controllers from this panel, but keeps the Labels, Sliders and TextFields.
	 */
	public void clear(){
		this.removeAll();
		textFields=new ArrayList<JTextField>();
		sliders=new ArrayList<JSlider>();
		
		springLayout = new SpringLayout();
		setLayout(springLayout);
		
		for(Integer i:values){
			addField(i.toString());
		}
		values=new ArrayList<Integer>();
	}
	
	/**
	 * Get the frequency list from this object.
	 * @return
	 */
	public FrequencyList getFrequencyList()
	{
		return FrequencyPanel.this.freqs;
	}
	
	/**
	 * Get the frequency value at the given location in the frequency value array list.
	 * @param n
	 * @return
	 */
	public int getValue(int n)
	{
		return this.values.get(n);
	}
}
