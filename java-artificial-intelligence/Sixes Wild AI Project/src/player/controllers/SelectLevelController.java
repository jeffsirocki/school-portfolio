package player.controllers;

import java.awt.Dimension;
import java.awt.event.MouseEvent;

import common.model.AbstractLevel;

/**
 * Controller used to launch a new game to play the selected level once the level is clicked.
 * @author Nicholas Panzarino
 *
 */
public class SelectLevelController extends AbstractPlayerController {

	/**
	 * The level to be played when this controlelr is activated.
	 */
	AbstractLevel level;
	
	/**
	 * Make a new instance of this controller, which will launch the input level.
	 * @param level the level to be launched when this controlelr is activated.
	 */
	public SelectLevelController(AbstractLevel level) {
		this.level=level;
	}

	@Override
	public void mousePressed(MouseEvent e) {
		go();
	}
	
	/**
	 * All actions performed by this controller are done in a separate method which can be tested.
	 */
	public void go(){
		PlayerAppReference.get().getGameView().initialize(level.makeGame());
				
		PlayerAppReference.get().getGameView().updateFields();
				
		showOnly(PlayerAppReference.get().getGameView());
				
		//PlayerAppReference.get().getFrame().setSize(690, 530);
		PlayerAppReference.get().getFrame().setMinimumSize(new Dimension(690, 530));
	}

}
