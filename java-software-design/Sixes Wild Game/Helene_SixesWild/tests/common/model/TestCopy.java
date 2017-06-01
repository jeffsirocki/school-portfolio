package common.model;

import static org.junit.Assert.*;

import java.awt.Point;

import gameModes.elimination.EliminationLevel;
import gameModes.lightning.LightningLevel;
import gameModes.puzzle.PuzzleLevel;
import gameModes.release.ReleaseLevel;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class TestCopy {

	AbstractLevel level;
	Point P;
	
	@Before
	public void setUp() throws Exception {
		level = new PuzzleLevel(20);
		P = new Point(1, 1);

	}

	@After
	public void tearDown() throws Exception {
	}
	
	@Test
	public void testCopyParams() {
		LevelParameters copy = level.getParams().makeCopy();
		assertFalse(level.getParams().equals(copy));
		
		Board board = level.getParams().getBoard().makeCopy();
		AbstractSquare square = level.getParams().getBoard().getSquare(P).makeCopy();
		FrequencyList freq1 = level.getParams().getBonus().makeCopy();
		FrequencyList freq2 = level.getParams().getSpawn().makeCopy();
		ScoreThresholds thresh = level.getParams().getThresholds().makeCopy();
		ReferenceMap ref = level.getParams().getRef().makeCopy();
		
		assertFalse(level.getParams().getBoard().equals(board));
		assertFalse(level.getParams().getBoard().getSquare(P).equals(square));
		assertFalse(level.getParams().getBonus().equals(freq1));
		assertFalse(level.getParams().getSpawn().equals(freq2));
		assertFalse(level.getParams().getThresholds().equals(thresh));
		assertFalse(level.getParams().getRef().equals(ref));
	}
	
	@Test
	public void testCopyLevel(){
		helpCopyLevel();
		level=new EliminationLevel(3);
		helpCopyLevel();
		level=new LightningLevel(5);
		helpCopyLevel();
		level=new ReleaseLevel(8);
		helpCopyLevel();
	}
	

	public void helpCopyLevel() {
		System.out.println(level.getParams());
		AbstractLevel copy = level.makeCopy();
		assertFalse(level.equals(copy));
		assertTrue(level.getHighscore() == copy.getHighscore());
		assertTrue(level.getLevelID() == copy.getLevelID());
		assertFalse(level.getParams() == copy.getParams());
		
		assertFalse(level.getParams().getBoard().equals(copy.getParams().getBoard()));
		assertTrue(level.getParams().getBoard().getSize() == copy.getParams().getBoard().getSize());
		assertTrue(level.getParams().getBoard().getSquare(P).getClass() == copy.getParams().getBoard().getSquare(P).getClass());
		
		assertFalse(level.getParams().getBonus() == copy.getParams().getBonus());
		assertTrue(level.getParams().getBonus().getFreq(1) == copy.getParams().getBonus().getFreq(1));
		assertTrue(level.getParams().getBonus().getFreq(2) == copy.getParams().getBonus().getFreq(2));
		assertTrue(level.getParams().getBonus().getFreq(3) == copy.getParams().getBonus().getFreq(3));
		assertFalse(level.getParams().getBonus().getList().equals(copy.getParams().getBonus().getList()));
		assertFalse(level.getParams().getBonus().getTable().equals(copy.getParams().getBonus().getTable()));
		assertTrue(level.getParams().getBonus().getTable().get(P) == copy.getParams().getBonus().getTable().get(P));
		
		
		assertFalse(level.getParams().getRef().equals(copy.getParams().getRef()));
		assertTrue(level.getParams().getRef().getRef(P).x == copy.getParams().getRef().getRef(P).x);
		assertTrue(level.getParams().getRef().getRef(P).y == copy.getParams().getRef().getRef(P).y);
		
		assertFalse(level.getParams().getSpawn().equals(copy.getParams().getSpawn()));
		assertFalse(level.getParams().getSpawn().getList().equals(copy.getParams().getSpawn().getList()));
		assertFalse(level.getParams().getSpawn().getTable().equals(copy.getParams().getSpawn().getTable()));
		assertTrue(level.getParams().getSpawn().getFreq(1) == copy.getParams().getSpawn().getFreq(1));
		assertTrue(level.getParams().getSpawn().getFreq(2) == copy.getParams().getSpawn().getFreq(2));
		assertTrue(level.getParams().getSpawn().getFreq(3) == copy.getParams().getSpawn().getFreq(3));
		assertTrue(level.getParams().getSpawn().getFreq(4) == copy.getParams().getSpawn().getFreq(4));
		assertTrue(level.getParams().getSpawn().getFreq(5) == copy.getParams().getSpawn().getFreq(5));
		assertTrue(level.getParams().getSpawn().getFreq(6) == copy.getParams().getSpawn().getFreq(6));
		assertTrue(level.getParams().getSpawn().getTable().get(P) == copy.getParams().getSpawn().getTable().get(P));
		
		
		assertFalse(level.getParams().getThresholds().equals(copy.getParams().getThresholds()));
		assertTrue(level.getParams().getThresholds().scoreThresh[1] == copy.getParams().getThresholds().scoreThresh[1]);
		assertTrue(level.getParams().getThresholds().scoreThresh[2] == copy.getParams().getThresholds().scoreThresh[2]);
		assertTrue(level.getParams().getThresholds().scoreThresh[3] == copy.getParams().getThresholds().scoreThresh[3]);
		
		assertTrue(level.getType() == copy.getType());
		assertTrue(level.isUnlocked() == copy.isUnlocked());
	}

}
