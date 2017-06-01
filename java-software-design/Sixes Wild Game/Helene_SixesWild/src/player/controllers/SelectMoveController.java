package player.controllers;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.JCheckBox;

import common.controllers.BoardSelectionController;
import common.model.AbstractMove;
import common.view.BoardView;
import player.view.PlayMovePanel;

/**
 * Controller for choosing which move to perform on the Board during gameplay.
 * THis controller references a PlayMove Panel, but is attached to ComboBoxes Within that panel
 * @author Nicholas Panzarino
 *
 */
public class SelectMoveController implements ItemListener{

	/**
	 * The panel that contains the moves and the states of the checkBoxes states.
	 */
	PlayMovePanel inPanel;
	
	/**
	 * The controller of the current special move.
	 */
	BoardSelectionController boardController=null;
	
	/**
	 * Creates a new SelectMoveController for the given panel.
	 */
	public SelectMoveController(PlayMovePanel inPanel){
		this.inPanel=inPanel;
	}
	
	/**
	 * When the JCheckBox of this controller changes state, either set board controller to match the move, or 
	 * return the BoardCOntroller to it's default state.
	 */
	public void itemStateChanged(ItemEvent e) {
		JCheckBox box=(JCheckBox)e.getSource();
		if(box.isSelected()){
			//Deselect all others
			for(JCheckBox b:inPanel.getBoxes()){
				if(!b.equals(box)&&b.isSelected())
					b.setSelected(false);
			}
			
			//set board controller to this move's controller
			PlayerAppReference.get().getGameView().getDefaultController().enable(false);
			
			if(boardController==null){
				boardController=AbstractMove.controller(inPanel.getMove(box));
			}
			else
				boardController.enable(true);
			
			BoardView boardView=PlayerAppReference.get().getGameView().getBoardView();
		}
		else{
			boardController.enable(false);
			
			PlayerAppReference.get().getGameView().getDefaultController().enable(true);
		}
	}
}
