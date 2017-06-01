package builder.model;

import gameModes.puzzle.PuzzleLevel;
import junit.framework.TestCase;
import builder.actions.StarThresholdAction;

import common.model.LevelList;

public class TestBuilderModel extends TestCase {

	BuilderModel builderModel;
	LevelList list;
		
	@Override
	protected void setUp() throws Exception {
		list=new LevelList();
		list.addLevel(PuzzleLevel.class);		
		list.addLevel(PuzzleLevel.class);
		list.addLevel(PuzzleLevel.class);
		list.addLevel(PuzzleLevel.class);
	}

	@Override
	protected void tearDown() throws Exception {
		// TODO Auto-generated method stub
		super.tearDown();
	}

	public void testBuilderModel() {
		builderModel = new BuilderModel(list);
	}
}
