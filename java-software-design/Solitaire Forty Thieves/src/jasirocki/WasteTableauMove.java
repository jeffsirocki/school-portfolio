package jasirocki;

import ks.common.games.Solitaire;
import ks.common.model.Card;
import ks.common.model.Column;
import ks.common.model.Pile;

/**
 * Waste to Tableau Move
 * @author jasirocki
 *
 */
public class WasteTableauMove extends ks.common.model.Move {
	
	/** The waste pile. */
	protected Pile waste;

	/** The Destination column. */
	protected Column to;
	
	/** The card being dragged (if the move has already been made). */
	protected Card cardBeingDragged;
	
	/**
	 * MoveWasteToFoundationMove constructor comment.
	 */
	public WasteTableauMove(Pile waste, Card cardBeingDragged, Column toColumn) {
		super();
	
		this.waste = waste;
		this.to = toColumn;
		this.cardBeingDragged = cardBeingDragged;
	}
	/**
	 * Each move should knows how to execute itself.
	 * <p>
	 * Creation date: (10/21/01 3:33:39 PM)
	 * @param ks.game.Solitaire   the game being played.
	 * @return boolean
	 * @since V1.6.2
	 */
	public boolean doMove (Solitaire theGame) {
		// VALIDATE:
		if (valid(theGame) == false)
			return false;
	
		// EXECUTE:
		// move card from waste to to Pile.
		if (cardBeingDragged == null)
			to.add (waste.get());
		else
			to.add (cardBeingDragged);
			
		return true;
	}
	/**
	 * Move the card back to the waste pile (can only be one card)
	 */
	public boolean undo(ks.common.games.Solitaire game) {
		// VALIDATE:
		if (to.empty()) return false;
	
		// EXECUTE:
		// remove card and move to waste.
		waste.add (to.get());	
		return true;
	}
	/**
	 * Action for FortyThieves: Move a card to from the Waste Pile to a Column
	 * @param game ks.games.Solitaire
	 */
	public boolean valid (Solitaire theGame) {
		
		// VALIDATION:
		boolean validation = false;
	
		//   moveWastetoColumn (Waste from, Column to) : to.empty()
		if (to.empty())
			validation = true;

		// 	  moveColumnBetweenPiles(Waste from,Column to) : not to.empty() and cardBeingDragged.rank() == to.rank() - 1 and to.peek().faceUp()
		if (!to.empty() && (cardBeingDragged.getRank() == to.rank() - 1) && (cardBeingDragged.sameSuit(to.peek())))
			validation = true;
	
		return validation;
	}
}