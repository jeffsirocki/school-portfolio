package builder.view;

import java.awt.Component;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;

import javax.swing.JCheckBox;
import javax.swing.SpringLayout;
import javax.swing.SwingConstants;

import builder.actions.SpecialMoveAction;
import builder.controllers.BuilderViewReference;
import common.model.AbstractLevel;
import common.model.AbstractMove;

/**
 * Special Move panel allows you to select which special moves are allowed in the level.
 * @author njpanzario
 * @author tjbennett - windowbuilder
 * @author jasirocki - jdoc
 *
 */
public class SpecialMovePanel extends ParameterPanel {
	final int HEIGHT=30;
	final int LEFT_OFFSET=137;
	
	/**
	 * Spring layout used.
	 */
	SpringLayout springLayout;
	
	/**
	 * Array list of possible moves.
	 */
	ArrayList<Class<? extends AbstractMove>> moves=new ArrayList<Class<? extends AbstractMove>>();
	
	/**
	 * Check boxes for boolean values.
	 */
	ArrayList<JCheckBox> boxes=new ArrayList<JCheckBox>();

	/**
	 * Create the panel.
	 */
	public SpecialMovePanel() {
		
		springLayout = new SpringLayout();
		setLayout(springLayout);
		
		//addField("a");
		
		initialize(AbstractLevel.moveTypes());
	}
	
	/**
	 * Re-Initialize this Panel to display the input list of available moves.
	 * @param moves
	 */
	public void initialize(ArrayList<Class<? extends AbstractMove>> moves){
		this.removeAll();
		
		boxes=new ArrayList<JCheckBox>();
		
		springLayout = new SpringLayout();
		setLayout(springLayout);
		
		this.moves=moves;
		
		for(int i=0;i<moves.size();i++){
			final int n=i;
			
			
			
			Class<? extends AbstractMove> m=moves.get(i);
			addField(AbstractMove.type(m)+": ");
			boxes.get(n).addItemListener(new ItemListener() {
				public void itemStateChanged(ItemEvent e) {
					AbstractLevel l=BuilderViewReference.get().getModel().getCurrentLevel();
					if(l==null)
						return;
					
					boolean b=(SpecialMovePanel.this.boxes.get(n).isSelected());
					SpecialMoveAction act=new SpecialMoveAction(SpecialMovePanel.this.moves.get(n),b);
					BuilderViewReference.get().getModel().doAction(act);
				}
			});
		}
	}
	
	/**
	 * Add new special move.
	 * @param str
	 */
	public void addField(String str){
		Component c=this;
		String dir=SpringLayout.NORTH;
		
		if(boxes.size()>0){
			c=boxes.get(boxes.size()-1);
			dir=SpringLayout.SOUTH;
		}
		
		JCheckBox checkBox = new JCheckBox(str);
		checkBox.setHorizontalTextPosition(SwingConstants.LEFT);
		checkBox.setHorizontalAlignment(SwingConstants.RIGHT);
		checkBox.setOpaque(false);
		
		springLayout.putConstraint(SpringLayout.NORTH, checkBox, 12, dir, c);
		springLayout.putConstraint(SpringLayout.SOUTH, checkBox, 12+HEIGHT, dir, c);
		springLayout.putConstraint(SpringLayout.EAST, checkBox, LEFT_OFFSET, SpringLayout.WEST, this);
		springLayout.putConstraint(SpringLayout.WEST, checkBox, 0, SpringLayout.WEST, this);
		
		add(checkBox);
		
		boxes.add(checkBox);
	}

	/**
	 * Get panel name.
	 */
	@Override
	public String getPanelName() {
		return "Special Moves";
	}

	/**
	 * Update panel if changes are made.
	 */
	@Override
	public void updateFields() {
		AbstractLevel l=BuilderViewReference.get().getModel().getCurrentLevel();
		if(l==null)
			return;
		
		initialize(AbstractLevel.getMoveTypes(l.getClass()));
		
		for(Class<? extends AbstractMove> m:l.getParams().getMoves()){
			if(moves.contains(m)){
				boxes.get(moves.indexOf(m)).setSelected(true);
			}
		}
	}
}
