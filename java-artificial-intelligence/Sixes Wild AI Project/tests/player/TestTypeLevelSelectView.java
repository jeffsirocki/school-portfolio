package player;

import gameModes.elimination.EliminationLevel;
import gameModes.lightning.LightningLevel;
import gameModes.puzzle.PuzzleLevel;
import gameModes.release.ReleaseLevel;

import java.awt.Dimension;

import javax.swing.JTabbedPane;

import junit.framework.TestCase;
import player.controllers.PlayerAppReference;
import player.view.TypeSelectView;
import common.model.LevelList;

public class TestTypeLevelSelectView extends TestCase {
	
	@Override
	protected void setUp() throws Exception {
		PlayerAppReference.clear();
	}

	public void testSimple()
	{
		try{
			Thread.sleep(10);
		} catch(Exception ex) {
			ex.printStackTrace();
		}
		
		PlayerApplication playerApp = new PlayerApplication();
		
		TypeSelectView typeSelect = playerApp.getSelectView();
		LevelList levels = new LevelList();
		for(int i = 1; i <= 30; i++)
		{
			levels.addLevel(new LightningLevel(i));
		}
		levels.addLevel(new EliminationLevel(1));
		EliminationLevel e2 = new EliminationLevel(2);
		e2.setUnlocked(true);
		levels.addLevel(e2);
		
		typeSelect.initialize(levels);
		assertEquals(1, typeSelect.getComponentCount());
		assertEquals(2, ((JTabbedPane)typeSelect.getComponent(0)).getComponentCount());
		
		levels.addLevel(new PuzzleLevel(1));
		levels.addLevel(new ReleaseLevel(1));
		assertEquals(2, ((JTabbedPane)typeSelect.getComponent(0)).getComponentCount());
		
		typeSelect.updateFields();
		assertEquals(4, ((JTabbedPane)typeSelect.getComponent(0)).getComponentCount());	
		
		PlayerAppReference.get().getFrame().setSize(800, 600);
		typeSelect.setSize(800,600);
		PlayerAppReference.get().getFrame().setSize(1, 1);
		assertEquals(PlayerAppReference.get().getFrame().getSize(), new Dimension(450, 400));
	}
}
