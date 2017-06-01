package jasirocki;

import junit.framework.TestCase;
import ks.client.gamefactory.GameWindow;
import ks.common.model.Card;
import ks.common.model.Deck;
import ks.launcher.Main;

public class TestDealCardMove extends TestCase {
	
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
	
	public void testDealCardMove() {
		Card topCard = fortyThieves.deck.peek();
		DealCardMove dcm = new DealCardMove(fortyThieves.deck, fortyThieves.waste);
		
		assertTrue(dcm.valid(fortyThieves));
		
		dcm.doMove(fortyThieves);
		
		assertEquals(63, fortyThieves.deck.count());
		assertEquals(topCard, fortyThieves.waste.peek());
		int value = fortyThieves.getNumLeft().getValue();
		
		assertEquals(63, value);
		
		dcm.undo(fortyThieves);
		
		assertEquals(64, fortyThieves.deck.count());
	}
}
