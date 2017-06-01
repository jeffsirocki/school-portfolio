package common.model;

import junit.framework.TestCase;
import player.model.AddToSixMove;

public class TestMove extends TestCase {

	AddToSixMove atsm;
	Selection selection;
	Square square;
	
	@Override
	protected void setUp() throws Exception {
		square = new Square(0,0);
		selection = new Selection(square);
		atsm = new AddToSixMove(selection);
	}

	@Override
	protected void tearDown() throws Exception {
		// TODO Auto-generated method stub
		super.tearDown();
	}

	// TODO tests a failed addToSixMove... for more coverage test one that works LOL
	public void testAddToSixMove() {
		boolean addSixFail = atsm.doMove();
		assertEquals(addSixFail, false);
		int noScore = atsm.getScore();
		assertEquals(noScore, 0);
	}	
}
