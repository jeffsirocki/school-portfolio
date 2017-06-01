package player.controllers;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import player.model.AutoMove;
import player.view.GameView;

public class AutoMoveController extends MouseAdapter{

	GameView game;
	
	public AutoMoveController(GameView gameView) {
		this.game=gameView;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		AutoMove m = new AutoMove(this.game);
		m.doMove();
		PlayerAppReference.updateFields();
	}

}
