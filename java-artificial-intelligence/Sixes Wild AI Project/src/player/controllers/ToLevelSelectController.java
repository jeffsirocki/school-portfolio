package player.controllers;

import java.awt.Dimension;
import java.awt.event.MouseEvent;

import player.model.AbstractGame;

/**
 * Controller for navigating to the Lelvel Select view from anywhere in the application.
 * @author Nicholas Panzarino
 *
 */
public class ToLevelSelectController extends AbstractPlayerController{
	
	/**
	 * Create an instance of this controller.
	 */
	public ToLevelSelectController(){
		
	}

	/**
	 * Implementation performed in go() function.
	 */
	@Override
	public void mousePressed(MouseEvent e) {
		go();
	}
	
	/**
	 * Navigates to the Level select view.
	 * If there is an active game, it is ended.
	 */
	public void go(){
		AbstractGame g=PlayerAppReference.get().getGameView().getTheGame();
		if(g!=null){
			g.endGame();
		}
		PlayerAppReference.get().getAllLevels().updateUnlocked();
		
		PlayerAppReference.get().getSelectView().initialize(PlayerAppReference.get().getAllLevels());
		showOnly(PlayerAppReference.get().getSelectView());
		PlayerAppReference.get().getFrame().setMinimumSize(new Dimension(450, 400));
		
		//PlayerAppReference.get().getFrame().setSize(450, 400);
	}
}
