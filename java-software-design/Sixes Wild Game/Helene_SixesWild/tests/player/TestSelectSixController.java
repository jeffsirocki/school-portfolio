package player;

import common.model.Selection;
import common.model.Square;
import common.model.Tile;
import common.view.BoardView;
import player.controllers.SelectSixController;
import junit.framework.TestCase;

public class TestSelectSixController extends TestCase {
	
	public void testSimple()
	{
		PlayerApplication playerApp = new PlayerApplication();
		BoardView boardView = playerApp.getGameView().getBoardView();
		SelectSixController selectSix = new SelectSixController(boardView);
		
		Square square = new Square(0,0);
		assertFalse(selectSix.isValid(square));
		
		Tile sixTile = new Tile(6, 0);
		square.setTile(sixTile);
		assertFalse(selectSix.isValid(square));
		
		Tile notSixTile = new Tile(3, 1);
		square.setTile(notSixTile);
		assertTrue(selectSix.isValid(square));	
		
		Tile alsoNotSixTile = new Tile(3, 1);
		Square squareTwo = new Square(0,1);
		squareTwo.setTile(alsoNotSixTile);
		Selection selection = new Selection(square);
		selection.add(squareTwo);
		selectSix.setSelection(selection);
	}
}
