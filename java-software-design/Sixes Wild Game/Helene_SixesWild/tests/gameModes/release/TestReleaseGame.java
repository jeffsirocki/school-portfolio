package gameModes.release;

import gameModes.elimination.EliminationLevel;

import java.awt.Color;
import java.util.ArrayList;

import junit.framework.TestCase;
import player.model.AddToSixMove;
import common.model.AbstractSquare;
import common.model.Selection;
import common.model.Square;

public class TestReleaseGame extends TestCase {

	ReleaseGame releaseGame;
	ReleaseLevel releaseLevel;
	
	@Override
	protected void setUp() throws Exception {
		releaseLevel = new ReleaseLevel(1);
		releaseGame = new ReleaseGame(releaseLevel);
	}

	@Override
	protected void tearDown() throws Exception {
		// TODO Auto-generated method stub
		super.tearDown();
	}

	public void testReleaseGame() {
	
		// figure out what will be in the fields on the left
		ArrayList<String> leftFields = releaseGame.getFields();
		
		Color releaseColor = releaseGame.gameColor();
		assertEquals(releaseColor, new Color(153, 50, 204));
		
		int tenMoves = releaseGame.remainingMoves();
		assertEquals(tenMoves, 10);
		
		Square square = new Square(0,0);
		Selection selection = new Selection(square);
		AddToSixMove atsm = new AddToSixMove(selection);
		
		boolean failedMove = releaseGame.doMove(atsm);
		assertEquals(failedMove,false);
	}
	
	public void testReleaseLevel() {
		ReleaseLevel newRelease = new ReleaseLevel(2);
		newRelease.makeGame();
		
		ArrayList<Class<? extends AbstractSquare>> squareTypes = ReleaseLevel.squareTypes();
		
		newRelease.copy(releaseLevel);
	}
	
	public void testReleaseSquare() {
		Square rs1 = new Square(2,3);
		ReleaseSquare rs2 = new ReleaseSquare(rs1);
		String nameRelease = ReleaseSquare.type();
		assertEquals(nameRelease, "Release Square");
	}
}
