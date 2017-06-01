package gameModes.lightning;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;
import javax.swing.JTextField;

import builder.controllers.BuilderViewReference;
import builder.view.ParameterPanel;

/**
 * Panel that contains the lightning level parameters.
 * @author rlamsal-jdoc
 *
 */
public class LightningParameterPanel extends ParameterPanel {
	private JTextField txtMaxSeconds;

	/**
	 * Create the panel.
	 */
	public LightningParameterPanel() {
		setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JLabel lblMaxMoves = new JLabel("Max Time: ");
		add(lblMaxMoves);
		
		txtMaxSeconds = new JTextField();
		txtMaxSeconds.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				LightningTimeAction act = new LightningTimeAction(Integer.parseInt(txtMaxSeconds.getText()));
				BuilderViewReference.get().getModel().doAction(act);
			}
		});
		add(txtMaxSeconds);
		txtMaxSeconds.setColumns(5);

	}

	/**
	 * returns the panel name.
	 */
	@Override
	public String getPanelName() {
		return "Maximum Time";
	}

	/**
	 * updates the maximum time allowed in a level.
	 */
	@Override
	public void updateFields() {
		try{
			long maxSeconds=((LightningLevel)BuilderViewReference.get().getModel().getCurrentLevel()).getMaxSeconds();
			txtMaxSeconds.setText(String.valueOf(maxSeconds));
		}catch(Exception e){
			txtMaxSeconds.setText("");
		}
	}

}
