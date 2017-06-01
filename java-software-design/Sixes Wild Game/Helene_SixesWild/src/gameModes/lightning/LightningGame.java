package gameModes.lightning;

import gameModes.puzzle.PuzzleLevel;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import common.model.AbstractMove;
import player.controllers.PlayerAppReference;
import player.model.AbstractGame;

/**
 * Lightning game.
 * @author njpanzarino
 * @author tjbennett
 * @author rlamsal-jdoc
 *
 */
public class LightningGame extends AbstractGame {
	
	/**
	 * object that keeps track of the time.
	 */
	Timer timer;

	/**
	 * current time in the game.
	 */
	int currentTime;
	
	/**
	 * Constructor that takes in a lightning level.
	 * @param level
	 */
	public LightningGame(LightningLevel level) {
		super(level);
		currentTime=0;
		
		timer=new Timer();
		
		TimerTask task = new TimerTask() {
			@Override
			public void run() {
				currentTime += 1;
				PlayerAppReference.get().getGameView().updateFields();
			}
		};
		
		timer.scheduleAtFixedRate(task, 1000, 1000);
	}
	
	/**
	 * performs a move if its valid.
	 */
	@Override
	public boolean doMove(AbstractMove move) {
		if(currentTime < ((LightningLevel)this.getLevel()).getMaxSeconds()){
			if(super.doMove(move)){
				return true;
			}
		}
		return false;
	}


	/**
	 * the labels that keep track of the time on the side in the game view.
	 */
	@Override
	public ArrayList<String> getFields() {
		ArrayList<String> out = super.getFields();
		long remaining=(((LightningLevel)this.getLevel()).getMaxSeconds()-currentTime);
		out.add("Remaining: "+(remaining/60)%60+":"+String.format("%02d",remaining%60));
		return out;
	}

	/**
	 * end the timer and the game.
	 */
	@Override
	public void endGame() {
		super.endGame();
		timer.cancel();
	}

	/**
	 * checks if the current time is greater than the maximum time allocated.
	 */
	@Override
	public boolean isOver() {
		// game is over when the time runs out
		 
		if(currentTime >= ((LightningLevel)this.getLevel()).getMaxSeconds()){
			return true;
		}
		return false;
	}

}
