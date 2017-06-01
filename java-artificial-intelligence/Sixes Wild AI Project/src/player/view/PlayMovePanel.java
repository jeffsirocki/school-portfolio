package player.view;

import javax.swing.AbstractButton;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import player.controllers.AutoMoveController;
import player.controllers.PlayerAppReference;
import player.controllers.SelectMoveController;
import player.model.AutoMove;
import common.model.AbstractMove;
import common.view.IUpdateFields;

import java.awt.FlowLayout;
import java.util.ArrayList;

/**
 * JPanel for displaying/selecting available moves in the player.
 * @author Nicholas Panzarino
 *
 */
public class PlayMovePanel extends JPanel implements IUpdateFields{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 7667240239182887803L;

	/**
	 * Keep a referenceto the layout so we can add to it later.
	 */
	FlowLayout flowLayout;
	
	/**
	 * Store a list of all avaliable moves.
	 */
	ArrayList<Class<? extends AbstractMove>> moves;
	
	/**
	 * Create a list of checkboxes corresponding to each move.
	 */
	ArrayList<AbstractButton> boxes=new ArrayList<AbstractButton>();
	
	/**
	 * Create the panel.
	 */
	public PlayMovePanel() {
		flowLayout=new FlowLayout(FlowLayout.CENTER, 7, 7);
		setLayout(flowLayout);
		
		addField("move1");
	}
	
	/**
	 * Initialize this panel using an ArrayList of move classes.
	 * Creates a JCheckBox for each move type, centered in the middle of the panel.
	 * The name of the type of move will be displayed above the checkbox
	 * @param moves An array list of the avaliable move types for the level beign played
	 */
	public void initialize(ArrayList<Class<? extends AbstractMove>> moves){
		removeAll();
		flowLayout.setVgap(20);
		setLayout(flowLayout);
		this.moves=new ArrayList<Class<? extends AbstractMove>>();
		boxes=new ArrayList<AbstractButton>();
		
		int i=0;
		for(Class<? extends AbstractMove> m:moves){
			final int n=i;
			this.moves.add(m);
			if(m.equals(AutoMove.class)){
				addButton("Auto");
				boxes.get(n).addMouseListener(new AutoMoveController(PlayerAppReference.get().getGameView()));
			}
			else{
				addField(AbstractMove.type(m)+":");
				boxes.get(n).addItemListener(new SelectMoveController(this));
			}
			
			i++;
		}
	}
	
	/**
	 * Add a checkbox to the panel with the given String as a label
	 * @param str
	 */
	public void addField(String str){
		
		JCheckBox checkBox = new JCheckBox(str);
		checkBox.setVerticalTextPosition(SwingConstants.TOP);
		checkBox.setHorizontalTextPosition(SwingConstants.CENTER);
		checkBox.setOpaque(false);
		add(checkBox);
		
		boxes.add(checkBox);
	}
	
	public void addButton(String str){
		
		JButton button = new JButton(str);
		button.setVerticalTextPosition(SwingConstants.TOP);
		button.setHorizontalTextPosition(SwingConstants.CENTER);
		button.setOpaque(false);
		add(button);
		
		boxes.add(button);
	}

	
	/**
	 * Returns the move corresponding the input box
	 * @param box
	 * @return
	 */
	public Class<? extends AbstractMove> getMove(JCheckBox box){
		for(int i=0;i<boxes.size();i++){
			if(boxes.get(i).equals(box))
				return moves.get(i);
		}
		return null;
	}
	
	/**
	 * deselects all checkboxes
	 */
	public void deselectAll(){
		for(AbstractButton b:boxes){
			if(b.isSelected())
				b.setSelected(false);
		}
	}

	/**
	 * Returns a list of all the moves displayed by this panel.
	 */
	public ArrayList<Class<? extends AbstractMove>> getMoves() {
		return moves;
	}

	/**
	 * returns a list of all the JCheckBoxes in this panel
	 */
	public ArrayList<AbstractButton> getBoxes() {
		return boxes;
	}

	@Override
	public void updateFields() {
		//nothing to update
	}
	
}
