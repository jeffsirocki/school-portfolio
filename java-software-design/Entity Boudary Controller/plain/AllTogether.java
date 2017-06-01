package plain;

import java.applet.Applet;
import java.awt.*;
import java.awt.event.*;

/** Launch as an applet to run. */
public class AllTogether extends Applet implements AdjustmentListener, ActionListener {

	/** Height and Width. Color, too. */
	int height = 10;
	int width = 10;
	int color = 0;
	
	/** min/max. */
	int min = 10;
	int max = 120;
	
	/** Prevent warnings in Eclipse. */
	private static final long serialVersionUID = 1L;
	
	// GUI elements.
	private Scrollbar scrollbarH = null;
	private Scrollbar scrollbarW = null;
	private Scrollbar scrollbarC = null;
	private TextField textFieldH = null;
	private TextField textFieldW = null;
	private TextField textFieldC = null;
	
	/**
	 * This method initializes the applet with all proper controls and graphical
	 * view elements.
	 */
	public void init() {
		this.setSize(new Dimension(633, 490));
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
		add(labelH, null);
		add(labelW, null);
		
		int pageSize = (max-min)/8;
		
		scrollbarH = new Scrollbar();
		scrollbarH.setBounds(new Rectangle(14, 40, 16, 100));
		scrollbarH.setValues(height, pageSize, min, max + pageSize);
		
		scrollbarW = new Scrollbar();
		scrollbarW.setBounds(new Rectangle(90, 40, 16, 100));
		scrollbarW.setValues(width, pageSize, min, max + pageSize);
		
		scrollbarC = new Scrollbar();
		scrollbarC.setBounds(new Rectangle(158, 40, 16, 100));
		pageSize = (255)/8;
		scrollbarC.setValues(color, pageSize, 0, 255 + pageSize);
		
		add(scrollbarH, null);
		add(scrollbarW, null);
		add(scrollbarC, null);
		
		add(labelC, null);
		Label labelC1 = new Label();
		labelC1.setBounds(new Rectangle(313, 384, 48, 23));
		labelC1.setText("Color:");
		Label labelW1 = new Label();
		labelW1.setBounds(new Rectangle(313, 353, 53, 23));
		labelW1.setText("Width:");
		Label labelH1 = new Label();
		labelH1.setBounds(new Rectangle(313, 316, 45, 23));
		labelH1.setText("Height:");
		
		textFieldH = new TextField();
		//textFieldH.setEditable(false);
		textFieldH.setBounds(new Rectangle(383, 326, 106, 23));
		textFieldW = new TextField();
		textFieldW.setEditable(false);
		textFieldW.setBounds(new Rectangle(383, 355, 106, 23));
		textFieldC = new TextField();
		textFieldC.setEditable(false);
		textFieldC.setBounds(new Rectangle(383, 385, 106, 23));
		
		add(textFieldH, null);
		add(textFieldW, null);
		add(textFieldC, null);
		
		add(labelH1, null);
		add(labelW1, null);
		add(labelC1, null);
		
		// add controllers for the scrollbars
		scrollbarC.addAdjustmentListener(this);
		scrollbarH.addAdjustmentListener(this);
		scrollbarW.addAdjustmentListener(this);
		
		textFieldH.addActionListener (this);
	}
	
	public void paint (Graphics g) {
		Color c = new Color (color,color,color);
		g.setColor(c);
		g.fillRect(310, 10, width, height);
	}

	public void adjustmentValueChanged(AdjustmentEvent e) {
		if (e.getSource() == scrollbarH) {
			height = e.getValue();
		} else if (e.getSource() == scrollbarW) {
			width = e.getValue();
		} else if (e.getSource() == scrollbarC) {
			color = e.getValue();
		}
		
		// update text fields
		textFieldH.setText("" + height);
		textFieldW.setText("" + width);
		textFieldC.setText("" + color);
		
		repaint();		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == textFieldH) {
			try {
				int h = Integer.valueOf(textFieldH.getText());
				scrollbarH.setValue(h);
				
				height = h; // record the new value
			} catch (NumberFormatException nfe) {
				java.awt.Toolkit.getDefaultToolkit().beep();
				textFieldH.setText("");
			}
		}
		
		repaint();
	}
}  //  @jve:decl-index=0:visual-constraint="10,10"









