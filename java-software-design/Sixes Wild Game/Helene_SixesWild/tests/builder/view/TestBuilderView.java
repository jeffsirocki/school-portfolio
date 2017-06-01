package builder.view;

import gameModes.elimination.EliminationLevel;
import gameModes.lightning.LightningLevel;
import gameModes.puzzle.PuzzleLevel;
import gameModes.release.ReleaseLevel;

import javax.swing.JComboBox;
import javax.swing.JSlider;

import common.model.AbstractLevel;
import builder.BuilderApplication;
import builder.controllers.BuilderViewReference;
import junit.framework.TestCase;

public class TestBuilderView extends TestCase {
	
	BuilderView view;
	
	@Override
	protected void setUp() throws Exception {
		BuilderViewReference.clear();
		
		BuilderApplication builderApp = new BuilderApplication();
		builderApp.getFrame().setVisible(true);
		view=BuilderViewReference.get();
		
		
		for(AbstractLevel l:view.getModel().getAllLevels()){
			view.getModel().getAllLevels().removeLevel(l);
		}
		
		//Set up with 4 puzzle, 3 release,2 elim, 1 lightning
		for(int i=0;i<4;i++){
			view.getModel().getAllLevels().addLevel(PuzzleLevel.class);
			if(i<3)
				view.getModel().getAllLevels().addLevel(ReleaseLevel.class);
			if(i<2)
				view.getModel().getAllLevels().addLevel(EliminationLevel.class);
			if(i<1)
				view.getModel().getAllLevels().addLevel(LightningLevel.class);
		}
	}

	public void testSimple()
	{
		try{
			Thread.sleep(10);
		} catch(Exception ex) {
			ex.printStackTrace();
		}
		
		JComboBox<String> levelTypeCombo=view.levelTypeCombo;
		JComboBox<Integer> levelNumCombo=view.levelNumCombo;
		JSlider sizeSlider=view.sizeSlider;
		JComboBox<String> squareTypeCombo=view.squareTypeCombo;
		
		levelNumCombo.setSelectedIndex(1);
	}
	
	public void testBoardView() {
		view.resetBoard();
	}
	
	public void testFrequencyPanel() {
		FrequencyPanel fp = new FrequencyPanel();
		fp.setPanelName("HELENE");
		String name = fp.getPanelName();
		fp.setLabelPrefix("INC");
		String label = fp.getLabelPrefix();
	}
	
	public void testParameterView() {
		LevelParameterView lpv = new LevelParameterView();
		lpv.addParameterPanel(null);
		lpv.removeParameterPanel(null);
	}
}
