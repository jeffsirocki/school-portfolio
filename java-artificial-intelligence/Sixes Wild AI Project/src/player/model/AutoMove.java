package player.model;

import player.AutoPlayer;
import player.view.GameView;
import common.model.AbstractMove;

public class AutoMove extends AbstractMove {

	private static final long serialVersionUID = -5627984862266273804L;

	GameView gameView;
	AbstractMove selectedMove;
	
	public AutoMove(GameView gameView) {
		this.gameView = gameView;
	}

	@Override
	public boolean doMove() {
		AutoPlayer player = new AutoPlayer(gameView);
		player.playAuto(1500);
		return true;
	}
	
	/**
	 * Give this move a type name
	 */
	public static String type(){
		return "Auto";
	}

}
