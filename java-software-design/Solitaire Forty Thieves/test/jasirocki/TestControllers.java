package jasirocki;

import java.awt.event.MouseEvent;

import ks.client.gamefactory.GameWindow;
import ks.common.model.Deck;
import ks.launcher.Main;
import ks.tests.KSTestCase;
import ks.tests.model.ModelFactory;

public class TestControllers extends KSTestCase {

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
	
	/**
	 * Tests all Controllers
	 *  Mouse Presses
	 *   - Deck(1) "QC"
	 *   - Waste(3) "AS 2S 3S"
	 *   - Tableaus(4) "3S 4S AC AD"
	 *  Mouse Releases
	 *   - Foundation(5) "AS 2S 3S AC AD"
	 *   - Tableau(2) "3S 4S"
	 *   
	 * Tests all five move classes.
	 */
	public void testControllers() {
		MouseEvent press = this.createPressed(fortyThieves, fortyThieves.deckView, 0, 0);
		fortyThieves.deckView.getMouseManager().handleMouseEvent(press);
		
		assertEquals(fortyThieves.waste.peek().toString(), "QC");
		
		ModelFactory.init(fortyThieves.waste, "3S 2S AS");
		
		MouseEvent press1 = this.createPressed(fortyThieves, fortyThieves.wasteView, 0, 0);
		fortyThieves.wasteView.getMouseManager().handleMouseEvent(press1);
		assertEquals("2S", fortyThieves.waste.peek().toString());
		
		MouseEvent release = this.createReleased(fortyThieves, fortyThieves.foundationViews[0], 0, 0);
		fortyThieves.foundationViews[0].getMouseManager().handleMouseEvent(release);
		assertEquals("AS", fortyThieves.foundation[0].peek().toString());
		
		MouseEvent press2 = this.createPressed(fortyThieves, fortyThieves.wasteView, 0, 0);
		fortyThieves.wasteView.getMouseManager().handleMouseEvent(press2);
		assertEquals("3S", fortyThieves.waste.peek().toString());
		
		MouseEvent release1 = this.createReleased(fortyThieves, fortyThieves.foundationViews[0], 0, 0);
		fortyThieves.foundationViews[0].getMouseManager().handleMouseEvent(release1);
		assertEquals("2S", fortyThieves.foundation[0].peek().toString());
		
		ModelFactory.init(fortyThieves.tableau[0], "7S 6S 5S");
		ModelFactory.init(fortyThieves.tableau[1], "9S 8S 4S");
		ModelFactory.init(fortyThieves.tableau[2], "AC AD");
		
		MouseEvent press3 = this.createPressed(fortyThieves, fortyThieves.tableauViews[1], 0, 80);
		fortyThieves.tableauViews[1].getMouseManager().handleMouseEvent(press3);
		assertEquals("8S", fortyThieves.tableau[1].peek().toString());
		
		MouseEvent release2 = this.createReleased(fortyThieves, fortyThieves.tableauViews[0], 0, 0);
		fortyThieves.tableauViews[0].getMouseManager().handleMouseEvent(release2);
		assertEquals("4S", fortyThieves.tableau[0].peek().toString());
		
		MouseEvent press4 = this.createPressed(fortyThieves, fortyThieves.wasteView, 0, 0);
		fortyThieves.wasteView.getMouseManager().handleMouseEvent(press4);
		
		MouseEvent release3 = this.createReleased(fortyThieves, fortyThieves.tableauViews[0], 0, 0);
		fortyThieves.tableauViews[0].getMouseManager().handleMouseEvent(release3);
		assertEquals("3S", fortyThieves.tableau[0].peek().toString());
		
		MouseEvent press5 = this.createPressed(fortyThieves, fortyThieves.tableauViews[0], 0, 120);
		fortyThieves.tableauViews[0].getMouseManager().handleMouseEvent(press5);
		assertEquals("4S", fortyThieves.tableau[0].peek().toString());
		
		MouseEvent release4 = this.createReleased(fortyThieves, fortyThieves.foundationViews[0], 0, 0);
		fortyThieves.foundationViews[0].getMouseManager().handleMouseEvent(release4);
		assertEquals("3S", fortyThieves.foundation[0].peek().toString());
		
		MouseEvent press6 = this.createPressed(fortyThieves, fortyThieves.tableauViews[2], 0, 40);
		fortyThieves.tableauViews[2].getMouseManager().handleMouseEvent(press6);
		assertEquals("AC", fortyThieves.tableau[2].peek().toString());
		
		MouseEvent release5 = this.createReleased(fortyThieves, fortyThieves.foundationViews[1], 0, 0);
		fortyThieves.foundationViews[1].getMouseManager().handleMouseEvent(release5);
		assertEquals("AD", fortyThieves.foundation[1].peek().toString());
		
		MouseEvent press7 = this.createPressed(fortyThieves, fortyThieves.tableauViews[2], 0, 0);
		fortyThieves.tableauViews[2].getMouseManager().handleMouseEvent(press7);
		
		MouseEvent release6 = this.createReleased(fortyThieves, fortyThieves.foundationViews[2], 0, 0);
		fortyThieves.foundationViews[2].getMouseManager().handleMouseEvent(release6);
		assertEquals("AC", fortyThieves.foundation[2].peek().toString());
	}
}
