package gameModes.elimination;

import java.awt.Color;
import java.util.ArrayList;

import junit.framework.TestCase;
import player.model.AddToSixMove;
import common.model.AbstractSquare;
import common.model.Selection;
import common.model.Square;

public class TestEliminationGame extends TestCase {

	EliminationGame eliminationGame;
	EliminationLevel eliminationLevel;
	
	@Override
	protected void setUp() throws Exception {
		eliminationLevel = new EliminationLevel(1);
		eliminationGame = new EliminationGame(eliminationLevel);
	}
	@Override
	protected void tearDown() throws Exception {
		// TODO Auto-generated method stub
		super.tearDown();
	}
	
	public void testEliminationGame() {
		// figure out what will be in the fields on the left
		ArrayList<String> leftFields = eliminationGame.getFields();
				
		Color eliminationColor = eliminationGame.gameColor();
		assertEquals(eliminationColor, new Color(255, 120, 0));
				
		int tenMoves = eliminationGame.remainingMoves();
		assertEquals(tenMoves, 10);
				
		Square square = new Square(0,0);
		Selection selection = new Selection(square);
		AddToSixMove atsm = new AddToSixMove(selection);
				
		boolean failedMove = eliminationGame.doMove(atsm);
		assertEquals(failedMove,false);
	}
	
	public void testEliminationLevel() {
		EliminationLevel newElimination = new EliminationLevel(2);
		newElimination.makeGame();
		ArrayList<Class<? extends AbstractSquare>> squareTypes = EliminationLevel.squareTypes();
		
		newElimination.copy(eliminationLevel);
	}
}
