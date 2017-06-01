package common.model;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Collections;

import gameModes.elimination.EliminationLevel;
import gameModes.lightning.LightningLevel;
import gameModes.puzzle.PuzzleLevel;
import gameModes.release.ReleaseLevel;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class LevelListTester {
	
	LevelList list;

	@Before
	public void setUp() throws Exception {
		list=new LevelList();
	}

	@After
	public void tearDown() throws Exception {
		
	}

	@Test
	public void testLevelList() {
		list.addLevel(PuzzleLevel.class);
		System.out.println(list);
		
		list.addLevel(PuzzleLevel.class);
		list.addLevel(PuzzleLevel.class);
		list.addLevel(PuzzleLevel.class);
		System.out.println(list);
		
		list.addLevel(ReleaseLevel.class);
		list.addLevel(ReleaseLevel.class);
		list.addLevel(ReleaseLevel.class);
		
		list.addLevel(EliminationLevel.class);
		list.addLevel(EliminationLevel.class);

		list.addLevel(LightningLevel.class);

		System.out.println(list);
		
		assertEquals(list.countType(LightningLevel.class),1);
		assertEquals(list.countTypes(),4);

		list.removeLevel(list.getType(LightningLevel.class).get(0));
		assertEquals(list.countType(LightningLevel.class),0);
		
		assertEquals(list.countTypes(),3);
		
		list.updateUnlocked();
		
		ArrayList<AbstractLevel> l=list.getType(PuzzleLevel.class);
		System.out.println(l);
		Collections.sort(l);
		System.out.println(l);
		for(int i=1;i<l.size();i++){
			assertTrue(l.get(i).getLevelID()>l.get(i-1).getLevelID());
		}
		assertTrue(l.get(0).isUnlocked());
		assertTrue(l.get(1).isUnlocked()==false);
		l.get(0).setHighscore(1000);
		list.updateUnlocked();
		
		assertTrue(l.get(1).isUnlocked());
	}

}
