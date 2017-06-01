package gameModes.elimination;

import java.awt.FlowLayout;

import javax.swing.JLabel;
import javax.swing.JTextField;

import builder.controllers.BuilderViewReference;
import builder.view.ParameterPanel;
import common.model.FrequencyList;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

/**
 * Panel that contains the elimination parameters.
 * @author rlamsal-jdoc
 *
 */
public class EliminationParameterPanel extends ParameterPanel {
	private JTextField txtMaxMoves;

	/**
	 * Create the panel.
	 */
	public EliminationParameterPanel() {
		setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JLabel lblMaxMoves = new JLabel("Max Moves: ");
		add(lblMaxMoves);
		
		txtMaxMoves = new JTextField();
		txtMaxMoves.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				EliminationMoveAction act = new EliminationMoveAction(Integer.parseInt(txtMaxMoves.getText()));
				BuilderViewReference.get().getModel().doAction(act);
			}
		});
		add(txtMaxMoves);
		txtMaxMoves.setColumns(5);

	}

	/**
	 * returns a string with the panel name.
	 */
	@Override
	public String getPanelName() {
		return "Maximum Moves";
	}

	/**
	 * updates the maximum moves.
	 */
	@Override
	public void updateFields() {
		try{
			int maxMoves=((EliminationLevel)BuilderViewReference.get().getModel().getCurrentLevel()).maxMoves;
			txtMaxMoves.setText(String.valueOf(maxMoves));
		}catch(Exception e){
			txtMaxMoves.setText("");
		}
	}

}
