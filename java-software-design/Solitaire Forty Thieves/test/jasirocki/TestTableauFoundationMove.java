package jasirocki;

import junit.framework.TestCase;
import ks.client.gamefactory.GameWindow;
import ks.common.model.Column;
import ks.common.model.Deck;
import ks.common.model.Pile;
import ks.launcher.Main;
import ks.tests.model.ModelFactory;

public class TestTableauFoundationMove extends TestCase {

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
	
	public void testTableauFoundationMove() {
		
		Column t0 = fortyThieves.tableau[0];
		Column t1 = fortyThieves.tableau[1];
		Column t2 = fortyThieves.tableau[2];
		Pile p0 = fortyThieves.foundation[0];
		//topTableau2 = new Card(topTableau1.getRank()-1, topTableau1.getSuit());
		
		ModelFactory.init(t0, "8S 7S 6S 5S");
		ModelFactory.init(t1, "4S 3S 2S");
		ModelFactory.init(t2, "AS");
		
		TableauFoundationMove tfm = new TableauFoundationMove(t0, t0.get(), p0);
		
		assertFalse(tfm.valid(fortyThieves));
		assertFalse(tfm.doMove(fortyThieves));
		assertFalse(tfm.undo(fortyThieves));
		
		TableauFoundationMove tfm1 = new TableauFoundationMove(t2, t2.get(), p0);
		TableauFoundationMove tfm2 = new TableauFoundationMove(t1, t1.get(), p0);
		
		assertTrue(tfm1.valid(fortyThieves));
		assertTrue(tfm1.doMove(fortyThieves));
		
		assertTrue(tfm2.valid(fortyThieves));
		assertTrue(tfm2.doMove(fortyThieves));
		
		assertTrue(tfm2.undo(fortyThieves) && tfm1.undo(fortyThieves));
		
		
	}
	
}
