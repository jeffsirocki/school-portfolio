package jasirocki;

import ks.common.games.Solitaire;
import ks.common.model.Card;
import ks.common.model.Column;
import ks.common.model.Stack;

/**
 * Tableau to Tableau move
 * @author jasirocki
 *
 */
public class TableauTableauMove extends ks.common.model.Move {
	
	/** From Column. */
	protected Column from;

	/** To Column. */
	protected Column to;
	
	/** Column being moved. */
	protected Card cardBeingDragged;
	
	/**
	 * MoveCardToFoundationmove constructor.
	 * <p>
	 * If the move has already been made, then <code>columnBeingDragged</code> is non-null, and
	 * <code>numCards == columnBeingDragged.count()</code> If the move has not been made, then
	 * only the number of cards to be moved is known, and <code>columnBeingDragged</code> is null.
	 */
	public TableauTableauMove(Column fromPile, Card cardBeingDragged, Column toPile) {
		super(); 
	
		from = fromPile;
		to = toPile;
		this.cardBeingDragged = cardBeingDragged;
	}
	/**
	 * Each move should knows how to execute itself.
	 * <p>
	 * @param ks.game.Solitaire   the game being played.
	 * @return boolean
	 */
	public boolean doMove (Solitaire theGame) {
		// VALIDATE:
		if (valid(theGame) == false)
			return false;
	
		// EXECUTE:
		// move card from waste to to Pile.
		if (cardBeingDragged == null)
			to.add (from.get());
		else
			to.add (cardBeingDragged);
			
		return true;
	}
	
	/**
	 * undo 
	 */
	public boolean undo(ks.common.games.Solitaire game) {
		// VALIDATE:
		if (to.empty()) return false;
	
		// EXECUTE:
		// remove card and move to waste.
		from.add (to.get());	
		return true;
	}
	
	/**
	 * Move a card to a column
	 *
	 * @param game ks.game.Solitaire
	 */
	public boolean valid (Solitaire game) {
		// VALIDATION:
		boolean validation = false;
	
		Card targetCard = cardBeingDragged;
		
		//   moveColumnBetweenPiles(Column col, Column to) : to.empty()
		if (to.empty())
			validation = true;
	
		// 	  moveColumnBetweenPiles(Column col,Column to) : not col.empty() and col.bottom() == to.rank() - 1 and to.peek().faceUp() == true
		if (!to.empty() && (targetCard.getRank() == to.rank() - 1) && (targetCard.sameSuit(to.peek())))
			validation = true;
	
		return validation;
	}
}
