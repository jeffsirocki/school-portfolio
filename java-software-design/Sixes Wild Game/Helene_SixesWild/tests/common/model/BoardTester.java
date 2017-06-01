package common.model;

import static org.junit.Assert.assertEquals;

import java.awt.Point;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import player.PlayerApplication;
import player.controllers.PlayerAppReference;
import player.controllers.RemoveSquareController;
import player.controllers.ShuffleBoardController;
import player.controllers.Swap2Controller;
import player.model.RemoveMove;
import player.model.ShuffleMove;
import player.model.Swap2Move;
import builder.controllers.BuilderViewReference;
import builder.controllers.TypeSelectionController;
import builder.model.BuilderModel;
import builder.view.BuilderView;
import common.controllers.BoardSelectionController;

public class BoardTester {
	Board board;
	FrequencyList freq;
	Square square, square2, square3;

	@Before
	public void setUp() throws Exception {
		BuilderView builderView = new BuilderView(new BuilderModel());
		BuilderViewReference.set(builderView);
		PlayerApplication playerApp = new PlayerApplication();
		PlayerAppReference.set(playerApp);
		square = new Square(0,0);
		square2 = new Square(0,1);
		square3 = new Square(0,2);
		board=new Board(5);
		
		FrequencyList spawn=new FrequencyList(new int[] {1,2,3,4,5,6});
		spawn.setFreq(2,20);
		spawn.setFreq(3,20);
		spawn.setFreq(4,15);
		spawn.setFreq(5,15);
		spawn.setFreq(6,10);
		
		FrequencyList bonus=new FrequencyList(new int[] {1,2,3});
		bonus.setFreq(2,10);
		bonus.setFreq(3, 5);
		
		for(int r=0;r<board.size;r++){
			for(int c=0;c<board.size;c++){
				if(r==0){
					AbstractContainer square=(AbstractContainer)board.getSquare(r,c);
					square.setFillFrom(new RandomSource(spawn,bonus));
				}
				else{
					AbstractContainer square=(AbstractContainer)board.getSquare(r,c);
					square.setFillFrom(board.getSquare((r-1),c));
				}
			}
		}
		board.update();
	}

	@After
	public void tearDown() throws Exception {
	}

	/**
	 * Tests:
	 *  - Remove Tile
	 *  - Change Size
	 */
	
	@Test
	public void test() {
		
		System.out.println(board);
		
		board.removeTile(3,3);
		board.removeTile(4,3);
		
		System.out.println(board);
		board.update();
		System.out.println(board);
		
		assertEquals(board.getSquare(null),null);
		assertEquals(board.getSquare(new Point(-1,-1)),null);
		
		BuilderViewReference.updateFields();
	}
	
	@Test
	public void testSize() {
		
		//System.out.println(board);
		assertEquals(board.squares.size(), 25);
		
		board.setSize(7);
		assertEquals(board.squares.size(), 49);
	}

	@Test
	public void testRemoveMove() {
		square = new Square(0,0);
		RemoveMove rm = new RemoveMove(board, square);
		rm.doMove();
		
		BoardSelectionController bsc;
		bsc = rm.getController();
	}
	
	@Test
	public void testSwap2Move() {
		Swap2Move s2m = new Swap2Move(board, square, square2);
		s2m.doMove();
		
		BoardSelectionController bsc;
		bsc = s2m.getController();
	}
	
	@Test
	public void testShuffle() {
		ShuffleMove sm = new ShuffleMove(board);
		sm.doMove();
		
		BoardSelectionController bsc;
		bsc = sm.getController();
	}
	
	@Test
	public void testControllers() {
		
		Swap2Controller s2c = new Swap2Controller(PlayerAppReference.get().getGameView().getBoardView());
		s2c.isValid(square);
		
		RemoveSquareController rsc = new RemoveSquareController(PlayerAppReference.get().getGameView().getBoardView());
		rsc.isValid(square2);
		
		ShuffleBoardController sbc = new ShuffleBoardController(PlayerAppReference.get().getGameView().getBoardView());
		sbc.isValid(square3);
	}
}
