package player.view;

import gameModes.elimination.EliminationLevel;
import gameModes.lightning.LightningLevel;
import gameModes.puzzle.PuzzleLevel;
import gameModes.release.ReleaseLevel;
import common.model.AbstractLevel;
import player.PlayerApplication;
import player.controllers.PlayerAppReference;
import player.controllers.SelectLevelController;
import player.controllers.ToLevelSelectController;
import junit.framework.TestCase;

public class TestPlayerView extends TestCase {
	
	PlayerApplication window;

	protected void setUp() throws Exception {
		PlayerAppReference.clear();
		
		window = new PlayerApplication();
		window.getFrame().setVisible(true);
		
		for(AbstractLevel l:window.getAllLevels()){
			window.getAllLevels().removeLevel(l);
		}
		
		for(int i=0;i<4;i++){
			window.getAllLevels().addLevel(PuzzleLevel.class);
		if(i<3)
			window.getAllLevels().addLevel(ReleaseLevel.class);
		if(i<2)
			window.getAllLevels().addLevel(EliminationLevel.class);
		if(i<1)
			window.getAllLevels().addLevel(LightningLevel.class);
		}
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}

	public void testSimple()
	{
		try{
			Thread.sleep(100);
		} catch(Exception ex) {
			ex.printStackTrace();
		}
		
		window.getStartView();
		window.getSelectView();
		window.getGameView();
		
		ToLevelSelectController toSel=new ToLevelSelectController();
		SelectLevelController puzzle=new SelectLevelController(window.getAllLevels().getLevel(PuzzleLevel.class,1));
		SelectLevelController lightning=new SelectLevelController(window.getAllLevels().getLevel(LightningLevel.class,1));
		SelectLevelController elimination=new SelectLevelController(window.getAllLevels().getLevel(EliminationLevel.class,1));
		SelectLevelController release=new SelectLevelController(window.getAllLevels().getLevel(ReleaseLevel.class,1));

		toSel.go();
		puzzle.go();
		
		try{
			Thread.sleep(100);
		} catch(Exception ex) {
			ex.printStackTrace();
		}
		
		toSel.go();
		lightning.go();
		
		try{
			Thread.sleep(100);
		} catch(Exception ex) {
			ex.printStackTrace();
		}
		
		toSel.go();
		release.go();
		
		try{
			Thread.sleep(100);
		} catch(Exception ex) {
			ex.printStackTrace();
		}
		
		toSel.go();
		elimination.go();
		
		try{
			Thread.sleep(100);
		} catch(Exception ex) {
			ex.printStackTrace();
		}
	
		PlayerAppReference.updateFields();
	}
	
}
