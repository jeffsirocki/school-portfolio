package common.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import gameModes.elimination.EliminationLevel;
import gameModes.elimination.EliminationMoveAction;
import gameModes.lightning.LightningLevel;
import gameModes.lightning.LightningTimeAction;
import gameModes.puzzle.PuzzleLevel;
import gameModes.puzzle.PuzzleMoveAction;
import gameModes.release.ReleaseLevel;
import gameModes.release.ReleaseMoveAction;

import java.awt.Point;
import java.util.ArrayList;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import builder.actions.ChangeRefMapAction;
import builder.actions.StarThresholdAction;
import builder.controllers.BuilderViewReference;
import builder.model.BuilderModel;
import builder.view.BuilderView;

public class TestLevelParameters {

	LevelParameters params;
	Board board;
	FrequencyList bonus;
	FrequencyList spawn;
	Integer size;
	ReferenceMap ref;
	ArrayList<Class<? extends AbstractMove>> moves;
	ScoreThresholds thresholds;
	
	AbstractSquare square;
	Square sqr;
	EmptySquare empty;
	//ReleaseSquare release;
	Tile tile;
	
	Selection select;
	Point P, P2;
	
	
	@Before
	public void setUp() throws Exception {
		BuilderView builderView = new BuilderView(new BuilderModel());
		BuilderViewReference.set(builderView);
		params = new LevelParameters();
		board = new Board(5);
		spawn = new FrequencyList(new FrequencyList(new int[] {1,2,3,4,5,6}));
		bonus = new FrequencyList(new FrequencyList(new int[] {1,2,3}));
		moves = params.getMoves();
		ref = params.getRef();
		size = 9;
		thresholds = new ScoreThresholds(100, 500, 1000);
		
		params.setBoard(board);
		params.setSpawn(spawn);
		params.setBonus(bonus);
		params.setMoves(moves);
		params.setRef(ref);
		params.setSize(size);
		params.setThresholds(thresholds);
		
		square = new Square(4, 4);
		sqr = new Square(5, 5);
		empty = new EmptySquare(3, 3);
		tile = new Tile(5, 3);
		select = new Selection(square); 
		P = new Point(1, 1);
		P2 = new Point(1, 2);
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testBoard() {
		board.getSize();
		board.getClass();
		board.getSquare(P);
		board.getSquare(1, 1);
		
		board.setSize(9);
		assertEquals(board.size, 9);
		
		board.iterator();
	
		board.removeAllReferences();
		board.removeTile(1, 1);
		board.removeAllTiles();
		
		//board.changeType(sqr, c);
	}
	
	@Test
	public void testSquare() {
		assertEquals(square.getColumn(), square.getRow());
		square.setSelected(true);
		assertTrue(square.isSelected());
		
		square.getDrawer();
		square.getType();
		
		sqr.getColumn();
		sqr.getRow();
		sqr.getDrawer();
		sqr.getFillFrom();
		sqr.getType();
		sqr.hasTile();
		sqr.isSelected();
		sqr.peek();
		//sqr.takeTile();
		//sqr.push(tile);
		sqr.removeFillFrom();
		sqr.remove();
		sqr.setFillFrom(square);
		sqr.setSelected(true);
		sqr.setTile(tile);
		sqr.makeCopy();
		
		Square.type();
		//Class<? extends AbstractType> Square = null;
		//AbstractSquare.type(Square);
		
	}
	
	@Test
	public void testEmptySquare() {
		empty.setSelected(true);
		empty.getColumn();
		empty.getRow();
		empty.getType();
		empty.getDrawer();
		empty.isSelected();
		empty.makeCopy();
	}
	
	@Test
	public void testTile() {
		tile.getBonus();
		tile.getVal();
		tile.copy();
	}
	
	@Test
	public void testRef() {
		
		//ref.initialize(5);
		ref.getRef(P);
		ref.iterator();
		
		ref.setSize(9);
		ref.setRef(P, P2);
	}
	
	@Test
	public void testThresholds() {
		assertEquals(thresholds.getNumStars(99), 0);
		assertEquals(thresholds.getNumStars(200), 1);
		assertEquals(thresholds.getNumStars(600), 2);
		assertEquals(thresholds.getNumStars(1200), 3);
	}
	
	@Test
	public void testThresholdAction() {
		StarThresholdAction sta = new StarThresholdAction(2,3);
		sta.doAction();
		sta.undoAction();
	}

	@Test
	public void testPuzzleMoveAction() {
		PuzzleLevel level = new PuzzleLevel(1);
		BuilderViewReference.get().setCurrentLevel(level);
		PuzzleMoveAction pma = new PuzzleMoveAction(2);
		pma.doAction();
		pma.undoAction();
	}
	
	@Test
	public void testLightningTimeAction() {
		LightningLevel level = new LightningLevel(1);
		BuilderViewReference.get().setCurrentLevel(level);
		LightningTimeAction lta = new LightningTimeAction(2);
		lta.doAction();
		lta.undoAction();
	}
	
	@Test
	public void testEliminationMoveAction() {
		EliminationLevel level = new EliminationLevel(1);
		BuilderViewReference.get().setCurrentLevel(level);
		EliminationMoveAction ema = new EliminationMoveAction(2);
		ema.doAction();
		ema.undoAction();
	}
	
	@Test
	public void testReleaseMoveAction() {
		ReleaseLevel level = new ReleaseLevel(1);
		BuilderViewReference.get().setCurrentLevel(level);
		ReleaseMoveAction rma = new ReleaseMoveAction(2);
		rma.doAction();
		rma.undoAction();
	}
	
	@Test
	public void testChangeRefMapAction() {
		LightningLevel level = new LightningLevel(1);
		BuilderViewReference.get().setCurrentLevel(level);
		ChangeRefMapAction crma = new ChangeRefMapAction(ref, select);
		crma.tempDo();
		crma.doAction();
		crma.undoAction();
	}
}
