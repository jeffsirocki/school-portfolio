package gameModes.puzzle;

import java.awt.FlowLayout;

import javax.swing.JLabel;
import javax.swing.JTextField;

import builder.controllers.BuilderViewReference;
import builder.view.ParameterPanel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

/**
 * Panel that contains puzzle level parameters.
 * @author rlamsal-jdoc
 *
 */
public class PuzzleParameterPanel extends ParameterPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = -4028480665373064320L;
	private JTextField txtMaxMoves;

	/**
	 * Create the panel.
	 */
	public PuzzleParameterPanel() {
		setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JLabel lblMaxMoves = new JLabel("Max Moves: ");
		add(lblMaxMoves);
		
		txtMaxMoves = new JTextField();
		txtMaxMoves.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PuzzleMoveAction act = new PuzzleMoveAction(Integer.parseInt(txtMaxMoves.getText()));
				BuilderViewReference.get().getModel().doAction(act);
			}
		});
		add(txtMaxMoves);
		txtMaxMoves.setColumns(5);

	}

	/**
	 * gets the string name of the panel.
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
			int maxMoves=((PuzzleLevel)BuilderViewReference.get().getModel().getCurrentLevel()).maxMoves;
			txtMaxMoves.setText(String.valueOf(maxMoves));
		}catch(Exception e){
			txtMaxMoves.setText("");
		}
	}

}
