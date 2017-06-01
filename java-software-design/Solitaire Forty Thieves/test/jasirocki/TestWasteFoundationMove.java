package jasirocki;

import junit.framework.TestCase;
import ks.client.gamefactory.GameWindow;
import ks.common.model.Card;
import ks.common.model.Deck;
import ks.common.model.Pile;
import ks.launcher.Main;
import ks.tests.model.ModelFactory;

public class TestWasteFoundationMove extends TestCase {

	FortyThieves fortyThieves;
	GameWindow gw;
	
	@Override
	protected void setUp() {
		fortyThieves = new FortyThieves();
		gw = Main.generateWindow(fortyThieves, Deck.OrderBySuit);
	}
	
	@Override
	protected void tearDown() {
		gw.dispose();
	}
	
	public void testWasteFoundationMove() {
		Pile p0 = fortyThieves.foundation[0];
		Pile waste = fortyThieves.waste;
		
		ModelFactory.init(waste, "5S AS");
		WasteFoundationMove wfm = new WasteFoundationMove(waste, waste.get(), p0);
		WasteFoundationMove wfm1 = new WasteFoundationMove(waste, waste.get(), p0);
		
		assertTrue(wfm.valid(fortyThieves));
		assertTrue(wfm.doMove(fortyThieves));
		
		assertFalse(wfm1.valid(fortyThieves));
		assertFalse(wfm1.doMove(fortyThieves));
		
		assertTrue(wfm.undo(fortyThieves));

	}
	
}
