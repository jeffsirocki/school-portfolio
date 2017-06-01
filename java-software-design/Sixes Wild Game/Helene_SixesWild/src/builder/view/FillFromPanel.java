package builder.view;
import javax.swing.JButton;
import javax.swing.JToggleButton;
import javax.swing.SpringLayout;
import javax.swing.JCheckBox;
import javax.swing.SwingConstants;
import javax.swing.ImageIcon;

import common.model.AbstractLevel;
import common.model.ReferenceMap;
import common.view.BoardView;
import builder.actions.ChangeDefaultFillAction;
import builder.controllers.BuilderViewReference;
import builder.controllers.ChangeRefController;
import builder.controllers.TypeSelectionController;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

/**
 * Gravity controls to set fill from references
 * @author njpanzarino
 * @author jasirocki - jdoc
 *
 */
public class FillFromPanel extends ParameterPanel {

	/**
	 * Panel size.
	 */
	static final int SIZE=30;
	
	/**
	 * Gap for panel.
	 */
	static final int GAP=6;
	
	/**
	 * Middle button.
	 */
	JButton middleButton;
	
	/**
	 * Layout for panel.
	 */
	SpringLayout springLayout;
	
	/**
	 * Check box for displaying/hiding references.
	 */
	JCheckBox chckbxShowReferences;
	
	/**
	 * Check box for toggling bridge gaps behavior.
	 */
	JCheckBox chckbxBridgeGaps;
	
	/**
	 * Toggle Button for manual selection mode.
	 */
	JToggleButton manualButton;
	
	/**
	 * Create the panel.
	 */
	public FillFromPanel() {
		springLayout = new SpringLayout();
		setLayout(springLayout);
		
		middleButton = new JButton("");
		middleButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				if(BuilderViewReference.get().getModel().getCurrentLevel()==null)
					return;
				ChangeDefaultFillAction act=new ChangeDefaultFillAction(BuilderViewReference.get().getModel().getCurrentLevel(),new int[] {0,0});
				BuilderViewReference.get().getModel().doAction(act);
				BuilderViewReference.get().getBoardView().redraw();
			}
		});
		springLayout.putConstraint(SpringLayout.NORTH, middleButton, 50, SpringLayout.NORTH, this);
		springLayout.putConstraint(SpringLayout.WEST, middleButton, 100, SpringLayout.WEST, this);
		springLayout.putConstraint(SpringLayout.SOUTH, middleButton, 80, SpringLayout.NORTH, this);
		springLayout.putConstraint(SpringLayout.EAST, middleButton, -100, SpringLayout.EAST, this);
		add(middleButton);
		
		JButton button = new JButton("");
		button.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				if(BuilderViewReference.get().getModel().getCurrentLevel()==null)
					return;
				ChangeDefaultFillAction act=new ChangeDefaultFillAction(BuilderViewReference.get().getModel().getCurrentLevel(),ReferenceMap.FROM_LEFT);
				BuilderViewReference.get().getModel().doAction(act);
				BuilderViewReference.get().getBoardView().redraw();
			}
		});
		button.setIcon(new ImageIcon(FillFromPanel.class.getResource("/resource/arrowRight.png")));
		springLayout.putConstraint(SpringLayout.NORTH, button, 0, SpringLayout.NORTH, middleButton);
		springLayout.putConstraint(SpringLayout.WEST, button, GAP, SpringLayout.EAST, middleButton);
		springLayout.putConstraint(SpringLayout.SOUTH, button, 0, SpringLayout.SOUTH, middleButton);
		springLayout.putConstraint(SpringLayout.EAST, button, GAP+SIZE, SpringLayout.EAST, middleButton);
		add(button);
		
		JButton button_1 = new JButton("");
		button_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				if(BuilderViewReference.get().getModel().getCurrentLevel()==null)
					return;
				ChangeDefaultFillAction act=new ChangeDefaultFillAction(BuilderViewReference.get().getModel().getCurrentLevel(),ReferenceMap.FROM_RIGHT);
				BuilderViewReference.get().getModel().doAction(act);
				BuilderViewReference.get().getBoardView().redraw();
			}
		});
		button_1.setIcon(new ImageIcon(FillFromPanel.class.getResource("/resource/arrowLeft.png")));
		springLayout.putConstraint(SpringLayout.NORTH, button_1, 0, SpringLayout.NORTH, middleButton);
		springLayout.putConstraint(SpringLayout.WEST, button_1, -GAP-SIZE, SpringLayout.WEST, middleButton);
		springLayout.putConstraint(SpringLayout.SOUTH, button_1, 0, SpringLayout.SOUTH, middleButton);
		springLayout.putConstraint(SpringLayout.EAST, button_1, -GAP, SpringLayout.WEST, middleButton);
		add(button_1);
		
		JButton button_2 = new JButton("");
		button_2.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				if(BuilderViewReference.get().getModel().getCurrentLevel()==null)
					return;
				ChangeDefaultFillAction act=new ChangeDefaultFillAction(BuilderViewReference.get().getModel().getCurrentLevel(),ReferenceMap.FROM_DOWN);
				BuilderViewReference.get().getModel().doAction(act);
				BuilderViewReference.get().getBoardView().redraw();
			}
		});
		button_2.setIcon(new ImageIcon(FillFromPanel.class.getResource("/resource/arrowUp.png")));
		springLayout.putConstraint(SpringLayout.NORTH, button_2, -GAP-SIZE, SpringLayout.NORTH, middleButton);
		springLayout.putConstraint(SpringLayout.WEST, button_2, 0, SpringLayout.WEST, middleButton);
		springLayout.putConstraint(SpringLayout.SOUTH, button_2, -GAP, SpringLayout.NORTH, middleButton);
		springLayout.putConstraint(SpringLayout.EAST, button_2, 0, SpringLayout.EAST, middleButton);
		add(button_2);
		
		JButton button_3 = new JButton("");
		button_3.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				if(BuilderViewReference.get().getModel().getCurrentLevel()==null)
					return;
				ChangeDefaultFillAction act=new ChangeDefaultFillAction(BuilderViewReference.get().getModel().getCurrentLevel(),ReferenceMap.FROM_UP);
				BuilderViewReference.get().getModel().doAction(act);
				BuilderViewReference.get().getBoardView().redraw();
			}
		});
		button_3.setIcon(new ImageIcon(FillFromPanel.class.getResource("/resource/arrowDown.png")));
		springLayout.putConstraint(SpringLayout.NORTH, button_3, GAP, SpringLayout.SOUTH, middleButton);
		springLayout.putConstraint(SpringLayout.WEST, button_3, 0, SpringLayout.WEST, middleButton);
		springLayout.putConstraint(SpringLayout.SOUTH, button_3, GAP+SIZE, SpringLayout.SOUTH, middleButton);
		springLayout.putConstraint(SpringLayout.EAST, button_3, 0, SpringLayout.EAST, middleButton);
		add(button_3);
		
		chckbxBridgeGaps = new JCheckBox("Bridge Gaps: ");
		chckbxBridgeGaps.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				AbstractLevel l=BuilderViewReference.get().getModel().getCurrentLevel();
				if(l==null)
					return;
				
				l.getParams().getRef().setBridge(((JCheckBox)e.getSource()).isSelected());
				l.getParams().updateRef();
				BuilderViewReference.get().getBoardView().redraw();
			}
		});
		chckbxBridgeGaps.setOpaque(false);
		chckbxBridgeGaps.setBackground(getBackground());
		springLayout.putConstraint(SpringLayout.EAST, chckbxBridgeGaps, 0, SpringLayout.EAST, button);
		chckbxBridgeGaps.setHorizontalTextPosition(SwingConstants.LEFT);
		springLayout.putConstraint(SpringLayout.WEST, chckbxBridgeGaps, 0, SpringLayout.WEST, this);
		chckbxBridgeGaps.setHorizontalAlignment(SwingConstants.RIGHT);
		add(chckbxBridgeGaps);
		
		chckbxShowReferences = new JCheckBox("Show Gravity: ");
		chckbxShowReferences.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				if(BuilderViewReference.get().getModel().getCurrentLevel()==null)
					return;
				BuilderViewReference.get().getBoardView().showReferences(((JCheckBox)e.getSource()).isSelected());
				BuilderViewReference.get().getBoardView().redraw();
			}
		});
		chckbxShowReferences.setOpaque(false);
		springLayout.putConstraint(SpringLayout.NORTH, chckbxShowReferences, GAP, SpringLayout.SOUTH, chckbxBridgeGaps);
		springLayout.putConstraint(SpringLayout.WEST, chckbxShowReferences, 0, SpringLayout.WEST, this);
		springLayout.putConstraint(SpringLayout.EAST, chckbxShowReferences, 0, SpringLayout.EAST, button);
		chckbxShowReferences.setHorizontalTextPosition(SwingConstants.LEFT);
		chckbxShowReferences.setHorizontalAlignment(SwingConstants.RIGHT);
		add(chckbxShowReferences);
		
		manualButton = new JToggleButton("Manual Select");
		manualButton.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				BoardView b=BuilderViewReference.get().getBoardView();
				for(MouseListener m:b.getMouseListeners()){
					b.removeMouseListener(m);
				}
				for(MouseMotionListener m:b.getMouseMotionListeners()){
					b.removeMouseMotionListener(m);
				}
				
				if(((JToggleButton)e.getSource()).isSelected()){
					new ChangeRefController(b);
				}
				else{
					new TypeSelectionController(b);
				}
			}
		});
		springLayout.putConstraint(SpringLayout.NORTH, chckbxBridgeGaps, GAP*2, SpringLayout.SOUTH, manualButton);
		springLayout.putConstraint(SpringLayout.NORTH, manualButton, GAP*2, SpringLayout.SOUTH, button_3);
		springLayout.putConstraint(SpringLayout.WEST, manualButton, -SIZE, SpringLayout.WEST, button_1);
		springLayout.putConstraint(SpringLayout.EAST, manualButton, SIZE, SpringLayout.EAST, button);
		add(manualButton);

	}
	
	/**
	 * Set bounds for panel.
	 */
	@Override
	public void setBounds(int x, int y, int width, int height) {
		super.setBounds(x, y, width, height);
		rePosition();
	}
	
	/**
	 * Reposition the items on panel.
	 */
	public void rePosition(){
		springLayout.removeLayoutComponent(middleButton);
		springLayout.putConstraint(SpringLayout.NORTH, middleButton, SIZE+3*GAP, SpringLayout.NORTH, this);
		springLayout.putConstraint(SpringLayout.WEST, middleButton, (getWidth()-SIZE)/2, SpringLayout.WEST, this);
		springLayout.putConstraint(SpringLayout.SOUTH, middleButton, 2*SIZE+3*GAP, SpringLayout.NORTH, this);
		springLayout.putConstraint(SpringLayout.EAST, middleButton, -(getWidth()-SIZE)/2, SpringLayout.EAST, this);
	}

	/**
	 * Get Panel name.
	 */
	@Override
	public String getPanelName() {
		return "Change Gravity";
	}

	/**
	 * Update the panel.
	 */
	@Override
	public void updateFields() {
		AbstractLevel l=BuilderViewReference.get().getModel().getCurrentLevel();
		if(l==null)
			return;
		
		boolean bridge=l.getParams().getRef().isBridge();
		if(bridge!=chckbxBridgeGaps.isShowing()){
			chckbxBridgeGaps.setSelected(bridge);
		}
		
		manualButton.setSelected(false);
		
		BuilderViewReference.get().getBoardView().showReferences(chckbxShowReferences.isSelected());
		
	}
}
