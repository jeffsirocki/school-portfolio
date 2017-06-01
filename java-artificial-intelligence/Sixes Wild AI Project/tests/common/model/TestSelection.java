package common.model;

import java.util.Iterator;

import junit.framework.TestCase;

public class TestSelection extends TestCase {

	Selection selection;
	Square sqr;
	
	@Override
	protected void setUp() throws Exception {
		
		sqr = new Square(3, 2);
		
		selection = new Selection(sqr);		
		
	}

	@Override
	protected void tearDown() throws Exception {
		// clear the selection and verify that the square that was added is unselected.
		selection.clear();
		boolean unSelectedSqr = selection.get(0).isSelected();
		assertEquals(unSelectedSqr, false);
	}

	
	public void testSelection() {
		// test the case in which a square already in the selection is added again
		selection.add(sqr);
		
		// test iterator
		Iterator<AbstractSquare> iterator =  selection.iterator();
		
		// test size
		int selectionSize = selection.size();
		assertEquals(selectionSize, 1);
		
		// test get function
		AbstractSquare testSquare = selection.get(0);
		assertEquals(testSquare, sqr);
	}
	
}
