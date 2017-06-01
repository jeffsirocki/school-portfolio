package builder.view;

import javax.swing.JPanel;

import common.view.IUpdateFields;

/**
 * Defines a special type of JPanel that can be displayed by a levelParameterView object.
 * @author Nicholas Panzarino
 *
 */
public abstract class ParameterPanel extends JPanel implements IUpdateFields{

	/**
	 * Create the panel.
	 */
	public ParameterPanel() {

	}
	
	/**
	 * Should return the name to display if this panel is inserted into a JComboBox.
	 * @return
	 */
	public abstract String getPanelName();

	
	/**
	 * Returns the value of the panel name.
	 */
	@Override
	public final String toString() {
		return getPanelName();
	}
	
	/**
	 * Updates the internal fields of this panel to reflect changes made to the model.
	 */
	public abstract void updateFields();

}
