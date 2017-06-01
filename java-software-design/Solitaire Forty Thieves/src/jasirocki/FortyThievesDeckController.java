package jasirocki;

import ks.common.controller.SolitaireReleasedAdapter;
import ks.common.model.Deck;
import ks.common.model.Move;
import ks.common.model.Pile;

/**
 * DeckView Controller
 *  - Handles(press)
 * @author jasirocki
 *
 */
public class FortyThievesDeckController extends SolitaireReleasedAdapter {
	
	/** The game. */
	protected FortyThieves theGame;

	/** The WastePile of interest. */
	protected Pile waste;

	/** The Deck of interest. */
	protected Deck deck;

	/**
	 * FortyThievesDeckController constructor comment.
	 */
	public FortyThievesDeckController(FortyThieves theGame, Deck d, Pile waste) {
		super(theGame);

		this.theGame = theGame;
		this.waste = waste;
		this.deck = d;
	}

	/**
	 * Coordinate reaction to the beginning of a Drag Event. In this case,
	 * no drag is ever achieved, and we simply deal upon the pres.
	 */
	public void mousePressed (java.awt.event.MouseEvent me) {

		// Attempting a DealFourCardMove
		Move m = new DealCardMove (deck, waste);
		if (m.doMove(theGame)) {
			theGame.pushMove(m);
			theGame.refreshWidgets();
 		}
	}
}