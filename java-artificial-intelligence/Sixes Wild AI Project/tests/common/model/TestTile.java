package common.model;

import junit.framework.TestCase;

public class TestTile extends TestCase {
	
	Tile tile;
	
	@Override
	protected void setUp() throws Exception {
		// so all I have left to do with this is run copy...
		tile = new Tile(2,2);
	}

	@Override
	protected void tearDown() throws Exception {
		// TODO Auto-generated method stub
		super.tearDown();
	}

	public void testTileCopy() {
		Tile t = tile.makeCopy();
	}
	
}
