package gameModes.lightning;

import gameModes.elimination.EliminationLevel;

import java.awt.Color;
import java.util.ArrayList;

import junit.framework.TestCase;
import player.model.AddToSixMove;
import common.model.AbstractSquare;
import common.model.Selection;
import common.model.Square;

public class TestLightningGame extends TestCase {

	LightningGame lightningGame;
	LightningLevel lightningLevel;
	
	@Override
	protected void setUp() throws Exception {
		lightningLevel = new LightningLevel(1);
		lightningGame = new LightningGame(lightningLevel);

	}

	@Override
	protected void tearDown() throws Exception {
		// TODO Auto-generated method stub
		super.tearDown();
	}

	public void testLightningGame() {
		// figure out what will be in the fields on the left
		ArrayList<String> leftFields = lightningGame.getFields();
				
		Color lightningColor = lightningGame.gameColor();
			
		Square square = new Square(0,0);
		Selection selection = new Selection(square);
		AddToSixMove atsm = new AddToSixMove(selection);
				
		boolean failedMove = lightningGame.doMove(atsm);
		assertEquals(failedMove,false);
	}
	
	public void testLightningLevel() {
		LightningLevel newLightning = new LightningLevel(2);
		newLightning.makeGame();
		
		ArrayList<Class<? extends AbstractSquare>> squareTypes = EliminationLevel.squareTypes();
		
		newLightning.copy(lightningLevel);
	}
}
