package jasirocki;

import junit.framework.TestCase;
import ks.client.gamefactory.GameWindow;
import ks.common.model.Card;
import ks.common.model.Column;
import ks.common.model.Deck;
import ks.launcher.Main;
import ks.tests.model.ModelFactory;

public class TestTableauTableauMove extends TestCase {

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
	
	public void testTableauTableauMove() {
		
		Column t0 = fortyThieves.tableau[0];
		Column t1 = fortyThieves.tableau[1];
		Column t2 = fortyThieves.tableau[2];
		//topTableau2 = new Card(topTableau1.getRank()-1, topTableau1.getSuit());
		
		ModelFactory.init(t0, "8S 7S 6S 5S");
		ModelFactory.init(t1, "2S 3S 4S");
		ModelFactory.init(t2, "AS");
		
		TableauTableauMove ttm = new TableauTableauMove(t1, t1.get(), t0);
		
		assertTrue(ttm.valid(fortyThieves));
		assertTrue(ttm.doMove(fortyThieves));
		
		System.out.println("" + t1.peek());
		
		TableauTableauMove ttm1 = new TableauTableauMove(t1, t1.get(), t0);
		TableauTableauMove ttm2 = new TableauTableauMove(t1, t1.get(), t0);
		TableauTableauMove ttm3 = new TableauTableauMove(t2, t2.get(), t0);
		
		assertTrue(ttm1.valid(fortyThieves));
		assertTrue(ttm1.doMove(fortyThieves));
		
		assertTrue(ttm2.valid(fortyThieves));
		assertTrue(ttm2.doMove(fortyThieves));
		
		assertTrue(ttm3.valid(fortyThieves));
		assertTrue(ttm3.doMove(fortyThieves));
		
		TableauTableauMove ttm4 = new TableauTableauMove(t0, t0.get(), t1);
		TableauTableauMove ttm5 = new TableauTableauMove(t0, t0.get(), t1);
		
		assertTrue(ttm4.valid(fortyThieves));
		assertTrue(ttm4.doMove(fortyThieves));
		
		assertFalse(ttm5.valid(fortyThieves));
		assertFalse(ttm5.doMove(fortyThieves));
		
		assertTrue(ttm4.undo(fortyThieves));
		assertTrue(ttm3.undo(fortyThieves));
		assertTrue(ttm2.undo(fortyThieves));
		assertTrue(ttm1.undo(fortyThieves));
		
	}
	
}
