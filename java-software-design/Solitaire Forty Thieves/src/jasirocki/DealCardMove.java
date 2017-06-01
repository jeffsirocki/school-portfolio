package jasirocki;

import ks.common.games.Solitaire;
import ks.common.model.Card;
import ks.common.model.Deck;
import ks.common.model.Move;
import ks.common.model.Stack;

/**
 * Move card from top of deck to top of waste pile.
 */
public class DealCardMove extends Move {
	Deck deck;
	Stack stack;

	/**
	 * Constructor
	 * @param deck
	 * @param stack
	 */
	public DealCardMove(Deck deck, Stack stack) {
		this.deck = deck;
		this.stack = stack;
	}
	
	@Override
	public boolean doMove(Solitaire game) {
		if (!valid(game)) { return false; }
		
		Card card = deck.get();
		stack.add(card);
		game.updateNumberCardsLeft(-1);
		return true;
	}

	@Override
	public boolean undo(Solitaire game) {
		Card c = stack.get();
		deck.add(c);
		game.updateNumberCardsLeft(+1);
		return true;
	}

	@Override
	public boolean valid(Solitaire game) {
		return !deck.empty();
	}

}
