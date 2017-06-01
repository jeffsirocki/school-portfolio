package jasirocki;

import junit.framework.TestCase;
import ks.client.gamefactory.GameWindow;
import ks.common.model.Card;
import ks.common.model.Deck;
import ks.launcher.Main;

public class TestWasteTableauMove extends TestCase {

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
		Card topCard = fortyThieves.deck.peek();
		DealCardMove dcm = new DealCardMove(fortyThieves.deck, fortyThieves.waste);
		
		assertTrue(dcm.valid(fortyThieves));
		
		dcm.doMove(fortyThieves);
		
		assertEquals(63, fortyThieves.deck.count());
		assertEquals(topCard, fortyThieves.waste.peek());
		int value = fortyThieves.getNumLeft().getValue();
		
		assertEquals(63, value);
		
		Card topWaste = fortyThieves.waste.peek();
		Card topTableau = fortyThieves.tableau[0].peek();
		topWaste = new Card(topTableau.getRank()-1, topTableau.getSuit());
		WasteTableauMove wtm = new WasteTableauMove(fortyThieves.waste, topWaste, fortyThieves.tableau[0]);
		
		assertTrue(wtm.valid(fortyThieves));
		
		wtm.doMove(fortyThieves);
		
		assertEquals(topWaste, fortyThieves.tableau[0].peek());
		assertTrue(fortyThieves.tableau[0].count() == 5);
		
		wtm.undo(fortyThieves);
		
		assertEquals(63, fortyThieves.deck.count());
		assertTrue(fortyThieves.tableau[0].count() == 4);
		assertEquals(topWaste, fortyThieves.waste.peek());
		value = fortyThieves.getNumLeft().getValue();
		
		assertEquals(63, value);
		
		dcm.undo(fortyThieves);
		
		assertEquals(64, fortyThieves.deck.count());
	}
	
}
