package gameModes.puzzle;

import gameModes.elimination.EliminationLevel;

import java.awt.Color;
import java.util.ArrayList;

import player.model.AddToSixMove;
import common.model.AbstractSquare;
import common.model.Selection;
import common.model.Square;
import common.model.Tile;
import junit.framework.TestCase;

public class TestPuzzleGame extends TestCase {
	
	PuzzleGame game;
	PuzzleLevel level;

	protected void setUp() throws Exception {
		level = new PuzzleLevel(1);
		game = new PuzzleGame(level);
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}

	public void test() {
		
		// figure out what will be in the fields on the left
			ArrayList<String> leftFields = game.getFields();
			
			level.setHighscore(10);
			level.setLevelID(1);
			level.setMaxMoves(10);
			level.setUnlocked(true);
			assertEquals(level.getLevelID(), 1);
			assertEquals(level.getMaxMoves(), 10);
			assertEquals(level.isUnlocked(), true);
			assertEquals(level.getHighscore(), 10);
			
			Color puzzleColor = game.gameColor();
			
			Square square = new Square(0,0);
			Selection selection = new Selection(square);
			AddToSixMove atsm = new AddToSixMove(selection);
			
			boolean failedMove = game.doMove(atsm);
			assertEquals(failedMove,false);
			
			assertEquals(game.getScore(), 0);
			
			Square sq1 = new Square(0,0);
			Square sq2 = new Square(0,1);
			Tile t1 = new Tile(3, 1);
			Tile t2 = new Tile(3, 1);
			sq1.setTile(t1);
			sq2.setTile(t2);
			
			Selection validSelection = new Selection(sq1);
			validSelection.add(sq2);
			//assertTrue(game.doMove(atsm));
			assertFalse(game.isOver());
			
			level.copy(level);
	}
}
