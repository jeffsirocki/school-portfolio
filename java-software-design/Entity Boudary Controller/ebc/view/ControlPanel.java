package ebc.view;

import ebc.model.*;
import java.awt.*;

/**
 * Graphical elements used to present control widgets to the user.
 */
public class ControlPanel extends Panel implements IModelUpdated {

	/** Model. */
	Model model;
	
	/** GUI widgets used only in this class. */
	Scrollbar scrollbarH = null;
	Scrollbar scrollbarW = null;
	Scrollbar scrollbarC = null;
	
	/** View is told the model so it can properly represent it. */
	public ControlPanel (Model m) {
		super();

		this.model = m;

		initialize();
	}

	/**
	 * Initialize the panel to contain all control and view widgets
	 */
	void initialize() {
		Label labelC = new Label();
		labelC.setBounds(new Rectangle(155, 9, 45, 23));
		labelC.setText("Color");
		Label labelW = new Label();
		labelW.setBounds(new Rectangle(87, 9, 51, 23));
		labelW.setText("Width");
		Label labelH = new Label();
		labelH.setText("Height");
		labelH.setBounds(new Rectangle(11, 9, 58, 23));

		setLayout(null);
		setBounds(new Rectangle(14, 44, 226, 167));
		add(labelH);
		add(labelW);
		
		// add GUI elements and install controllers
		add(getScrollbarH());
		add(getScrollbarW());
		add(getScrollbarC());
		add(labelC);
	}
	
	/**
	 * Instantiate and/or return the height scrollbar
	 */
	public Scrollbar getScrollbarH() {
		if (scrollbarH == null) {
			scrollbarH = new Scrollbar();
			scrollbarH.setBounds(new Rectangle(14, 40, 16, 100));

			Value val = model.getHeight();
			int pageSize = (val.getMaximum() - val.getMinimum())/8;
			scrollbarH.setValues(val.getValue(), pageSize, val.getMinimum(), val.getMaximum() + pageSize);
		}
		return scrollbarH;
	}

	/**
	 * Instantiate and/or return the width scrollbar
	 */
	public Scrollbar getScrollbarW() {
		if (scrollbarW == null) {
			scrollbarW = new Scrollbar();
			scrollbarW.setBounds(new Rectangle(90, 40, 16, 100));

			Value val = model.getWidth();
			int pageSize = (val.getMaximum() - val.getMinimum())/8;
			scrollbarW.setValues(val.getValue(), pageSize, val.getMinimum(), val.getMaximum() + pageSize);
		}
		return scrollbarW;
	}

	/**
	 * Instantiate and/or return the color scroll bar.
	 */
	public Scrollbar getScrollbarC() {
		if (scrollbarC == null) {
			scrollbarC = new Scrollbar();
			scrollbarC.setBounds(new Rectangle(158, 40, 16, 100));

			Value val = model.getColor();
			int pageSize = (val.getMaximum() - val.getMinimum())/8;
			scrollbarC.setValues(val.getValue(), pageSize, val.getMinimum(), val.getMaximum() + pageSize);
		}
		return scrollbarC;
	}

	/**
	 * Properly respond to updates to model.
	 */
	public void modelChanged() {
		scrollbarH.setValue(model.getHeight().getValue());
		scrollbarW.setValue(model.getWidth().getValue());
		scrollbarC.setValue(model.getColor().getValue());
	}
}
