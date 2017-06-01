package builder.controllers;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import builder.actions.CopyStateAction;
import builder.view.FrequencyPanel;
import common.model.FrequencyList;

/**
 * Controller that changes the frequencies.
 * @author rlamsal-jdoc
 *
 */
public class FrequencyChangeController extends MouseAdapter{
	
	/**
	 * A generic save/load state action that can be used for undo/redo.
	 */
	CopyStateAction<FrequencyList> action;
	
	/**
	 * slider identifier.
	 */
	int n;
	
	/**
	 * A panel that has a Label, Slider, and text field.
	 */
	FrequencyPanel frequencyPanel;
	
	/**
	 * Boolean to check if frequency has been changed or not.
	 */
	public static boolean changed=false;
	
	/**
	 * Constructor that takes in a Frequency panel and the slider identifier n.
	 * @param frequencyPanel
	 * @param n
	 */
	public FrequencyChangeController(FrequencyPanel frequencyPanel, int n)
	{
		this.frequencyPanel = frequencyPanel;
		action = new CopyStateAction<FrequencyList>(frequencyPanel.getFrequencyList());
		this.n = n;
	}
	
	/**
	 * gets the final frequency on the mouse release and updates the panel.
	 */
	@Override
	public void mouseReleased(MouseEvent e)
	{
		//System.out.println(frequencyPanel.getFrequencyList().getFreq(frequencyPanel.getValue(n)));
		FrequencyList fin = action.getFinal();
		fin.copy(frequencyPanel.getFrequencyList());
		action.setFinal(fin);
		
		if(changed)
			BuilderViewReference.get().getModel().doAction(action);
		
		frequencyPanel.updateFields();
		changed=false;
	}
	
	/**
	 * saves the initial state of the frequency to allow for the undo capability.
	 */
	@Override
	public void mousePressed(MouseEvent e)
	{
		action=new CopyStateAction<FrequencyList>(frequencyPanel.getFrequencyList());
	}
}
