package jasirocki;

import ks.common.games.Solitaire;
import ks.common.model.Column;
import ks.common.model.Card;
import ks.common.model.Pile;

/**
 * Tableau to Foundation move
 * @author jasirocki
 *
 */
public class TableauFoundationMove extends ks.common.model.Move {
	
	/** The Column. */
	protected Column column;

	/** The card being dragged (if at all). */
	protected Card draggingCard;
	
	/** The destination Foundation Pile. */
	protected Pile foundation;
	
	/**
	 * MoveCardToFoundationmove constructor comment.
	 */
	public TableauFoundationMove(Column c, Card card, Pile foundation) {
		super();
	
		column = c;
		this.draggingCard = card;
		this.foundation = foundation;
	}
	/**
	 * Each move should knows how to execute itself.
	 * <p>
	 * @param ks.game.Solitaire   the game being played.
	 * @return boolean
	 */
	public boolean doMove (Solitaire theGame) {
		// VALIDATE (should we also check for validitation by rank? suit?
		if (valid (theGame) == false)
			return false;
	
		// EXECUTE:
		// Deal with both situations
		if (draggingCard == null)
			foundation.add(column.get());
		else
			foundation.add(draggingCard);
	
		// advance score
		theGame.updateScore (1);
		return true;
	}
	/**
	 * undo method.
	 */
	public boolean undo(Solitaire game) {
		// VALIDATE:
		if (foundation.empty()) return false;
	
		// EXECUTE UNDO:	
		column.add(foundation.get());
	
		// reverse score advance
		game.updateScore (-1);
		return true;
	}
	/**
	 * Action for Klondike: Column card draggged to Foundation Pile
	 * @param d ks.common.games.Solitaire
	 */
	public boolean valid(ks.common.games.Solitaire game) {
		// VALIDATION:
		boolean validation = false;
	
		// If draggingCard is null, then no action has yet taken place.
		if (draggingCard == null) {
			// Suit are the same, Rank is one less, empty checks
			if (!foundation.empty() && !column.empty() && (column.rank() == foundation.rank() + 1) && (column.suit() == foundation.suit()))
				validation = true;
	
			// If foundation is empty, check if ace
			if (foundation.empty() && !column.empty() && column.rank() == Card.ACE)
				validation = true;  
		} else {
			// the action must have taken place, so act on the card.
	
			// moveWasteToFoundation(waste,pile) : not foundation.empty() and not waste.empty() and 
			if (!foundation.empty() && (draggingCard.getRank() == foundation.rank() + 1) && (draggingCard.getSuit() == foundation.suit()))
				validation = true;
	
				// moveWasteToFoundation(waste,pile) : foundation.empty() and card.rank() == ACE
			if (foundation.empty() && (draggingCard.getRank() == Card.ACE))
				validation = true;
		}
	
		return validation;
	}
}
