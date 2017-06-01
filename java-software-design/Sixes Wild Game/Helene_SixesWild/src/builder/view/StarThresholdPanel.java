package builder.view;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import builder.actions.CopyStateAction;
import builder.actions.StarThresholdAction;
import builder.controllers.BuilderViewReference;
import common.model.AbstractLevel;
import common.model.ScoreThresholds;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

/**
 * Panel that displays the options to set the 1,2, and 3 star thresholds for a level.
 * @author tjbennett
 * @author njpanzarino
 * @author jasirocki
 *
 */
public class StarThresholdPanel extends ParameterPanel {

	/**
	 * TextField for entering the 3 star threshold.
	 */
	JTextField textField3;
	
	/**
	 * TextField for entering the 2 star threshold.
	 */
	JTextField textField2;
	
	/**
	 * TextField for entering the 1 star threshold.
	 */
	JTextField textField1;
	
	/**
	 * Object that holds the star threshold values.
	 */
	ScoreThresholds scoreThresholds;
	
	/**
	 * Create the panel.
	 */
	public StarThresholdPanel() {
		
		
		JLabel label = new JLabel("3 Stars");
		this.textField3 = new JTextField();
		
		JLabel label_1 = new JLabel("2 Stars");
		this.textField2 = new JTextField();
		
		JLabel label_2 = new JLabel("1 Star");
		this.textField1 = new JTextField();
		
		GroupLayout groupLayout = new GroupLayout(this);
		
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(label_2, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE)
							.addGap(23)
							.addComponent(textField1, GroupLayout.DEFAULT_SIZE, 199, Short.MAX_VALUE))
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(label_1, GroupLayout.PREFERRED_SIZE, 41, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addComponent(textField2, GroupLayout.DEFAULT_SIZE, 198, Short.MAX_VALUE))
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(label, GroupLayout.PREFERRED_SIZE, 41, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addComponent(textField3, GroupLayout.DEFAULT_SIZE, 198, Short.MAX_VALUE)))
					.addGap(23))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(label_2)
						.addComponent(textField1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(7)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(3)
							.addComponent(label_1))
						.addComponent(textField2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(7)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(6)
							.addComponent(label))
						.addComponent(textField3, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(219, Short.MAX_VALUE))
		);
		setLayout(groupLayout);
	}

	/**
	 * Initialize star thresholds.
	 * Add action listeners to the textFields that will update the values entered.
	 * @param st
	 */
	public void initialize(ScoreThresholds st){
		scoreThresholds = st;
		
		textField3.removeAll();
		
		textField3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				AbstractLevel l=BuilderViewReference.get().getModel().getCurrentLevel();
				if(l==null)
					return;
				
				if(Integer.parseInt(textField3.getText()) != l.getParams().getThresholds().getScoreThresh(3))
				{
					System.out.println(textField3.getText());
					CopyStateAction<ScoreThresholds> action = new CopyStateAction<ScoreThresholds>(l.getParams().getThresholds());
					ScoreThresholds fin = action.getFinal();
					fin.setThreshold(3, Integer.parseInt(textField3.getText()));
					action.setFinal(fin);
				
					BuilderViewReference.get().getModel().doAction(action);
					System.out.println(textField3.getText());
				}
			}
		});
		
		
		textField2.removeAll();
		
		textField2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				AbstractLevel l=BuilderViewReference.get().getModel().getCurrentLevel();
				if(l==null)
					return;
				
				if(Integer.parseInt(textField2.getText()) != l.getParams().getThresholds().getScoreThresh(2))
				{
					System.out.println(textField2.getText());
					CopyStateAction<ScoreThresholds> action = new CopyStateAction<ScoreThresholds>(l.getParams().getThresholds());
					ScoreThresholds fin = action.getFinal();
					fin.setThreshold(2, Integer.parseInt(textField2.getText()));
					action.setFinal(fin);
				
					BuilderViewReference.get().getModel().doAction(action);
					System.out.println(textField2.getText());
				}
			}
		});
		
		textField1.removeAll();
		
		textField1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				AbstractLevel l=BuilderViewReference.get().getModel().getCurrentLevel();
				if(l==null)
					return;
				
				if(Integer.parseInt(textField1.getText()) != l.getParams().getThresholds().getScoreThresh(1))
				{
					System.out.println(textField1.getText());
					CopyStateAction<ScoreThresholds> action = new CopyStateAction<ScoreThresholds>(l.getParams().getThresholds());
					ScoreThresholds fin = action.getFinal();
					fin.setThreshold(1, Integer.parseInt(textField1.getText()));
					action.setFinal(fin);
				
					BuilderViewReference.get().getModel().doAction(action);
					System.out.println(textField1.getText());
				}
			}
		});
	}
	
	/**
	 * Get panel name.
	 */
	@Override
	public String getPanelName() {
		return "Star Thresholds";
	}

	/**
	 * Update fields if changes have been made.
	 */
	@Override
	public void updateFields() {
		AbstractLevel l = BuilderViewReference.get().getModel().getCurrentLevel();
		if(l == null){
			return;
		}
		
		initialize(l.getParams().getThresholds());
		
		textField3.setText("" + l.getParams().getThresholds().getScoreThresh(3));
		textField2.setText("" + l.getParams().getThresholds().getScoreThresh(2));
		textField1.setText("" + l.getParams().getThresholds().getScoreThresh(1));	
	}
}
